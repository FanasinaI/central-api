package fanasina.group.fifacentralapi.controller;

import fanasina.group.fifacentralapi.dto.PlayerDto;
import fanasina.group.fifacentralapi.service.StatsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final StatsService statsService;

    public PlayerController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/best")
    public List<PlayerDto> getBestPlayers(
            @RequestParam(required = false) Integer top,
            @RequestParam(required = false) String timeUnit) {
        return statsService.getBestPlayers(top, timeUnit);
    }
}