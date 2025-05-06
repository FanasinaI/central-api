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
        String sql = "SELECT * FROM player_ranking pr " +
                "JOIN player p ON pr.player_id = p.id " +
                "JOIN playing_time pt ON pr.playing_time_id = pt.id " +
                "ORDER BY pr.rank ASC";
        return executeQuery(sql);
    }

    @Override
    public List<PlayerRanking> findTopPlayers(int top) {
        String sql = "SELECT * FROM player_ranking pr " +
                "JOIN player p ON pr.player_id = p.id " +
                "JOIN playing_time pt ON pr.playing_time_id = pt.id " +
                "ORDER BY pr.rank ASC LIMIT ?";
        return executeQueryWithTop(sql, top);
    }

    @Override
    public List<PlayerRanking> findPlayersByPlayingTimeUnit(DurationUnit unit) {
        String sql = "SELECT * FROM player_ranking pr " +
                "JOIN player p ON pr.player_id = p.id " +
                "JOIN playing_time pt ON pr.playing_time_id = pt.id " +
                "WHERE pt.duration_unit = ?::duration_unit " +
                "ORDER BY pr.rank ASC";
        return executeQueryWithUnit(sql, unit);
    }

    @Override
    public List<PlayerRanking> findTopPlayersByPlayingTimeUnit(int top, DurationUnit unit) {
        String sql = "SELECT * FROM player_ranking pr " +
                "JOIN player p ON pr.player_id = p.id " +
                "JOIN playing_time pt ON pr.playing_time_id = pt.id " +
                "WHERE pt.duration_unit = ?::duration_unit " +
                "ORDER BY pr.rank ASC LIMIT ?";
        return executeQueryWithTopAndUnit(sql, top, unit);
    }

    private List<PlayerRanking> executeQuery(String sql) {
        List<PlayerRanking> players = new ArrayList<>();
        try (Connection conn = datasource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                players.add(mapRowToPlayerRanking(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des joueurs", e);
        }
        return players;
    }

    private List<PlayerRanking> executeQueryWithTop(String sql, int top) {
        List<PlayerRanking> players = new ArrayList<>();
        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, top);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    players.add(mapRowToPlayerRanking(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des meilleurs joueurs", e);
        }
        return players;
    }

    private PlayerRanking mapRowToPlayerRanking(ResultSet rs) throws SQLException {
        PlayerRanking player = new PlayerRanking();
        player.setId(rs.getString("id"));
        player.setName(rs.getString("name"));
        player.setNumber(rs.getInt("number"));
        player.setPosition(PlayerPosition.valueOf(rs.getString("position")));
        player.setNationality(rs.getString("nationality"));
        player.setAge(rs.getInt("age"));
        player.setChampionship(Championship.valueOf(rs.getString("championship")));
        player.setScoredGoals(rs.getInt("scored_goals"));

        PlayingTime playingTime = new PlayingTime();
        playingTime.setValue(rs.getDouble("value"));
        playingTime.setDurationUnit(DurationUnit.valueOf(rs.getString("duration_unit")));
        player.setPlayingTime(playingTime);

        return player;
    }
}