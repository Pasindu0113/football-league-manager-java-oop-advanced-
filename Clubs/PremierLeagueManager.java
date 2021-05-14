package Clubs;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PremierLeagueManager implements LeagueManager { //implementing the interface to the class

    public ArrayList<FootballClub> clubsList = new ArrayList<>(); //creating two arraylists in order to store the clubs as well as the matches they're playing
    public ArrayList<Match> playedMatches = new ArrayList<>();

    @Override
    public void addClub() { //method to add a club to the system
        Scanner sc = new Scanner(System.in).useDelimiter("\n"); //getting all the information needed to create a club form the user
        System.out.println("\n--------- Add Club ---------\n");
        System.out.print("Enter the Name of the Club : ");
        String clubName = sc.next();
        System.out.print("Enter the Location of the Club: ");
        String clubLocation = sc.next();
        System.out.print("Enter the Number of seasons played: ");
        while (!sc.hasNextInt()) { //validating the user's input because the number of seasons played can only be an integer value
            sc.next();
            System.out.print("Only integers are allowed, please try again : ");
        }
        int noSeasonsPlayed = sc.nextInt();

        while (true) {
            System.out.println("Enter they Type of the Club,\na. Football Club\nb. University Football Club\nc. School Football Club"); //getting which type of club is getting entered from the user
            String clubType = sc.next();

            if (clubType.equalsIgnoreCase("a")) {
                FootballClub club = new FootballClub(clubName, clubLocation,noSeasonsPlayed); //passing the parameters to the FootballClub class constructor
                club.setClubName(clubName);
                club.setClubLocation(clubLocation);
                club.setNumberOfSeasonsPlayed(noSeasonsPlayed);
                if(clubsList.contains(club)){
                    System.out.println("This club is already in the List"); //checking if the user is trying to add a club that is already in the system and validating it
                }
                else{
                    clubsList.add(club); //adding the club to the system using the previously created arraylist
                    System.out.println("\nClub added Successfully\n");
                }
                break;
            } else if (clubType.equalsIgnoreCase("b")) {
                System.out.print("Enter the Name of the University : ");
                String uniName = sc.next();
                UniversityFootballClub club = new UniversityFootballClub(clubName, clubLocation,noSeasonsPlayed, uniName); //passing the parameters to the UniversityFootballClub class constructor
                club.setClubName(clubName);
                club.setClubLocation(clubLocation);
                club.setNumberOfSeasonsPlayed(noSeasonsPlayed);
                club.setUniversityName(uniName);
                if(clubsList.contains(club)){
                    System.out.println("This club is already in the List"); //checking if the user is trying to add a club that is already in the system and validating it
                }
                else{
                    clubsList.add(club); //adding the club to the system using the previously created array list
                    System.out.println("\nClub added Successfully\n");
                }
                break;
            } else if (clubType.equalsIgnoreCase("c")) {
                System.out.print("Enter the Name of the School : ");
                String sclName = sc.next();
                SchoolFootballClub club = new SchoolFootballClub(clubName, clubLocation, noSeasonsPlayed, sclName); //passing the parameters to the SchoolFootballClub class constructor
                club.setClubName(clubName);
                club.setClubLocation(clubLocation);
                club.setNumberOfSeasonsPlayed(noSeasonsPlayed);
                club.setSchoolName(sclName);
                if(clubsList.contains(club)){
                    System.out.println("This club is already in the List"); //checking if the user is trying to add a club that is already in the system and validating it
                }
                else{
                    clubsList.add(club); //adding the club to the system using the previously created array list
                    System.out.println("\nClub added Successfully\n");
                }
                break;
            } else {
                System.out.println("Invalid Input, please reenter"); //there are only three types of clubs therefore validating if the user tries to enter any other option than the three options already given

            }

        }

    }
    @Override
    public void deleteClub() { //method to delete a club from the system
        Scanner sc1 = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Enter the name of the club you want deleted : "); //getting the name of the club that the user wants deleted
        String delClub = sc1.next();
        boolean bln = false;
        for (FootballClub club : clubsList) { //loop through the clubsList ArrayList to find the club that the user wants out of the system
            if (club.getClubName().equalsIgnoreCase(delClub)) {
                bln = true;
                clubsList.remove(club); //removing the club from the system
                System.out.println("The club " + delClub + " was deleted from the system");
                break;
            }
        }
        if (!bln) {
            System.out.println("The club " + delClub + " is not found in the system"); //validating if the club that the user is trying to remove doesn't exist in the clubsList ArrayList
        }
    }

    @Override
    public void selectedClubStat() { //method to get the statistics of a selected club
        Scanner sc2 = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Enter the club Name you want to see the statistics of : ");
        String selectedClub = sc2.next();
        boolean bln1 = false;
        if (clubsList.size() > 0) {
            for (FootballClub club : clubsList) { //loop through the clubsList arraylist
                if (club.getClubName().equalsIgnoreCase(selectedClub)) { //searching for the club that the user has entered
                    bln1 = true;
                    System.out.println("Club Name : " + club.getClubName()); //printing out the details of the club
                    System.out.println("Club Location : " + club.getClubLocation());
                    System.out.println("Number of Seasons played : " + club.getNumberOfSeasonsPlayed());
                    System.out.println("Number of Wins : " + club.getNumberOfWins());
                    System.out.println("Number of Draws : " + club.getNumberOfDraws());
                    System.out.println("Number of Defeats : " + club.getNumberOfDefeats());
                    System.out.println("Number of Goals Received : " + club.getNumberOfGoalsReceived());
                    System.out.println("Number of Goals Scored : " + club.getNumberOfGoalsScored());
                    System.out.println("Number of Points : " + club.getPoints());
                    System.out.println("Number of Matches played : " + club.getNumberOfMatchesPlayed());

                    if (club instanceof SchoolFootballClub) {

                        System.out.println("Club Type : School Football Club");
                        System.out.println("Name of the School : " + ((SchoolFootballClub) club).getSchoolName());

                    } else if (club instanceof UniversityFootballClub) {

                        System.out.println("Club Type : University Football Club");
                        System.out.println("Name of the University : " + ((UniversityFootballClub) club).getUniversityName());

                    } else {
                        System.out.println("Club Type : Football Club");
                    }
                }
            }
            if(!bln1){
                System.out.println("The club you entered does not appear in the list"); //validation if the club name that the user entered does not appear in the clubsList arraylist
            }

        } else {
            System.out.println("The clubs list appears to be empty"); //validation if the clubsList arraylist has no clubs display
        }
    }

    @Override
    public void sortedClubStat() { //method to sort the clubs list using to it's points and number of goals
        FootballClub[] clubsListArray = clubsList.toArray(new FootballClub[]{});
        SortingAlgo.bubbleSort(clubsListArray); //calling the method bubbleSort from the class SortingAlgo
        List<FootballClub> sortedList = Arrays.asList(clubsListArray);
        if (sortedList.size() > 0) {
            for (FootballClub sortedClub : sortedList) { //loop through the sortedList and printing out the information
                System.out.print("Club Name : " + sortedClub.getClubName());
                System.out.print(" | Club Location : " + sortedClub.getClubLocation());
                System.out.print(" | Number of Seasons played : " + sortedClub.getNumberOfSeasonsPlayed());
                System.out.print(" | Number of Wins : " + sortedClub.getNumberOfWins());
                System.out.print(" | Number of Draws : " + sortedClub.getNumberOfDraws());
                System.out.print(" | Number of Defeats : " + sortedClub.getNumberOfDefeats());
                System.out.print(" | Number of Goals Received : " + sortedClub.getNumberOfGoalsReceived());
                System.out.print(" | Number of Goals Scored : " + sortedClub.getNumberOfGoalsScored());
                System.out.print(" | Number of Points : " + sortedClub.getPoints());
                System.out.print(" | Number of Matches played : " + sortedClub.getNumberOfMatchesPlayed());

                if (sortedClub instanceof SchoolFootballClub) {

                    System.out.print(" | Club Type : School Football Club");
                    System.out.print(" | Name of the School : " + ((SchoolFootballClub) sortedClub).getSchoolName()+ "\n");

                } else if (sortedClub instanceof UniversityFootballClub) {

                    System.out.print(" | Club Type : University Football Club");
                    System.out.print(" | Name of the University : " + ((UniversityFootballClub) sortedClub).getUniversityName()+ "\n");

                } else {

                    System.out.print(" | Club Type : Football Club"+ "\n");

                }

            }
        } else {
            System.out.println("The Clubs list appears to be empty");
        }

    }
    @Override
    public void addPlayedMatch() { //method to add a played match to the system

        Scanner sc3 = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Enter match Date format(dd-mm-yyyy) : "); //getting the date of the match from the user
        String matchDate = sc3.next();
        Date date;

        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(matchDate); //initialising a pattern for the date
            System.out.print("Enter First Club Name : "); //getting the first club from the user
            String firstClub =sc3.next();
            FootballClub first = null;
            for(FootballClub club : clubsList){
                if(club.getClubName().equalsIgnoreCase(firstClub)) //searching for the first club of the match
                    first = club;
            }
            if(clubsList.contains(first))   {
                System.out.print("Enter the second Club Name : "); //getting the second club from the user
                String secondClub =sc3.next();
                FootballClub second = null;
                for(FootballClub club : clubsList){
                    if(club.getClubName().equalsIgnoreCase(secondClub)) //searching for the second club of the match
                        second = club;
                }
                if (clubsList.contains(second)){
                    System.out.print("Enter the number of goals the Club 1 scored : ");//getting the first club goals
                    while (!sc3.hasNextInt()) { //validating the user's input because the number of seasons played can only be an integer value
                        sc3.next();
                        System.out.print("Only integers are allowed, please try again : ");
                    }
                    int club1GoalsScored = sc3.nextInt();

                    System.out.print("Enter the number of goals the Club 2 scored : "); //getting the second club goals

                    while (!sc3.hasNextInt()) { //validating the user's input because the number of seasons played can only be an integer value
                        sc3.next();
                        System.out.print("Only integers are allowed, please try again : ");
                    }

                    int club2GoalsScored = sc3.nextInt();

                    Match match = new Match(first,second,club1GoalsScored,club2GoalsScored,date); //setting the match details to the match class object
                    match.setMatchDate(date);
                    match.setClub1(first);
                    match.setClub2(second);
                    match.setClub1Score(club1GoalsScored);
                    match.setClub2Score(club2GoalsScored);

                    if(playedMatches.contains(match)){
                        System.out.println("This match is already in the system");
                    }
                    else{
                        playedMatches.add(match); //adding the match to the playedMatches arraylist
                        assert first != null;
                        first.setNumberOfGoalsScored(first.getNumberOfGoalsScored()+club1GoalsScored); //setting the variables from the FootballClub class according to the details entered
                        assert second != null;
                        second.setNumberOfGoalsScored(second.getNumberOfGoalsScored()+club2GoalsScored);
                        first.setNumberOfGoalsReceived(first.getNumberOfGoalsReceived()+club2GoalsScored);
                        second.setNumberOfGoalsReceived(second.getNumberOfGoalsReceived()+club1GoalsScored);

                        if(club1GoalsScored > club2GoalsScored){ //if the club1 wins the match
                            first.setPoints(first.getPoints()+3);
                            first.setNumberOfWins(first.getNumberOfWins()+1);
                            second.setNumberOfDefeats(second.getNumberOfDefeats()+1);
                            first.setNumberOfMatchesPlayed(first.getNumberOfMatchesPlayed()+1);
                            second.setNumberOfMatchesPlayed(second.getNumberOfMatchesPlayed()+1);
                        }
                        else if (club1GoalsScored < club2GoalsScored){ //if club2 wins the match
                            second.setPoints(second.getPoints()+3);
                            second.setNumberOfWins(second.getNumberOfWins()+1);
                            first.setNumberOfDefeats(first.getNumberOfDefeats()+1);
                            first.setNumberOfMatchesPlayed(first.getNumberOfMatchesPlayed()+1);
                            second.setNumberOfMatchesPlayed(second.getNumberOfMatchesPlayed()+1);
                        }
                        else { //if the match is a draw
                            first.setPoints(first.getPoints()+1);
                            second.setPoints(second.getPoints()+1);
                            first.setNumberOfDraws(first.getNumberOfDraws()+1);
                            second.setNumberOfDraws(second.getNumberOfDraws()+1);
                            first.setNumberOfMatchesPlayed(first.getNumberOfMatchesPlayed()+1);
                            second.setNumberOfMatchesPlayed(second.getNumberOfMatchesPlayed()+1);
                        }
                        System.out.println("\nMatch added Successfully\n");
                    }

                } else {
                    System.out.println("The club you entered does not appear in the list, Please add the club if you want to continue adding the match\nOr select another option");
                }
            } else {
                System.out.println("The club you entered does not appear in the list, Please add the club if you want to continue adding the match\nOr select another option");
            }

        } catch (ParseException e) {
            System.out.println("Please use the given date format");
        }

    }
    @Override
    public void saveToFile () throws IOException { //method to save all the data to a file
        FileOutputStream fileOutputStream = new FileOutputStream("clubsData.ser"); //creating a fileOutputStream and initialising the fle that the data gets saved
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream); //creating the objectOutputStream to add the objects to the file that previously specified
        for(FootballClub club : clubsList)
            objectOutputStream.writeObject(club); //loop through the clubsList and adding all the clubs to the file
        objectOutputStream.close();
        fileOutputStream.close();
        System.out.println("Clubs data saved to file clubsData.ser"); //let the user know that the clubs data were saved to the file

    }

    @Override
    public void loadSavedData () { //method to load all the saved data from the file 
        try{
        FileInputStream fileInputStream = new FileInputStream("clubsData.ser");//creating a fileOutputStream and setting the path to the same file that the data was saved
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);//creating a objectInputStream to retrieve all the objects that got saved in the file
        for(; ;){
            try {
                FootballClub footballClub = (FootballClub) objectInputStream.readObject();
                clubsList.add(footballClub); //adding the retrieved objects to the clubsList
            }catch(EOFException e){
            break;
            }
        }
            objectInputStream.close();
            fileInputStream.close();

            System.out.println("Clubs data Loaded from file clubsData.ser"); //let the user know that the clubs data were loaded
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Files are empty, Please save data to files first");
        }

    }
    @Override
    public void saveMatchToFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("matchesData.ser"); //creating a fileOutputStream and initialising the fle that the data gets saved
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream); //creating the objectOutputStream to add the objects to the file that previously specified
        for(Match match : playedMatches)
            objectOutputStream.writeObject(match); //loop through the playedMatches List and adding all the clubs to the file
        objectOutputStream.close();
        fileOutputStream.close();
        System.out.println("Matches data saved to file matchesData.ser"); //let the user know that the matched data were saved to the file
    }

    @Override
    public void loadSavedMatchesData () { //method to load all the saved data from the file
        try{
            FileInputStream fileInputStream = new FileInputStream("matchesData.ser");//creating a fileOutputStream and setting the path to the same file that the data was saved
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);//creating a objectInputStream to retrieve all the objects that got saved in the file
            for(; ;){
                try {
                    Match match = (Match) objectInputStream.readObject();
                    playedMatches.add(match); //adding the retrieved objects to the playedMatches List
                }catch(EOFException e){
                    break;
                }
            }

            objectInputStream.close();
            fileInputStream.close();

            System.out.println("Matches data Loaded from file matchesData.ser"); //let the user know that the matches data were loaded
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("");
        }

    }

    }

