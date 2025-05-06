package fanasina.group.fifacentralapi.controller;

import fanasina.group.fifacentralapi.dto.ChampionshipRankingResponse;
import fanasina.group.fifacentralapi.service.ChampionshipRankingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/central/championships")
public class ChampionshipController {
    private final ChampionshipRankingService rankingService;

    public ChampionshipController(ChampionshipRankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/rankings")
    public List<ChampionshipRankingResponse> getRankings() {
        return rankingService.getAllChampionshipRankings();
    }
}