package fanasina.group.fifacentralapi.dto;

import fanasina.group.fifacentralapi.enums.PlayerPosition;
import java.util.UUID;

public class PlayerDTO {
    private UUID id;
    private String name;
    private int number;
    private PlayerPosition position;
    private String nationality;
    private int age;
    private ClubDTO club;

    public PlayerDTO() {}

    public PlayerDTO(UUID id, String name, int number, PlayerPosition position,
                     String nationality, int age, ClubDTO club) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.position = position;
        this.nationality = nationality;
        this.age = age;
        this.club = club;
    }

    public PlayerDTO(String name, int number, PlayerPosition position,
                     String nationality, int age, ClubDTO club) {
        this.name = name;
        this.number = number;
        this.position = position;
        this.nationality = nationality;
        this.age = age;
        this.club = club;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

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

    public ClubDTO getClub() { return club; }
    public void setClub(ClubDTO club) { this.club = club; }
}
