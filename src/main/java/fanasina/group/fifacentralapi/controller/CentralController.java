package fanasina.group.fifacentralapi.controller;


import fanasina.group.fifacentralapi.dto.BestPlayerResponse;
import fanasina.group.fifacentralapi.dto.ChampionshipRankingResponse;
import fanasina.group.fifacentralapi.enums.DurationUnit;
import fanasina.group.fifacentralapi.service.ChampionshipRankingService;
import fanasina.group.fifacentralapi.service.PlayerRankingService;
import fanasina.group.fifacentralapi.service.SynchronizationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/central")
public class CentralController {

    private final SynchronizationService synchronizationService;
    private final PlayerRankingService playerRankingService;
    private final ChampionshipRankingService championshipRankingService;

    public CentralController(SynchronizationService synchronizationService,
                             PlayerRankingService playerRankingService,
                             ChampionshipRankingService championshipRankingService) {
        this.synchronizationService = synchronizationService;
        this.playerRankingService = playerRankingService;
        this.championshipRankingService = championshipRankingService;
    }

    @PostMapping("/synchronization")
    public void synchronizeData(@RequestHeader("X-API-KEY") String apiKey) {
        synchronizationService.synchronize(apiKey);
    }

    @GetMapping("/bestPlayers")
    public List<BestPlayerResponse> getBestPlayers(
            @RequestParam(required = false) Integer top,
            @RequestParam(required = false) DurationUnit playingTimeUnit) {

        if (top != null && playingTimeUnit != null) {
            return playerRankingService.getBestPlayersWithFilters(top, playingTimeUnit);
        } else if (top != null) {
            return playerRankingService.getBestPlayersWithTopFilter(top);
        } else if (playingTimeUnit != null) {
            return playerRankingService.getBestPlayersWithPlayingTimeFilter(playingTimeUnit);
        } else {
            return playerRankingService.getAllBestPlayers();
        }
    }

    @GetMapping("/championshipRankings")
    public List<ChampionshipRankingResponse> getChampionshipRankings() {
        return championshipRankingService.getAllChampionshipRankings();
    }
}