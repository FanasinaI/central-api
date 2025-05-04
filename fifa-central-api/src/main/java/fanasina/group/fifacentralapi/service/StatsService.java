package fanasina.group.fifacentralapi.service;

import fanasina.group.fifacentralapi.dto.PlayerDto;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsService {
    private final SyncService syncService;

    public StatsService(SyncService syncService) {
        this.syncService = syncService;
    }

    public List<PlayerDto> getBestPlayers(Integer top, String timeUnit) {
        List<PlayerDto> players = syncService.getAllPlayers();

        Comparator<PlayerDto> comparator = Comparator.comparingInt(PlayerDto::getGoals);

        if ("MINUTE".equalsIgnoreCase(timeUnit)) {
            comparator = comparator.thenComparingInt(PlayerDto::getPlayingTime);
        }

        players.sort(comparator.reversed());

        return top != null ? players.stream().limit(top).collect(Collectors.toList()) : players;
    }
}