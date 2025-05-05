package fanasina.group.fifacentralapi.service;

import fanasina.group.fifacentralapi.config.ChampionshipConfig;
import fanasina.group.fifacentralapi.dto.ClubDto;
import fanasina.group.fifacentralapi.dto.PlayerDto;
import fanasina.group.fifacentralapi.entity.Club;
import fanasina.group.fifacentralapi.entity.Player;
import fanasina.group.fifacentralapi.repository.ClubRepository;
import fanasina.group.fifacentralapi.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class SyncService {
    private static final Logger log = LoggerFactory.getLogger(SyncService.class);

    private final ApiClientService apiClient;
    private final ChampionshipConfig config;
    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;
    private final Map<String, List<PlayerDto>> playersCache = new ConcurrentHashMap<>();
    private final Map<String, List<ClubDto>> clubsCache = new ConcurrentHashMap<>();

    // Constructeur avec injection des dépendances
    public SyncService(ApiClientService apiClient,
                       ChampionshipConfig config,
                       PlayerRepository playerRepository,
                       ClubRepository clubRepository) {
        this.apiClient = apiClient;
        this.config = config;
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
    }

    @Scheduled(fixedRate = 3600000) // Toutes les heures
    @Transactional
    public void syncFromAllChampionships() {
        log.info("Début de la synchronisation des données");

        config.getUrls().forEach((champName, baseUrl) -> {
            try {
                log.info("Synchronisation pour le championnat: {}", champName);

                // Récupération des données
                List<PlayerDto> players = apiClient.fetchPlayers(baseUrl, champName);
                List<ClubDto> clubs = apiClient.fetchClubs(baseUrl, champName);

                // Mise en cache
                playersCache.put(champName, players);
                clubsCache.put(champName, clubs);

                // Persistance en base
                savePlayers(players, champName);
                saveClubs(clubs, champName);

                log.info("Synchronisation réussie pour {}: {} joueurs, {} clubs",
                        champName, players.size(), clubs.size());

            } catch (Exception e) {
                log.error("Échec de synchronisation pour {}: {}", champName, e.getMessage());
                log.debug("StackTrace: ", e);
            }
        });
    }

    private void savePlayers(List<PlayerDto> playerDtos, String championship) {
        List<Player> players = playerDtos.stream()
                .map(dto -> convertToPlayerEntity(dto, championship))
                .collect(Collectors.toList());

        playerRepository.saveAll(players);
    }

    private void saveClubs(List<ClubDto> clubDtos, String championship) {
        List<Club> clubs = clubDtos.stream()
                .map(dto -> convertToClubEntity(dto, championship))
                .collect(Collectors.toList());

        clubRepository.saveAll(clubs);
    }

    private Player convertToPlayerEntity(PlayerDto dto, String championship) {
        Player player = new Player();
        player.setExternalId(dto.getId());
        player.setName(dto.getName());
        player.setNumber(dto.getNumber());
        player.setPosition(dto.getPosition());
        player.setNationality(dto.getNationality());
        player.setAge(dto.getAge());
        player.setGoals(dto.getGoals());
        player.setPlayingTime(dto.getPlayingTime());
        player.setChampionship(championship);
        if (dto.getClubId() != null) {
            Club club = new Club();
            club.setExternalId(dto.getClubId());
            // Vous devrez peut-être récupérer le club complet depuis la base
            // ou adapter selon votre modèle
            player.setClubId(club.getId()); // À adapter selon votre implémentation
        }
        return player;
    }

    private Club convertToClubEntity(ClubDto dto, String championship) {
        Club club = new Club();
        club.setExternalId(dto.getId());
        club.setName(dto.getName());
        club.setAcronym(dto.getAcronym());
        club.setCreationYear(dto.getCreationYear());
        club.setStadium(dto.getStadium());
        club.setCoachName(dto.getCoachName());
        club.setCoachNationality(dto.getCoachNationality());
        club.setPoints(dto.getPoints());
        club.setGoalsScored(dto.getGoalsScored());
        club.setGoalsConceded(dto.getGoalsConceded());
        club.setCleanSheets(dto.getCleanSheets());
        club.setChampionship(championship);
        return club;
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