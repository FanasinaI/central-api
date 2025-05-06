package fanasina.group.fifacentralapi.entity;

import fanasina.group.fifacentralapi.enums.DurationUnit;

public class PlayingTime {
    private String id;
    private double value;
    private DurationUnit durationUnit;

    public PlayingTime() {}

    public PlayingTime(String id, double value, DurationUnit durationUnit) {
        this.id = id;
        this.value = value;
        this.durationUnit = durationUnit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public DurationUnit getDurationUnit() {
        return durationUnit;
    }

    public void setDurationUnit(DurationUnit durationUnit) {
        this.durationUnit = durationUnit;
    }
}
