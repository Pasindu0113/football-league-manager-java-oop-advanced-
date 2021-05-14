package Clubs;

public class UniversityFootballClub extends FootballClub{
    private String UniversityName;

    public UniversityFootballClub(String ClubName,String ClubLocation, int NumberOfSeasonsPlayed, String UniversityName){
        super(ClubName,ClubLocation,NumberOfSeasonsPlayed);
        this.UniversityName = UniversityName;
    }

    public String getUniversityName() {
        return UniversityName;
    }

    public void setUniversityName(String universityName) {
        this.UniversityName = universityName;
    }
}
