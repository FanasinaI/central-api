package fanasina.group.fifacentralapi.dao;

import fanasina.group.fifacentralapi.dto.ClubDTO;
import fanasina.group.fifacentralapi.dto.CoachDTO;
import fanasina.group.fifacentralapi.dto.PlayerDTO;
import fanasina.group.fifacentralapi.entity.ClubRanking;
import fanasina.group.fifacentralapi.entity.PlayerRanking;
import fanasina.group.fifacentralapi.entity.PlayingTime;
import fanasina.group.fifacentralapi.enums.Championship;
import fanasina.group.fifacentralapi.database.Datasource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.UUID;

@Repository
public class SynchronizerDAOImpl implements SynchronizeDAO {
    private final Datasource datasource;

    public SynchronizerDAOImpl(Datasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public void saveClubs(List<ClubDTO> clubs) {
        String insertCoachSql = "INSERT INTO coach (id, name, nationality) VALUES (?, ?, ?) " +
                "ON CONFLICT (id) DO NOTHING";

        String insertClubSql = "INSERT INTO club (id, name, acronym, year_creation, stadium, coach_id) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (id) DO NOTHING";

        try (Connection conn = datasource.getConnection();
             PreparedStatement coachStmt = conn.prepareStatement(insertCoachSql);
             PreparedStatement clubStmt = conn.prepareStatement(insertClubSql)) {

            conn.setAutoCommit(false);

            for (ClubDTO club : clubs) {
                if (club.getId() == null || club.getId().isEmpty()) {
                    throw new IllegalArgumentException("Club ID cannot be null or empty");
                }

                CoachDTO coach = club.getCoach();
                if (coach != null) {
                    String coachId = coach.getId() != null && !coach.getId().isEmpty() ?
                            coach.getId() :
                            UUID.randomUUID().toString();

                    coachStmt.setString(1, coachId);
                    coachStmt.setString(2, coach.getName());
                    coachStmt.setString(3, coach.getNationality());
                    coachStmt.addBatch();

                    coach.setId(coachId);
                }

                clubStmt.setString(1, club.getId());
                clubStmt.setString(2, club.getName());
                clubStmt.setString(3, club.getAcronym());
                clubStmt.setInt(4, club.getYearCreation());
                clubStmt.setString(5, club.getStadium());
                clubStmt.setString(6, coach != null ? coach.getId() : null);
                clubStmt.addBatch();
            }

            coachStmt.executeBatch();
            clubStmt.executeBatch();

            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde des clubs", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Données invalides: " + e.getMessage(), e);
        }
    }

    @Override
    public void savePlayers(List<PlayerDTO> players) {
        String sql = "INSERT INTO player (id, name, number, position, nationality, age, club_id) " +
                "VALUES (?, ?, ?, ?::player_position, ?, ?, ?) " +
                "ON CONFLICT (id) DO NOTHING";

        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            for (PlayerDTO player : players) {
                if (player.getId() == null || player.getId().isEmpty()) {
                    player.setId(UUID.randomUUID().toString());
                }

                stmt.setString(1, player.getId());
                stmt.setString(2, player.getName());
                stmt.setInt(3, player.getNumber());

                stmt.setString(4, player.getPosition().name());

                stmt.setString(5, player.getNationality());
                stmt.setInt(6, player.getAge());
                stmt.setString(7, player.getClub() != null ? player.getClub().getId() : null);
                stmt.addBatch();
            }

            stmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde des joueurs", e);
        }
    }

    @Override
    public void saveClubRankings(List<ClubRanking> clubRankings, Championship championship, int seasonYear) {
        String sql = "INSERT INTO club_ranking (" +
                "id, championship_id, club_id, rank, ranking_points, " +
                "scored_goals, conceded_goals, difference_goals, " +
                "clean_sheet_number, season_year) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (club_id, championship_id, season_year) " +
                "DO UPDATE SET " +
                "rank = EXCLUDED.rank, " +
                "ranking_points = EXCLUDED.ranking_points, " +
                "scored_goals = EXCLUDED.scored_goals, " +
                "conceded_goals = EXCLUDED.conceded_goals, " +
                "difference_goals = EXCLUDED.difference_goals, " +
                "clean_sheet_number = EXCLUDED.clean_sheet_number";

        try (Connection conn = datasource.getConnection()) {
            conn.setAutoCommit(false);
            int championshipId = getChampionshipIdByEnum(conn, championship);
            if (false) {
                throw new RuntimeException("Championnat introuvable: " + championship);
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                for (ClubRanking cr : clubRankings) {
                    stmt.clearParameters();
                    stmt.setString(1, UUID.randomUUID().toString());
                    stmt.setInt(2, championshipId);
                    stmt.setString(3, cr.getClub().getId());

                    stmt.setInt(4, cr.getRank());
                    stmt.setInt(5, cr.getRankingPoints());
                    stmt.setInt(6, cr.getScoredGoals());
                    stmt.setInt(7, cr.getConcededGoals());
                    stmt.setInt(8, cr.getDifferenceGoals());
                    stmt.setInt(9, cr.getCleanSheetNumber());
                    stmt.setInt(10, seasonYear);

                    stmt.addBatch();
                }

                stmt.executeBatch();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Erreur lors de la sauvegarde des classements clubs", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
    }

    @Override
    public void savePlayerRankings(List<PlayerRanking> rankings, Championship championship, int seasonYear) {
        String sql = "INSERT INTO player_ranking (" +
                "id, player_id, rank, championship_id, scored_goals, " +
                "playing_time_id, season_year) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (player_id, championship_id, season_year) " +
                "DO UPDATE SET " +
                "rank = EXCLUDED.rank, " +
                "scored_goals = EXCLUDED.scored_goals, " +
                "playing_time_id = EXCLUDED.playing_time_id";

        try (Connection conn = datasource.getConnection()) {
            conn.setAutoCommit(false);

            int championshipId = getChampionshipIdByEnum(conn, championship);
            if (championshipId == 0) {
                throw new RuntimeException("Championnat introuvable: " + championship);
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                for (PlayerRanking pr : rankings) {
                    if (pr == null || pr.getId() == null) {
                        continue;
                    }

                    Integer playingTimeId = getValidPlayingTimeId(conn, pr.getPlayingTime());
                    if (playingTimeId == null) {
                        continue;
                    }

                    if (!playerExists(conn, pr.getId())) {
                        continue;
                    }

                    stmt.setString(1, UUID.randomUUID().toString());
                    stmt.setString(2, pr.getId());
                    stmt.setInt(3, pr.getRank());
                    stmt.setInt(4, championshipId);
                    stmt.setInt(5, pr.getScoredGoals());
                    stmt.setInt(6, playingTimeId);
                    stmt.setInt(7, seasonYear);

                    stmt.addBatch();
                }

                stmt.executeBatch();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Erreur SQL lors de la sauvegarde", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base", e);
        }
    }

    private Integer getValidPlayingTimeId(Connection conn, PlayingTime playingTime) throws SQLException {
        if (playingTime == null || playingTime.getId() == null) {
            return null;
        }

        try {
            int id = Integer.parseInt(playingTime.getId());
            String query = "SELECT id FROM playing_time WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next() ? id : null;
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean playerExists(Connection conn, String playerId) throws SQLException {
        String query = "SELECT id FROM player WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, playerId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    private int getChampionshipIdByEnum(Connection conn, Championship championship) throws SQLException {
        String query = "SELECT id FROM championship WHERE name = ?::championship_name";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, championship.name());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return 0;
    }}