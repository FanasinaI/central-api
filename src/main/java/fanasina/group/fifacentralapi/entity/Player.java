package fanasina.group.fifacentralapi.entity;

import lombok.*;
import org.springframework.data.relational.core.dialect.LimitClause;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Player {
    private String id = UUID.randomUUID().toString();
    private String name;
    private int number;
    private LimitClause.Position position;
    private String nationality;
    private int age;
    private String clubId;
    private Club club;
}
