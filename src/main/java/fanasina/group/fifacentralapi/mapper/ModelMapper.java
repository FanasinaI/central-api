package fanasina.group.fifacentralapi.mapper;

import fanasina.group.fifacentralapi.dto.*;
import fanasina.group.fifacentralapi.entity.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ModelMapper {

    public ClubDTO toClubDTO(Club club) {
        ClubDTO dto = new ClubDTO();
        dto.setId(club.getId());
        dto.setName(club.getName());
        dto.setAcronym(club.getAcronym());
        dto.setYearCreation(club.getYearCreation());
        dto.setStadium(club.getStadium());
        if (club.getCoach() != null) {
            dto.setCoach(toCoachDTO(club.getCoach()));
        }
        return dto;
    }

    public PlayerDTO toPlayerDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(UUID.fromString(player.getId()));
        dto.setName(player.getName());
        dto.setNumber(player.getNumber());
        dto.setPosition(player.getPosition());
        dto.setNationality(player.getNationality());
        dto.setAge(player.getAge());
        if (player.getClub() != null) {
            dto.setClub(toClubDTO(player.getClub()));
        }
        return dto;
    }

}