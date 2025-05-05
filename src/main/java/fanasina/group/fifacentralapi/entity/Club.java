package fanasina.group.fifacentralapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("clubs")
public class Club {
    @Id private Long id;
    @Column("external_id") private String externalId;
    private String name;
    private String acronym;
    @Column("creation_year") private Integer creationYear;
    private String stadium;
    @Column("coach_name") private String coachName;
    @Column("coach_nationality") private String coachNationality;
    private Integer points;
    @Column("goals_scored") private Integer goalsScored;
    @Column("goals_conceded") private Integer goalsConceded;
    @Column("clean_sheets") private Integer cleanSheets;
    private String championship;

    public Long getId() { return id; }
    public String getExternalId() { return externalId; }
    public String getName() { return name; }
    public String getAcronym() { return acronym; }
    public Integer getCreationYear() { return creationYear; }
    public String getStadium() { return stadium; }
    public String getCoachName() { return coachName; }
    public String getCoachNationality() { return coachNationality; }
    public Integer getPoints() { return points; }
    public Integer getGoalsScored() { return goalsScored; }
    public Integer getGoalsConceded() { return goalsConceded; }
    public Integer getCleanSheets() { return cleanSheets; }
    public String getChampionship() { return championship; }


    public void setId(Long id) { this.id = id; }
    public void setExternalId(String externalId) { this.externalId = externalId; }
    public void setName(String name) { this.name = name; }
    public void setAcronym(String acronym) { this.acronym = acronym; }
    public void setCreationYear(Integer creationYear) { this.creationYear = creationYear; }
    public void setStadium(String stadium) { this.stadium = stadium; }
    public void setCoachName(String coachName) { this.coachName = coachName; }
    public void setCoachNationality(String coachNationality) { this.coachNationality = coachNationality; }
    public void setPoints(Integer points) { this.points = points; }
    public void setGoalsScored(Integer goalsScored) { this.goalsScored = goalsScored; }
    public void setGoalsConceded(Integer goalsConceded) { this.goalsConceded = goalsConceded; }
    public void setCleanSheets(Integer cleanSheets) { this.cleanSheets = cleanSheets; }
    public void setChampionship(String championship) { this.championship = championship; }
}