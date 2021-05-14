package Clubs;

public class SortingAlgo {
    public static void bubbleSort(FootballClub[] clubsListArray) {
        for (int m = 0; m < clubsListArray.length - 1; m++) {
            for (int n = 0; n < clubsListArray.length - (m + 1); n++) {
                if (clubsListArray[n].getPoints() < (clubsListArray[n + 1].getPoints())) {
                    FootballClub temp = clubsListArray[n];
                    clubsListArray[n] = clubsListArray[n + 1];
                    clubsListArray[n + 1] = temp;
                } else if (clubsListArray[n].getPoints() == (clubsListArray[n + 1].getPoints())) {
                    if (clubsListArray[n].getNumberOfGoalsScored() < (clubsListArray[n + 1].getNumberOfGoalsScored())) {
                        FootballClub temp = clubsListArray[n];
                        clubsListArray[n] = clubsListArray[n + 1];
                        clubsListArray[n + 1] = temp;
                    }
                }
            }
        }
    }
}
