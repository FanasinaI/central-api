package fanasina.group.fifacentralapi.dto;

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

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public String getStadium() {
        return stadium;
    }

    public String getCoachName() {
        return coachName;
    }

    public String getCoachNationality() {
        return coachNationality;
    }

    public String getChampionship() {
        return championship;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public int getCleanSheets() {
        return cleanSheets;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public void setCoachNationality(String coachNationality) {
        this.coachNationality = coachNationality;
    }

    public void setChampionship(String championship) {
        this.championship = championship;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public void setCleanSheets(int cleanSheets) {
        this.cleanSheets = cleanSheets;
    }
}