package fanasina.group.fifacentralapi.dao;

import fanasina.group.fifacentralapi.entity.PlayerRanking;
import fanasina.group.fifacentralapi.enums.DurationUnit;
import java.util.List;

public interface PlayerRankingDAO {
    List<PlayerRanking> findAll();
    List<PlayerRanking> findTopPlayers(int top);
    List<PlayerRanking> findPlayersByPlayingTimeUnit(DurationUnit unit);
    List<PlayerRanking> findTopPlayersByPlayingTimeUnit(int top, DurationUnit unit);
}