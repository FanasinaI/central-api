package fanasina.group.fifacentralapi.dto;

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

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAge() {
        return age;
    }

    public String getClubId() {
        return clubId;
    }

    public String getChampionship() {
        return championship;
    }

    public int getGoals() {
        return goals;
    }

    public int getPlayingTime() {
        return playingTime;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public void setChampionship(String championship) {
        this.championship = championship;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setPlayingTime(int playingTime) {
        this.playingTime = playingTime;
    }
}