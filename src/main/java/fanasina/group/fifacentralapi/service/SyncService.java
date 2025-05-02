package fanasina.group.fifacentralapi.service;

import fanasina.group.fifacentralapi.dto.ClubDto;
import fanasina.group.fifacentralapi.dto.PlayerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class SyncService {
    private final ApiClientService apiClient;
    private final Map<String, String> championshipUrls;

    // Stockage thread-safe
    private final Map<String, List<PlayerDto>> playersCache = new ConcurrentHashMap<>();
    private final Map<String, List<ClubDto>> clubsCache = new ConcurrentHashMap<>();

    public SyncService(ApiClientService apiClient,
                       @Value("#{${championship.urls}}") Map<String, String> urls) {
        this.apiClient = apiClient;
        this.championshipUrls = urls;
    }

    @Scheduled(fixedRate = 3600000) // Toutes les heures
    public void syncFromAllChampionships() {  // Renommé pour cohérence
        championshipUrls.forEach((champName, baseUrl) -> {
            try {
                List<PlayerDto> players = apiClient.fetchPlayers(baseUrl, champName);
                List<ClubDto> clubs = apiClient.fetchClubs(baseUrl, champName);

                playersCache.put(champName, players);
                clubsCache.put(champName, clubs);
            } catch (Exception e) {
                System.err.println("Error syncing " + champName + ": " + e.getMessage());
            }
        });
    }

    public List<PlayerDto> getAllPlayers() {
        return playersCache.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<ClubDto> getAllClubs() {
        return clubsCache.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    // Méthode pour accéder aux données par championnat
    public Map<String, List<ClubDto>> getClubsByChampionship() {
        return Collections.unmodifiableMap(clubsCache);
    }

}