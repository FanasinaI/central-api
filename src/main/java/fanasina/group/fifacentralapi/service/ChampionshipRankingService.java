package fanasina.group.fifacentralapi.service;


import fanasina.group.fifacentralapi.dao.ChampionshipRankingDAO;
import fanasina.group.fifacentralapi.dto.ChampionshipRankingResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampionshipRankingService {
    private final ChampionshipRankingDAO championshipRankingDAO;

    public ChampionshipRankingService(ChampionshipRankingDAO championshipRankingDAO) {
        this.championshipRankingDAO = championshipRankingDAO;
    }

    public List<ChampionshipRankingResponse> getAllChampionshipRankings() {
        return championshipRankingDAO.findAll()
                .stream()
                .map(club -> new ChampionshipRankingResponse(
                        club.getClub().getName(),
                        club.getChampionship(),
                        club.getRank(),
                        club.getRankingPoints(),
                        club.getScoredGoals(),
                        club.getConcededGoals(),
                        club.getDifferenceGoals(),
                        club.getCleanSheetNumber()))
                .collect(Collectors.toList());
    }
}