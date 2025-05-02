package fanasina.group.fifacentralapi.dto;

import lombok.Data;

@Data
public class ClubDto {
    private String id;
    private String name;
    private String acronym;
    private int creationYear;
    private String stadium;
    private String coachName;
    private String coachNationality;
    private String championship;
    private int points;
    private int goalsScored;
    private int goalsConceded;
    private int cleanSheets;

    public int getPoints() {
        return points;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

}