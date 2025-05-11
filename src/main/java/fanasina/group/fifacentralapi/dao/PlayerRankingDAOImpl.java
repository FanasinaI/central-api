package fanasina.group.fifacentralapi.dao;

import fanasina.group.fifacentralapi.database.Datasource;
import fanasina.group.fifacentralapi.entity.PlayerRanking;
import fanasina.group.fifacentralapi.entity.PlayingTime;
import fanasina.group.fifacentralapi.enums.Championship;
import fanasina.group.fifacentralapi.enums.DurationUnit;
import fanasina.group.fifacentralapi.enums.PlayerPosition;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerRankingDAOImpl implements PlayerRankingDAO {
    private final Datasource datasource;

    public PlayerRankingDAOImpl(Datasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<PlayerRanking> findAll() {
        String sql = "SELECT pr.id as pr_id, pr.rank, p.id as p_id, pt.id as pt_id, " +
                "p.name, p.number, p.position, p.nationality, p.age, " +
                "pr.championship_id, pr.scored_goals, " +
                "pt.value, pt.duration_unit " +
                "FROM player_ranking pr " +
                "JOIN player p ON pr.player_id = p.id " +
                "JOIN playing_time pt ON pr.playing_time_id = pt.id";
        return executeQuery(sql);
    }

    @Override
    public List<PlayerRanking> findTopPlayers(int top) {
        String sql = "SELECT pr.id as pr_id, pr.rank, p.id as p_id, pt.id as pt_id, " +
                "p.name, p.number, p.position, p.nationality, p.age, " +
                "pr.championship_id, pr.scored_goals, " +
                "pt.value, pt.duration_unit " +
                "FROM player_ranking pr " +
                "JOIN player p ON pr.player_id = p.id " +
                "JOIN playing_time pt ON pr.playing_time_id = pt.id " +
                "ORDER BY pr.rank ASC LIMIT ?";
        return executeQuery(sql, top);
    }

    @Override
    public List<PlayerRanking> findPlayersByPlayingTimeUnit(DurationUnit unit) {
        String sql = "SELECT pr.id as pr_id, pr.rank, p.id as p_id, pt.id as pt_id, " +
                "p.name, p.number, p.position, p.nationality, p.age, " +
                "pr.championship_id, pr.scored_goals, " + // Changé ici
                "pt.value, pt.duration_unit " +
                "FROM player_ranking pr " +
                "JOIN player p ON pr.player_id = p.id " +
                "JOIN playing_time pt ON pr.playing_time_id = pt.id " +
                "WHERE pt.duration_unit = ?";
        return executeQuery(sql, unit.name());
    }

    @Override
    public List<PlayerRanking> findTopPlayersByPlayingTimeUnit(int top, DurationUnit unit) {
        String sql = "SELECT pr.id as pr_id, pr.rank, p.id as p_id, pt.id as pt_id, " +
                "p.name, p.number, p.position, p.nationality, p.age, " +
                "pr.championship_id, pr.scored_goals, " +
                "pt.value, pt.duration_unit " +
                "FROM player_ranking pr " +
                "JOIN player p ON pr.player_id = p.id " +
                "JOIN playing_time pt ON pr.playing_time_id = pt.id " +
                "WHERE pt.duration_unit = ? " +
                "ORDER BY pr.rank ASC LIMIT ?";
        return executeQuery(sql, unit.name(), top);
    }

    private List<PlayerRanking> executeQuery(String sql, Object... params) {
        List<PlayerRanking> rankings = new ArrayList<>();
        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    rankings.add(mapRowToPlayerRanking(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
        return rankings;
    }

    private PlayerRanking mapRowToPlayerRanking(ResultSet rs) throws SQLException {
        PlayerRanking ranking = new PlayerRanking();

        ranking.setId(rs.getString("pr_id"));
        ranking.setRank(rs.getInt("rank"));
        ranking.setName(rs.getString("name"));
        ranking.setNumber(rs.getInt("number"));
        ranking.setPosition(PlayerPosition.valueOf(rs.getString("position")));
        ranking.setNationality(rs.getString("nationality"));
        ranking.setAge(rs.getInt("age"));
        ranking.setChampionship(Championship.fromId(rs.getInt("championship_id")));
        ranking.setScoredGoals(rs.getInt("scored_goals"));

        PlayingTime playingTime = new PlayingTime();
        playingTime.setId(rs.getString("pt_id"));
        playingTime.setValue(rs.getDouble("value"));
        playingTime.setDurationUnit(DurationUnit.valueOf(rs.getString("duration_unit")));
        ranking.setPlayingTime(playingTime);

        return ranking;
    }
}