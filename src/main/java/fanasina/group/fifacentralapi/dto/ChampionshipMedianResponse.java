package fanasina.group.fifacentralapi.dto;

import fanasina.group.fifacentralapi.enums.Championship;

public class ChampionshipMedianResponse {
    private int rank;
    private Championship championship;
    private double differenceGoalsMedian;

    public ChampionshipMedianResponse() {}

    public ChampionshipMedianResponse(Championship championship, double differenceGoalsMedian) {
        this.championship = championship;
        this.differenceGoalsMedian = differenceGoalsMedian;
    }

    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }

    public Championship getChampionship() { return championship; }
    public void setChampionship(Championship championship) { this.championship = championship; }

    public double getDifferenceGoalsMedian() { return differenceGoalsMedian; }
    public void setDifferenceGoalsMedian(double differenceGoalsMedian) {
        this.differenceGoalsMedian = differenceGoalsMedian;
    }
}