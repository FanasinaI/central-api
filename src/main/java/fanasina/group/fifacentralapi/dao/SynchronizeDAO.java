package fanasina.group.fifacentralapi.dao;

import fanasina.group.fifacentralapi.dto.ClubDTO;
import fanasina.group.fifacentralapi.dto.PlayerDTO;
import fanasina.group.fifacentralapi.entity.ClubRanking;
import fanasina.group.fifacentralapi.entity.PlayerRanking;
import fanasina.group.fifacentralapi.enums.Championship;
import java.util.List;

public interface SynchronizeDAO {
    void saveClubs(List<ClubDTO> clubs);
    void savePlayers(List<PlayerDTO> players);
    void saveClubRankings(List<ClubRanking> rankings, Championship championship, int seasonYear);
    void savePlayerRankings(List<PlayerRanking> rankings, Championship championship, int seasonYear);
}