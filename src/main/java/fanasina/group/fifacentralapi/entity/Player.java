package fanasina.group.fifacentralapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("players")
public class Player {
    @Id private Long id;
    @Column("external_id") private String externalId;
    private String name;
    private Integer number;
    private String position;
    private String nationality;
    private Integer age;
    private Integer goals;
    @Column("playing_time") private Integer playingTime;
    private String championship;
    @Column("club_id") private Long clubId;

    public Long getId() { return id; }
    public String getExternalId() { return externalId; }
    public String getName() { return name; }
    public Integer getNumber() { return number; }
    public String getPosition() { return position; }
    public String getNationality() { return nationality; }
    public Integer getAge() { return age; }
    public Integer getGoals() { return goals; }
    public Integer getPlayingTime() { return playingTime; }
    public String getChampionship() { return championship; }
    public Long getClubId() { return clubId; }

    public void setId(Long id) { this.id = id; }
    public void setExternalId(String externalId) { this.externalId = externalId; }
    public void setName(String name) { this.name = name; }
    public void setNumber(Integer number) { this.number = number; }
    public void setPosition(String position) { this.position = position; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setAge(Integer age) { this.age = age; }
    public void setGoals(Integer goals) { this.goals = goals; }
    public void setPlayingTime(Integer playingTime) { this.playingTime = playingTime; }
    public void setChampionship(String championship) { this.championship = championship; }
    public void setClubId(Long clubId) { this.clubId = clubId; }
}