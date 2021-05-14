package Clubs;

import java.io.Serializable;

public class SportsClub implements Serializable {
    private String ClubName;
    private String ClubLocation;
    private int NumberOfSeasonsPlayed;

    public SportsClub(String ClubName, String ClubLocation, int numberOfSeasonsPlayed){
      this.ClubName = ClubName;
      this.ClubLocation = ClubLocation;
      this.NumberOfSeasonsPlayed = numberOfSeasonsPlayed;
    }

    public String getClubName() {
        return ClubName;
    }

    public String getClubLocation() {
        return ClubLocation;
    }

    public int getNumberOfSeasonsPlayed() {
        return NumberOfSeasonsPlayed;
    }

    public void setClubName(String clubName) {
        this.ClubName = clubName;
    }

    public void setClubLocation(String clubLocation) {
        this.ClubLocation = clubLocation;
    }

    public void setNumberOfSeasonsPlayed(int numberOfSeasonsPlayed) {
        NumberOfSeasonsPlayed = numberOfSeasonsPlayed;
    }
}

