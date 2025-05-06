package fanasina.group.fifacentralapi.service;

import fanasina.group.fifacentralapi.dao.ChampionshipRankingDAO;
import fanasina.group.fifacentralapi.dto.ChampionshipRankingResponse;
import fanasina.group.fifacentralapi.entity.ClubRanking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampionshipRankingService {
    private final ChampionshipRankingDAO rankingDAO;

    public ChampionshipRankingService(ChampionshipRankingDAO rankingDAO) {
        this.rankingDAO = rankingDAO;
    }

    public List<ChampionshipRankingResponse> getAllChampionshipRankings() {
        return rankingDAO.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ChampionshipRankingResponse mapToResponse(ClubRanking ranking) {
        ChampionshipRankingResponse response = new ChampionshipRankingResponse();
        response.setClubName(ranking.getClub().getName());
        response.setChampionship(ranking.getChampionship());
        response.setRank(ranking.getRank());
        response.setRankingPoints(ranking.getRankingPoints());
        response.setScoredGoals(ranking.getScoredGoals());
        response.setConcededGoals(ranking.getConcededGoals());
        response.setDifferenceGoals(ranking.getDifferenceGoals());
        response.setCleanSheetNumber(ranking.getCleanSheetNumber());
        return response;
    }
}