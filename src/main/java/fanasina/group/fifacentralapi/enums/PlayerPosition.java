package fanasina.group.fifacentralapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PlayerPosition {
    DEFENDER("DEFENDER"),
    GOAL_KEEPER("GOALKEEPER"), // Ajoutez ce mapping
    MIDFIELDER("MIDFIELDER"),
    STRIKER("STRIKER");

    private final String value;

    PlayerPosition(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static PlayerPosition fromValue(String value) {
        for (PlayerPosition position : PlayerPosition.values()) {
            if (position.value.equalsIgnoreCase(value)) {
                return position;
            }
        }
        throw new IllegalArgumentException("Unknown position: " + value);
    }
}