package fanasina.group.fifacentralapi.enums;

import java.util.Arrays;

public enum Championship {
    PREMIER_LEAGUE(1),
    LIGUE_1(2),
    BUNDESLIGA(3),
    SERIE_A(4),
    LA_LIGA(5),
    // Ajoutez d'autres championnats au besoin
    ;

    private final int id;

    Championship(int id) {
        this.id = id;
    }

    public static Championship fromId(int championshipId) {
        return Arrays.stream(values())
                .filter(c -> c.id == championshipId)  // Correction ici (c.id au lieu de c.)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Championship ID: " + championshipId));
    }
}