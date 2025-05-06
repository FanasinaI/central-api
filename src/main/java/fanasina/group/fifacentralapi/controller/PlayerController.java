package fanasina.group.fifacentralapi.controller;

import fanasina.group.fifacentralapi.dto.BestPlayerResponse;
import fanasina.group.fifacentralapi.enums.DurationUnit;
import fanasina.group.fifacentralapi.service.PlayerRankingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/central/players")
public class PlayerController {
    private final PlayerRankingService playerService;

    public PlayerController(PlayerRankingService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/best")
    public List<BestPlayerResponse> getBestPlayers(
            @RequestParam(required = false) Integer top,
            @RequestParam(required = false) DurationUnit playingTimeUnit) {
        if (top != null && playingTimeUnit != null) {
            return playerService.getBestPlayersWithFilters(top, playingTimeUnit);
        } else if (top != null) {
            return playerService.getBestPlayersWithTopFilter(top);
        } else if (playingTimeUnit != null) {
            return playerService.getBestPlayersWithPlayingTimeFilter(playingTimeUnit);
        }
        return playerService.getAllBestPlayers();
    }
}