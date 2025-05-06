package fanasina.group.fifacentralapi.service;

import fanasina.group.fifacentralapi.dao.PlayerRankingDAO;
import fanasina.group.fifacentralapi.dto.BestPlayerResponse;
import fanasina.group.fifacentralapi.enums.DurationUnit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerRankingService {
    private final PlayerRankingDAO playerRankingDAO;

    public PlayerRankingService(PlayerRankingDAO playerRankingDAO) {
        this.playerRankingDAO = playerRankingDAO;
    }

    public List<BestPlayerResponse> getAllBestPlayers() {
        return playerRankingDAO.findAll()
                .stream()
                .map(player -> new BestPlayerResponse(
                        player.getName(),
                        player.getNumber(),
                        player.getPosition(),
                        player.getNationality(),
                        player.getAge(),
                        player.getChampionship(),
                        player.getScoredGoals(),
                        player.getPlayingTime()))
                .collect(Collectors.toList());
    }

    public List<BestPlayerResponse> getBestPlayersWithTopFilter(int top) {
        return playerRankingDAO.findTopPlayers(top)
                .stream()
                .map(player -> new BestPlayerResponse(
                        player.getName(),
                        player.getNumber(),
                        player.getPosition(),
                        player.getNationality(),
                        player.getAge(),
                        player.getChampionship(),
                        player.getScoredGoals(),
                        player.getPlayingTime()))
                .collect(Collectors.toList());
    }

    public List<BestPlayerResponse> getBestPlayersWithPlayingTimeFilter(DurationUnit unit) {
        return playerRankingDAO.findPlayersByPlayingTimeUnit(unit)
                .stream()
                .map(player -> new BestPlayerResponse(
                        player.getName(),
                        player.getNumber(),
                        player.getPosition(),
                        player.getNationality(),
                        player.getAge(),
                        player.getChampionship(),
                        player.getScoredGoals(),
                        player.getPlayingTime()))
                .collect(Collectors.toList());
    }

    public List<BestPlayerResponse> getBestPlayersWithFilters(int top, DurationUnit unit) {
        return playerRankingDAO.findTopPlayersByPlayingTimeUnit(top, unit)
                .stream()
                .map(player -> new BestPlayerResponse(
                        player.getName(),
                        player.getNumber(),
                        player.getPosition(),
                        player.getNationality(),
                        player.getAge(),
                        player.getChampionship(),
                        player.getScoredGoals(),
                        player.getPlayingTime()))
                .collect(Collectors.toList());
    }
}