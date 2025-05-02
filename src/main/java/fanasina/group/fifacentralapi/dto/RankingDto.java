package fanasina.group.fifacentralapi.dto;
import lombok.Data;

@Data
public class RankingDto {
    private String championship;
    private double medianGoalDifference;
    private int rank;
}
