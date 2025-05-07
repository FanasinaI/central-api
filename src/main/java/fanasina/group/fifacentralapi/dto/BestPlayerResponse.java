package fanasina.group.fifacentralapi.dto;

import fanasina.group.fifacentralapi.entity.PlayingTime;
import fanasina.group.fifacentralapi.enums.Championship;
import fanasina.group.fifacentralapi.enums.PlayerPosition;

public class BestPlayerResponse {
    private int rank;
    private String id;
    private String name;
    private int number;
    private PlayerPosition position;
    private String nationality;
    private int age;
    private Championship championship;
    private int scoredGoals;
    private PlayingTime playingTime;

    public BestPlayerResponse() {}

    public BestPlayerResponse(int rank,String id,String name, int number, PlayerPosition position, String nationality,
                              int age, Championship championship, int scoredGoals, PlayingTime playingTime) {
        this.rank = rank;
        this.id = id;
        this.name = name;
        this.number = number;
        this.position = position;
        this.nationality = nationality;
        this.age = age;
        this.championship = championship;
        this.scoredGoals = scoredGoals;
        this.playingTime = playingTime;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public PlayerPosition getPosition() { return position; }
    public void setPosition(PlayerPosition position) { this.position = position; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Championship getChampionship() { return championship; }
    public void setChampionship(Championship championship) { this.championship = championship; }

    public int getScoredGoals() { return scoredGoals; }
    public void setScoredGoals(int scoredGoals) { this.scoredGoals = scoredGoals; }

    public PlayingTime getPlayingTime() { return playingTime; }
    public void setPlayingTime(PlayingTime playingTime) { this.playingTime = playingTime; }
}
