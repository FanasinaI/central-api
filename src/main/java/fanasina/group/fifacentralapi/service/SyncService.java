package fanasina.group.fifacentralapi.service;

import fanasina.group.fifacentralapi.config.ChampionshipConfig;
import fanasina.group.fifacentralapi.dto.ClubDto;
import fanasina.group.fifacentralapi.dto.PlayerDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class SyncService {
    private final ApiClientService apiClient;
    private final ChampionshipConfig config;

    private final Map<String, List<PlayerDto>> playersCache = new ConcurrentHashMap<>();
    private final Map<String, List<ClubDto>> clubsCache = new ConcurrentHashMap<>();

    public SyncService(ApiClientService apiClient, ChampionshipConfig config) {
        this.apiClient = apiClient;
        this.config = config;
    }

    @Scheduled(fixedRate = 3600000)
    public void syncFromAllChampionships() {
        config.getUrls().forEach((champName, baseUrl) -> {
            try {
                List<PlayerDto> players = apiClient.fetchPlayers(baseUrl, champName);
                List<ClubDto> clubs = apiClient.fetchClubs(baseUrl, champName);

                playersCache.put(champName, players);
                clubsCache.put(champName, clubs);
            } catch (Exception e) {
                System.err.println("Erreur de synchronisation pour " + champName + ": " + e.getMessage());
                e.printStackTrace();
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

    public Map<String, List<ClubDto>> getClubsByChampionship() {
        return Collections.unmodifiableMap(clubsCache);
    }
}