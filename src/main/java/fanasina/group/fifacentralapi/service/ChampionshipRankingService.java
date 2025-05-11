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

        Map<Championship, List<Integer>> differencesByChampionship = rankings.stream()
                .collect(Collectors.groupingBy(
                        ClubRanking::getChampionship,
                        Collectors.mapping(ClubRanking::getDifferenceGoals, Collectors.toList())
                ));
        List<ChampionshipMedianResponse> responses = differencesByChampionship.entrySet().stream()
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
        if (!responses.isEmpty()) {
            responses.get(0).setRank(1);

            for (int i = 1; i < responses.size(); i++) {
                ChampionshipMedianResponse current = responses.get(i);
                ChampionshipMedianResponse previous = responses.get(i-1);

                if (current.getDifferenceGoalsMedian() == previous.getDifferenceGoalsMedian()) {
                    current.setRank(previous.getRank());
                } else {
                    current.setRank(i + 1);
                }
            }
        }

        return responses;
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