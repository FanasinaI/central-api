package fanasina.group.fifacentralapi.dto;

import lombok.Data;

@Data
public class PlayerDto {
    private String id;
    private String name;
    private int number;
    private String position;
    private String nationality;
    private int age;
    private String clubId;
    private String championship;
    private int goals;
    private int playingTime;

    public int getGoals() {
        return this.goals;
    }

    public int getPlayingTime() {
            return this.playingTime;
    }
}