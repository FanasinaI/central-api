package fanasina.group.fifacentralapi.entity;

import fanasina.group.fifacentralapi.enums.DurationUnit;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PlayingTime {
    private String id;
    private double value;
    private DurationUnit durationUnit;
}