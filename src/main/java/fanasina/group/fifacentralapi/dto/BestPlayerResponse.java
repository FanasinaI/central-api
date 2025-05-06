package fanasina.group.fifacentralapi.dto;

import fanasina.group.fifacentralapi.entity.PlayingTime;
import fanasina.group.fifacentralapi.enums.Championship;
import fanasina.group.fifacentralapi.enums.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BestPlayerResponse {
    private final String name;
    private final int number;
    private final PlayerPosition position;
    private final String nationality;
    private final int age;
    private final Championship championship;
    private final int scoredGoals;
    private final PlayingTime playingTime;
}