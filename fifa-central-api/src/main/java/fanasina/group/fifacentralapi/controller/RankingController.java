package fanasina.group.fifacentralapi.controller;

import fanasina.group.fifacentralapi.dto.ChampionshipRanking;
import fanasina.group.fifacentralapi.service.ChampionshipRankingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rankings")
public class RankingController {
    private final ChampionshipRankingService rankingService;

    public RankingController(ChampionshipRankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/championships")
    public List<ChampionshipRanking> getChampionshipRankings() {
        return rankingService.calculateRankings();
    }
}