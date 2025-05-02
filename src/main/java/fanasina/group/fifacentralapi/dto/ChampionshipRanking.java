package fanasina.group.fifacentralapi.dto;

import lombok.Data;

@Data
public class ChampionshipRanking {
    private String championshipName;
    private double averageGoals;
    private int totalPoints;
    private int rank;

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getAverageGoals() {
        return this.averageGoals;
    }

    public void setChampionshipName(String championshipName) {
        this.championshipName = championshipName;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void setAverageGoals(double averageGoals) {
        this.averageGoals = averageGoals;
    }
}