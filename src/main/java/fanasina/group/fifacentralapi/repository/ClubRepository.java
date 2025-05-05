package fanasina.group.fifacentralapi.repository;

import fanasina.group.fifacentralapi.entity.Club;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends CrudRepository<Club, Long> {

    @Query("SELECT * FROM clubs WHERE championship = :champ ORDER BY points DESC")
    List<Club> findByChampionshipOrdered(@Param("champ") String championship);

    @Query("SELECT * FROM clubs ORDER BY (goals_scored - goals_conceded) DESC")
    List<Club> findAllByGoalDifference();
}