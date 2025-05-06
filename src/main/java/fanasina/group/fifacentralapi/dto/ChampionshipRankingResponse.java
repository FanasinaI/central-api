package fanasina.group.fifacentralapi.dto;

import fanasina.group.fifacentralapi.enums.Championship;

public class ChampionshipRankingResponse {
    private String clubName;
    private Championship championship;
    private int rank;
    private int rankingPoints;
    private int scoredGoals;
    private int concededGoals;
    private int differenceGoals;
    private int cleanSheetNumber;

    public ChampionshipRankingResponse() {}

    public ChampionshipRankingResponse(String clubName, Championship championship, int rank,
                                       int rankingPoints, int scoredGoals, int concededGoals,
                                       int differenceGoals, int cleanSheetNumber) {
        this.clubName = clubName;
        this.championship = championship;
        this.rank = rank;
        this.rankingPoints = rankingPoints;
        this.scoredGoals = scoredGoals;
        this.concededGoals = concededGoals;
        this.differenceGoals = differenceGoals;
        this.cleanSheetNumber = cleanSheetNumber;
    }

    public String getClubName() { return clubName; }
    public void setClubName(String clubName) { this.clubName = clubName; }

    public Championship getChampionship() { return championship; }
    public void setChampionship(Championship championship) { this.championship = championship; }

    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }

    public int getRankingPoints() { return rankingPoints; }
    public void setRankingPoints(int rankingPoints) { this.rankingPoints = rankingPoints; }

    public int getScoredGoals() { return scoredGoals; }
    public void setScoredGoals(int scoredGoals) { this.scoredGoals = scoredGoals; }

    public int getConcededGoals() { return concededGoals; }
    public void setConcededGoals(int concededGoals) { this.concededGoals = concededGoals; }

    public int getDifferenceGoals() { return differenceGoals; }
    public void setDifferenceGoals(int differenceGoals) { this.differenceGoals = differenceGoals; }

    public int getCleanSheetNumber() { return cleanSheetNumber; }
    public void setCleanSheetNumber(int cleanSheetNumber) { this.cleanSheetNumber = cleanSheetNumber; }
}
