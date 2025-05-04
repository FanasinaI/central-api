package fanasina.group.fifacentralapi.service;

import fanasina.group.fifacentralapi.dto.ChampionshipRanking;
import fanasina.group.fifacentralapi.dto.ClubDto;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@Service
public class ChampionshipRankingService {
    private final SyncService syncService;

    public ChampionshipRankingService(SyncService syncService) {
        this.syncService = syncService;
    }

    public List<ChampionshipRanking> calculateRankings() {
        Map<String, List<ClubDto>> clubsByChamp = syncService.getClubsByChampionship();
        List<ChampionshipRanking> rankings = new ArrayList<>();

        clubsByChamp.forEach((champName, clubs) -> {
            double avgGoals = clubs.stream()
                    .mapToInt(club -> club.getGoalsScored())
                    .average()
                    .orElse(0.0);

            int totalPoints = clubs.stream()
                    .mapToInt(club -> club.getPoints())
                    .sum();

            ChampionshipRanking ranking = new ChampionshipRanking();
            ranking.setChampionshipName(champName);
            ranking.setAverageGoals(avgGoals);
            ranking.setTotalPoints(totalPoints);

            rankings.add(ranking);
        });

        rankings.sort((r1, r2) -> Double.compare(r2.getAverageGoals(), r1.getAverageGoals()));

        for (int i = 0; i < rankings.size(); i++) {
            rankings.get(i).setRank(i + 1);
        }

        return rankings;
    }
}