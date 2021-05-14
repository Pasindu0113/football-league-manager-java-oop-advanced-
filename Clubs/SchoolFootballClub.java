package Clubs;

public class SchoolFootballClub extends FootballClub{
    private String SchoolName;

    public SchoolFootballClub(String ClubName, String ClubLocation, int NumberOfSeasonsPlayed, String SchoolName){
        super(ClubName,ClubLocation,NumberOfSeasonsPlayed);
        this.SchoolName = SchoolName;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        this.SchoolName = schoolName;
    }
}
