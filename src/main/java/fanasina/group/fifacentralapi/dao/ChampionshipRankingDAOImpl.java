package fanasina.group.fifacentralapi.dao;

import fanasina.group.fifacentralapi.database.Datasource;
import fanasina.group.fifacentralapi.entity.ClubRanking;
import fanasina.group.fifacentralapi.enums.Championship;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChampionshipRankingDAOImpl implements ChampionshipRankingDAO {
    private final Datasource datasource;

    public ChampionshipRankingDAOImpl(Datasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<ClubRanking> findAll() {
        String sql = "SELECT cr.id, cr.rank, cr.ranking_points, cr.scored_goals, " +
                "cr.conceded_goals, cr.difference_goals, cr.clean_sheet_number, " +
                "cr.championship_id, c.id as club_id, c.name as club_name, cr.season_year " +
                "FROM club_ranking cr " +
                "JOIN club c ON cr.club_id = c.id";

        List<ClubRanking> rankings = new ArrayList<>();

        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ClubRanking ranking = mapRowToClubRanking(rs);
                rankings.add(ranking);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching club rankings", e);
        }
        return rankings;
    }

    private ClubRanking mapRowToClubRanking(ResultSet rs) throws SQLException {
        ClubRanking ranking = new ClubRanking();
        ranking.setId(rs.getString("id"));
        ranking.setRank(rs.getInt("rank"));
        ranking.setRankingPoints(rs.getInt("ranking_points"));
        ranking.setScoredGoals(rs.getInt("scored_goals"));
        ranking.setConcededGoals(rs.getInt("conceded_goals"));
        ranking.setDifferenceGoals(rs.getInt("difference_goals"));
        ranking.setCleanSheetNumber(rs.getInt("clean_sheet_number"));

        // Conversion du championship_id (int) vers l'enum Championship
        int championshipId = rs.getInt("championship_id");
        ranking.setChampionship(Championship.fromId(championshipId));

        return ranking;
    }
}