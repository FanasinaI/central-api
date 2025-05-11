package fanasina.group.fifacentralapi.service;

import fanasina.group.fifacentralapi.dao.PlayerRankingDAO;
import fanasina.group.fifacentralapi.dto.BestPlayerResponse;
import fanasina.group.fifacentralapi.entity.PlayerRanking;
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
        return mapPlayerRankingsToResponse(playerRankingDAO.findAll());
    }

    public List<BestPlayerResponse> getBestPlayersWithTopFilter(int top) {
        return mapPlayerRankingsToResponse(playerRankingDAO.findTopPlayers(top));
    }

    public List<BestPlayerResponse> getBestPlayersWithPlayingTimeFilter(DurationUnit unit) {
        return mapPlayerRankingsToResponse(playerRankingDAO.findPlayersByPlayingTimeUnit(unit));
    }

    public List<BestPlayerResponse> getBestPlayersWithFilters(int top, DurationUnit unit) {
        return mapPlayerRankingsToResponse(playerRankingDAO.findTopPlayersByPlayingTimeUnit(top, unit));
    }

    private List<BestPlayerResponse> mapPlayerRankingsToResponse(List<PlayerRanking> rankings) {
        return rankings.stream()
                .map(this::mapPlayerRankingToResponse)
                .collect(Collectors.toList());
    }

    private BestPlayerResponse mapPlayerRankingToResponse(PlayerRanking ranking) {
        BestPlayerResponse response = new BestPlayerResponse();
        response.setRank(ranking.getRank());
        response.setId(ranking.getId());
        response.setName(ranking.getName());
        response.setNumber(ranking.getNumber());
        response.setPosition(ranking.getPosition());
        response.setNationality(ranking.getNationality());
        response.setAge(ranking.getAge());
        response.setChampionship(ranking.getChampionship());
        response.setScoredGoals(ranking.getScoredGoals());
        response.setPlayingTime(ranking.getPlayingTime());
        return response;
    }
}