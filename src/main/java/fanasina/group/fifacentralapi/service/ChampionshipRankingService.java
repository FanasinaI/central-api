package fanasina.group.fifacentralapi.service;

import fanasina.group.fifacentralapi.dao.ChampionshipRankingDAO;
import fanasina.group.fifacentralapi.dto.ChampionshipMedianResponse;
import fanasina.group.fifacentralapi.dto.ChampionshipRankingResponse;
import fanasina.group.fifacentralapi.entity.ClubRanking;
import fanasina.group.fifacentralapi.enums.Championship;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChampionshipRankingService {
    private final ChampionshipRankingDAO rankingDAO;

    public ChampionshipRankingService(ChampionshipRankingDAO rankingDAO) {
        this.rankingDAO = rankingDAO;
    }

    public List<ChampionshipMedianResponse> getChampionshipsByDifferenceGoalsMedian() {
        List<ClubRanking> rankings = rankingDAO.findAll();

        // Grouper les différences de buts par championnat
        Map<Championship, List<Integer>> differencesByChampionship = rankings.stream()
                .collect(Collectors.groupingBy(
                        ClubRanking::getChampionship,
                        Collectors.mapping(ClubRanking::getDifferenceGoals, Collectors.toList())
                ));

        // Calculer la médiane pour chaque championnat
        List<ChampionshipMedianResponse> response = differencesByChampionship.entrySet().stream()
                .map(entry -> {
                    List<Integer> differences = entry.getValue();
                    Collections.sort(differences);

                    double median;
                    int size = differences.size();
                    if (size % 2 == 0) {
                        median = (differences.get(size/2 - 1) + differences.get(size/2)) / 2.0;
                    } else {
                        median = differences.get(size/2);
                    }

                    return new ChampionshipMedianResponse(entry.getKey(), median);
                })
                .sorted(Comparator.comparingDouble(ChampionshipMedianResponse::getDifferenceGoalsMedian))
                .collect(Collectors.toList());

        // Ajouter le rang (0 étant le meilleur)
        for (int i = 0; i < response.size(); i++) {
            response.get(i).setRank(i);
        }

        return response;
    }

    public List<ChampionshipRankingResponse> getAllChampionshipRankings() {
        return rankingDAO.findAll().stream()
                .map(this::mapToRankingResponse)
                .collect(Collectors.toList());
    }

    private ChampionshipRankingResponse mapToRankingResponse(ClubRanking ranking) {
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