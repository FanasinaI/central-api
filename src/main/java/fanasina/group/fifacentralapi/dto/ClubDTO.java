package fanasina.group.fifacentralapi.dto;

import java.util.UUID;

public class ClubDTO {
    private String id;
    private String name;
    private String acronym;
    private int yearCreation;
    private String stadium;
    private CoachDTO coach;
    private String championshipName;

    public ClubDTO() {}

    public ClubDTO(String id, String name, String acronym, int yearCreation,
                   String stadium, CoachDTO coach, String championshipName) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.yearCreation = yearCreation;
        this.stadium = stadium;
        this.coach = coach;
        this.championshipName = championshipName;
    }

    public ClubDTO(String name, String acronym, int yearCreation,
                   String stadium, CoachDTO coach, String championshipName) {
        this.name = name;
        this.acronym = acronym;
        this.yearCreation = yearCreation;
        this.stadium = stadium;
        this.coach = coach;
        this.championshipName = championshipName;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAcronym() { return acronym; }
    public void setAcronym(String acronym) { this.acronym = acronym; }

    public int getYearCreation() { return yearCreation; }
    public void setYearCreation(int yearCreation) { this.yearCreation = yearCreation; }

    public String getStadium() { return stadium; }
    public void setStadium(String stadium) { this.stadium = stadium; }

    public CoachDTO getCoach() { return coach; }
    public void setCoach(CoachDTO coach) { this.coach = coach; }

    public String getChampionshipName() { return championshipName; }
    public void setChampionshipName(String championshipName) { this.championshipName = championshipName; }
}
