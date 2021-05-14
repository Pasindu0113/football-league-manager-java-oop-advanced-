package Clubs;

import java.io.Serializable;
import java.util.Date;

public class Match implements Serializable {
    private FootballClub Club1; //initialising variables needed in order to add a played match to the system
    private FootballClub Club2;
    private int Club1Score;
    private int Club2Score;
    private Date MatchDate;

    public Match(FootballClub club1, FootballClub club2, int club1Score, int club2Score, Date matchDate) {
        Club1 = club1;
        Club2 = club2;
        Club1Score = club1Score;
        Club2Score = club2Score;
        MatchDate = matchDate;
    }

    //creating the getters and the setters of the private variables

    public FootballClub getClub1() {
        return Club1;
    }
    public FootballClub getClub2() {
        return Club2;
    }

    public int getClub1Score() {
        return Club1Score;
    }

    public int getClub2Score() {
        return Club2Score;
    }

    public Date getMatchDate() {
        return MatchDate;
    }

    public void setClub1(FootballClub club1) {
        this.Club1 = club1;
    }

    public void setClub2(FootballClub club2) {
        this.Club2 = club2;
    }

    public void setClub1Score(int club1Score) {
        this.Club1Score = club1Score;
    }

    public void setClub2Score(int club2Score) {
        this.Club2Score = club2Score;
    }

    public void setMatchDate(Date matchDate) {
        this.MatchDate = matchDate;
    }

    @Override
    public String toString() {
        return "Match Details,\n" +
                "\n First Club Name = " + Club1.getClubName() +
                "\n Second Club Name = " + Club2.getClubName() +
                "\n First Club Score = " + Club1Score +
                "\n Second Club Score = " + Club2Score +
                "\n Match Played Date = " + MatchDate;
    }
}
