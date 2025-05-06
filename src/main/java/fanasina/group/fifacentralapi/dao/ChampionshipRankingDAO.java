package fanasina.group.fifacentralapi.dao;

import fanasina.group.fifacentralapi.entity.ClubRanking;
import java.util.List;

public interface ChampionshipRankingDAO {
    List<ClubRanking> findAll();
}