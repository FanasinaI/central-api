package fanasina.group.fifacentralapi.entity;

import fanasina.group.fifacentralapi.dto.ClubDTO;
import lombok.Data;

@Data
public class ClubRanking {
    private String id;
    private ClubDTO club;
    private int rank;
    private int rankingPoints;
    private int scoredGoals;
    private int concededGoals;
    private int differenceGoals;
    private int cleanSheetNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClubDTO getClub() {
        return club;
    }

    public void setClub(ClubDTO club) {
        this.club = club;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRankingPoints() {
        return rankingPoints;
    }

    public void setRankingPoints(int rankingPoints) {
        this.rankingPoints = rankingPoints;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getConcededGoals() {
        return concededGoals;
    }

    public void setConcededGoals(int concededGoals) {
        this.concededGoals = concededGoals;
    }

    public int getDifferenceGoals() {
        return differenceGoals;
    }

    public void setDifferenceGoals(int differenceGoals) {
        this.differenceGoals = differenceGoals;
    }

    public int getCleanSheetNumber() {
        return cleanSheetNumber;
    }

    public void setCleanSheetNumber(int cleanSheetNumber) {
        this.cleanSheetNumber = cleanSheetNumber;
    }
}
