package Clubs;

import java.io.IOException;

public interface LeagueManager {
    void addClub();
    void deleteClub();
    void selectedClubStat();
    void sortedClubStat();
    void addPlayedMatch();
    void saveToFile() throws IOException;
    void saveMatchToFile() throws IOException;
    void loadSavedData();
    void loadSavedMatchesData();



}
