package fanasina.group.fifacentralapi.dto;

import fanasina.group.fifacentralapi.enums.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private UUID id;
    private String name;
    private int number;  // Unique au sein du club
    private PlayerPosition position;
    private String nationality;
    private int age;
    private ClubDTO club;  // Référence au club

    // Constructeur pour la création sans ID
    public PlayerDTO(String name, int number, PlayerPosition position,
                     String nationality, int age, ClubDTO club) {
        this.name = name;
        this.number = number;
        this.position = position;
        this.nationality = nationality;
        this.age = age;
        this.club = club;
    }
}