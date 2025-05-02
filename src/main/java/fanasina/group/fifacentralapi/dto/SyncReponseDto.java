package fanasina.group.fifacentralapi.dto;

import lombok.Data;

@Data
public class SyncReponseDto {
    private String status;
    private int playerCount;
    private int clubCount;
    private long timestamp;
}
