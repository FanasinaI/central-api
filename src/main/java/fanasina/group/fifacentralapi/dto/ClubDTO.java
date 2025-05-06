package fanasina.group.fifacentralapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubDTO {
    private UUID id;
    private String name;
    private String acronym;  // Non unique (ex: "FCB" pour Barcelone et Bayern)
    private int yearCreation;
    private String stadium;
    private CoachDTO coach;  // Un club a un seul entraîneur
    private String championshipName;  // Nom du championnat (ex: "LA_LIGA")

    // Constructeur pour la création sans ID
    public ClubDTO(String name, String acronym, int yearCreation,
                   String stadium, CoachDTO coach, String championshipName) {
        this.name = name;
        this.acronym = acronym;
        this.yearCreation = yearCreation;
        this.stadium = stadium;
        this.coach = coach;
        this.championshipName = championshipName;
    }
}