package fanasina.group.fifacentralapi.repository;

import fanasina.group.fifacentralapi.entity.Player;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    @Query("SELECT * FROM players WHERE championship = :champ")
    List<Player> findByChampionship(@Param("champ") String championship);

    @Query("SELECT * FROM players WHERE goals > :minGoals ORDER BY goals DESC")
    List<Player> findTopScorers(@Param("minGoals") int minGoals);
}