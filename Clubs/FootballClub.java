package Clubs;

import java.util.Objects;

public class FootballClub extends SportsClub {
    private int NumberOfWins;
    private int NumberOfDraws;
    private int NumberOfDefeats;
    private int NumberOfGoalsReceived;
    private int NumberOfGoalsScored;
    private int Points;
    private int NumberOfMatchesPlayed;


    public FootballClub(String ClubName, String ClubLocation, int NumberOfSeasonsPlayed ) {
        super(ClubName, ClubLocation, NumberOfSeasonsPlayed);

    }

    public FootballClub(String ClubName, String ClubLocation, int numberOfSeasonsPlayed, int numberOfWins, int numberOfDraws, int numberOfDefeats, int numberOfGoalsReceived, int numberOfGoalsScored, int points, int numberOfMatchesPlayed) {
        super(ClubName, ClubLocation, numberOfSeasonsPlayed);
        NumberOfWins = numberOfWins;
        NumberOfDraws = numberOfDraws;
        NumberOfDefeats = numberOfDefeats;
        NumberOfGoalsReceived = numberOfGoalsReceived;
        NumberOfGoalsScored = numberOfGoalsScored;
        Points = points;
        NumberOfMatchesPlayed = numberOfMatchesPlayed;
    }

    public int getNumberOfWins() {
        return NumberOfWins;
    }

    public int getNumberOfDraws() {
        return NumberOfDraws;
    }

    public int getNumberOfDefeats() {
        return NumberOfDefeats;
    }

    public int getNumberOfGoalsReceived() {
        return NumberOfGoalsReceived;
    }

    public int getNumberOfGoalsScored() {
        return NumberOfGoalsScored;
    }

    public int getPoints() {
        return Points;
    }

    public int getNumberOfMatchesPlayed() {
        return NumberOfMatchesPlayed;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.NumberOfWins = numberOfWins;
    }

    public void setNumberOfDraws(int numberOfDraws) {
        this.NumberOfDraws = numberOfDraws;
    }

    public void setNumberOfDefeats(int numberOfDefeats) {
        this.NumberOfDefeats = numberOfDefeats;
    }

    public void setNumberOfGoalsReceived(int numberOfGoalsReceived) {
        this.NumberOfGoalsReceived = numberOfGoalsReceived;
    }

    public void setNumberOfGoalsScored(int numberOfGoalsScored) {
        this.NumberOfGoalsScored = numberOfGoalsScored;
    }

    public void setPoints(int points) {
        this.Points = points;
    }

    public void setNumberOfMatchesPlayed(int numberOfMatchesPlayed) {
        this.NumberOfMatchesPlayed = numberOfMatchesPlayed;
    }

    @Override
    public String toString() {
        return "" +
                getClubName()  +
                "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballClub club = (FootballClub) o;
        return getClubName().equals(club.getClubName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClubName());
    }
}
