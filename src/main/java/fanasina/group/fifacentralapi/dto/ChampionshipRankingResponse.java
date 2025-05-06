package fanasina.group.fifacentralapi.dto;

import fanasina.group.fifacentralapi.enums.Championship;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChampionshipRankingResponse {
    private final String clubName;
    private final Championship championship;
    private final int rank;
    private final int rankingPoints;
    private final int scoredGoals;
    private final int concededGoals;
    private final int differenceGoals;
    private final int cleanSheetNumber;
}