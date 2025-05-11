package fanasina.group.fifacentralapi.service;

import fanasina.group.fifacentralapi.dao.SynchronizeDAO;
import fanasina.group.fifacentralapi.dto.ClubDTO;
import fanasina.group.fifacentralapi.dto.PlayerDTO;
import fanasina.group.fifacentralapi.entity.ClubRanking;
import fanasina.group.fifacentralapi.entity.PlayerRanking;
import fanasina.group.fifacentralapi.enums.Championship;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SynchronizationService {
    private final SynchronizeDAO synchronizeDAO;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private static final List<String> CHAMPIONSHIP_PORTS = List.of(
            "8080"
    );

    public SynchronizationService(SynchronizeDAO synchronizeDAO,
                                  RestTemplate restTemplate,
                                  ObjectMapper objectMapper) {
        this.synchronizeDAO = synchronizeDAO;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void synchronize() {
        for (String port : CHAMPIONSHIP_PORTS) {
            Championship championship = getChampionshipByPort(port);

            String clubsUrl = String.format("http://localhost:%s/clubs", port);
            ClubDTO[] clubArray = restTemplate.getForObject(clubsUrl, ClubDTO[].class);
            List<ClubDTO> clubDTOs = clubArray != null ? List.of(clubArray) : List.of();

            String playersUrl = String.format("http://localhost:%s/players", port);
            PlayerDTO[] playerArray = restTemplate.getForObject(playersUrl, PlayerDTO[].class);
            List<PlayerDTO> playerDTOs = playerArray != null ? List.of(playerArray) : List.of();

            String clubStatsUrl = String.format("http://localhost:%s/clubs/statistics/2024", port);
            ClubRanking[] clubStatsArray = restTemplate.getForObject(clubStatsUrl, ClubRanking[].class);
            List<ClubRanking> clubRankings = clubStatsArray != null ? List.of(clubStatsArray) : List.of();

            // Traitement des joueurs
            List<PlayerRanking> playerRankings = playerDTOs.stream()
                    .map(player -> {
                        try {
                            String playerStatsUrl = String.format(
                                    "http://localhost:%s/players/%s/statistics/season1",
                                    port, player.getId());

                            PlayerRanking ranking = restTemplate.getForObject(
                                    playerStatsUrl,
                                    PlayerRanking.class);

                            if (ranking != null) {
                                ranking.setId(player.getId().toString());
                                ranking.setName(player.getName());
                                ranking.setNumber(player.getNumber());
                                ranking.setPosition(player.getPosition());
                                ranking.setNationality(player.getNationality());
                                ranking.setAge(player.getAge());
                                ranking.setChampionship(championship);
                            }
                            return ranking;
                        } catch (Exception e) {
                            System.err.printf("Erreur pour le joueur %s: %s%n",
                                    player.getId(), e.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            synchronizeDAO.saveClubs(clubDTOs);
            synchronizeDAO.savePlayers(playerDTOs);
            synchronizeDAO.saveClubRankings(clubRankings, championship, 2024);
            synchronizeDAO.savePlayerRankings(playerRankings, championship, 2024);
        }
    }

    private Championship getChampionshipByPort(String port) {
        switch (port) {
            case "8080": return Championship.PREMIER_LEAGUE;
            case "8082": return Championship.LA_LIGA;
            case "8083": return Championship.BUNDESLIGA;
            case "8084": return Championship.SERIE_A;
            case "8085": return Championship.LIGUE_1;
            default: throw new IllegalArgumentException("Port inconnu: " + port);
        }
    }
}