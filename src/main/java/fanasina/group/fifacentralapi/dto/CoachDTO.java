package fanasina.group.fifacentralapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachDTO {
    private UUID id;
    private String name;
    private String nationality;

    public CoachDTO(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }
}