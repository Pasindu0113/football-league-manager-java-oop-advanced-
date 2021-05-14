package Clubs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.awt.*;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main extends Application {

    static TableView<FootballClub> clubsTable; //table to add all the clubs
    static TableView<Match> matchesTable; //table to add all the matches the clubs play
    static Random randomClub = new Random(); //to get a club randomly from the list
    private final static PremierLeagueManager plm = new PremierLeagueManager();

    public static void main(String[] args) {
        try {
            plm.loadSavedData(); //load saved data from files
            plm.loadSavedMatchesData();
        } catch (Exception e) {
            System.out.println("Save data to file first");
        }

        //console application
        try {
            while (true) {
                Scanner sc = new Scanner(System.in).useDelimiter("\n"); //adding the options to the Menu
                System.out.println("--------- Football League Manager ---------\n");
                System.out.println("1. Add a new Club");
                System.out.println("2. Delete an existing Club");
                System.out.println("3. Display the statistics for a selected Club");
                System.out.println("4. Sorted statistics of all Clubs");
                System.out.println("5. Add a played Match");
                System.out.println("6. Save Data to Files");
                System.out.println("7. Open JavaFX GUI");
                System.out.println("8. Open Angular GUI");
                System.out.println("Click Q to exit the programme\n");

                System.out.print("Enter your Request : ");//getting the user's request
                String UserRequest = sc.nextLine();

                if (UserRequest.equals("1")) { //calling on the methods according to the user's input
                    plm.addClub();
                } else if (UserRequest.equals("2")) {
                    plm.deleteClub();
                } else if (UserRequest.equals("3")) {
                    plm.selectedClubStat();
                } else if (UserRequest.equals("4")) {
                    plm.sortedClubStat();
                } else if (UserRequest.equals("5")) {
                    plm.addPlayedMatch();
                } else if (UserRequest.equals("6")) {
                    plm.saveToFile();
                    plm.saveMatchToFile();
                } else if (UserRequest.equals("7")) {
                    launch(args);
                } else if (UserRequest.equals("8")) {
                    try {
                        Desktop gui = java.awt.Desktop.getDesktop();
                        URI guiURL = new URI("http://localhost:4200/");
                        gui.browse(guiURL);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (UserRequest.equalsIgnoreCase("Q")) { //exit from the menu loop
                    System.out.println("Program successfully terminated \nGood bye !");
                    break;
                } else {
                    System.out.println("Invalid Input Entered, Please Reenter"); //validating if the user enters anything other than the options presented
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid");
        }

    }

    //GUI
    @Override
    public void start(Stage primaryStage) {
        home(primaryStage);
    }
    public static void home(Stage primaryStage) {
        primaryStage.setTitle("Premier League Manager");

        javafx.scene.control.Button clubs = new javafx.scene.control.Button("Clubs");
        clubs.setMinWidth(150);

        javafx.scene.control.Button matches = new javafx.scene.control.Button("Matches");
        matches.setMinWidth(150);

        clubs.setLayoutX(65);
        clubs.setLayoutY(400);

        matches.setLayoutX(565);
        matches.setLayoutY(400);

        javafx.scene.control.Button searchBtn = new javafx.scene.control.Button("Search Match");
        searchBtn.setMinWidth(150);
        searchBtn.setMinHeight(50);

        javafx.scene.image.Image img = new javafx.scene.image.Image("search.png",40,40,false,true);
        ImageView view = new ImageView(img);
        searchBtn.setGraphic(view);

        searchBtn.setLayoutX(315);
        searchBtn.setLayoutY(380);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId("Home");
        anchorPane.getChildren().addAll(clubs,matches,searchBtn); //adding the created buttons to the created anchorPane

        clubs.setOnAction(event -> {   //setting the button to open the clubs window when clicked
            primaryStage.close();
            Stage stage2 = new Stage();
            stage2.setTitle("Clubs List");

            AnchorPane anchorPane1 = new AnchorPane();
            anchorPane1.setId("Clubs");

            TableColumn<FootballClub, String> clubNameColumn = new TableColumn<>("Club Name");   //adding details to the clubs table
            clubNameColumn.setMinWidth(150);
            clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("ClubName"));

            TableColumn<FootballClub, String> clubLocationColumn = new TableColumn<>("Club Location");
            clubLocationColumn.setMinWidth(150);
            clubLocationColumn.setCellValueFactory(new PropertyValueFactory<>("ClubLocation"));

            TableColumn<FootballClub, Integer> noOfWinsColumn = new TableColumn<>("No. of Wins");
            noOfWinsColumn.setMinWidth(140);
            noOfWinsColumn.setCellValueFactory(new PropertyValueFactory<>("NumberOfWins"));

            TableColumn<FootballClub, Integer> noOfDefeatsColumn = new TableColumn<>("No. of Defeats");
            noOfDefeatsColumn.setMinWidth(140);
            noOfDefeatsColumn.setCellValueFactory(new PropertyValueFactory<>("NumberOfDefeats"));

            TableColumn<FootballClub, Integer> noOfDrawsColumn = new TableColumn<>("No. of Draws");
            noOfDrawsColumn.setMinWidth(140);
            noOfDrawsColumn.setCellValueFactory(new PropertyValueFactory<>("NumberOfDraws"));

            TableColumn<FootballClub, Integer> noOfGoalsReceivedColumn = new TableColumn<>("No. of Goals Received");
            noOfGoalsReceivedColumn.setMinWidth(200);
            noOfGoalsReceivedColumn.setCellValueFactory(new PropertyValueFactory<>("NumberOfGoalsReceived"));

            TableColumn<FootballClub, Integer> noOfGoalsScoredColumn = new TableColumn<>("No. of Goals Scored");
            noOfGoalsScoredColumn.setMinWidth(200);
            noOfGoalsScoredColumn.setCellValueFactory(new PropertyValueFactory<>("NumberOfGoalsScored"));

            TableColumn<FootballClub, Integer> noOfMatchedPlayedColumn = new TableColumn<>("No. of Matches Played");
            noOfMatchedPlayedColumn.setMinWidth(200);
            noOfMatchedPlayedColumn.setCellValueFactory(new PropertyValueFactory<>("NumberOfMatchesPlayed"));

            TableColumn<FootballClub, Integer> noOfPointsColumn = new TableColumn<>("No. of Points");
            noOfPointsColumn.setMinWidth(130);
            noOfPointsColumn.setCellValueFactory(new PropertyValueFactory<>("Points"));

            TableColumn<FootballClub, Integer> seasonsPlayedColumn = new TableColumn<>("Seasons Played");
            seasonsPlayedColumn.setMinWidth(130);
            seasonsPlayedColumn.setCellValueFactory(new PropertyValueFactory<>("NumberOfSeasonsPlayed"));

            javafx.scene.control.Button sortPoints = new javafx.scene.control.Button("Sort Points");
            sortPoints.setId("btnStyle");
            sortPoints.setLayoutX(100);
            sortPoints.setLayoutY(630);

            sortPoints.setOnAction(event1 -> {   //button to sort according to the points in descending order
                noOfGoalsScoredColumn.setSortable(false);
                noOfWinsColumn.setSortable(false);
                noOfPointsColumn.setSortable(true);
                noOfPointsColumn.setSortType(TableColumn.SortType.DESCENDING);
                clubsTable.getSortOrder().add(noOfPointsColumn);
                clubsTable.sort();
            });

            javafx.scene.control.Button sortGoalsScored = new javafx.scene.control.Button("Sort Goals Scored");
            sortGoalsScored.setId("btnStyle");
            sortGoalsScored.setLayoutX(250);
            sortGoalsScored.setLayoutY(630);

            sortGoalsScored.setOnAction(event2 -> {   //button to sort according to the goals scored in descending order
                noOfGoalsScoredColumn.setSortable(true);
                noOfWinsColumn.setSortable(false);
                noOfPointsColumn.setSortable(false);
                noOfGoalsScoredColumn.setSortType(TableColumn.SortType.DESCENDING);
                clubsTable.getSortOrder().add(noOfGoalsScoredColumn);
                clubsTable.sort();
            });

            javafx.scene.control.Button sortWins = new javafx.scene.control.Button("Sort Wins");
            sortWins.setId("btnStyle");
            sortWins.setLayoutX(450);
            sortWins.setLayoutY(630);

            sortWins.setOnAction(event3 -> {//button to sort according to the wins in Ascending order
                noOfGoalsScoredColumn.setSortable(false);
                noOfWinsColumn.setSortable(true);
                noOfPointsColumn.setSortable(false);
                noOfWinsColumn.setSortType(TableColumn.SortType.DESCENDING);
                clubsTable.getSortOrder().add(noOfWinsColumn);
                clubsTable.sort();
            });

            javafx.scene.control.Button backBtn = new javafx.scene.control.Button("");   //initialising a home button to navigate to the home page once clicked
            backBtn.setId("btnStyle1");
            backBtn.setLayoutX(100);
            backBtn.setLayoutY(730);

            javafx.scene.image.Image img1 = new javafx.scene.image.Image("homeBtn.png",40,40,false,false);
            ImageView view1 = new ImageView(img1);
            backBtn.setGraphic(view1);

            backBtn.setOnAction(event12 -> {
                stage2.close();
                primaryStage.show();
            });

            clubsTable = new TableView<>();
            clubsTable.setLayoutX(100);
            clubsTable.setLayoutY(100);
            clubsTable.setItems(getFootballClubs());
            clubsTable.getColumns().addAll(clubNameColumn,clubLocationColumn,noOfWinsColumn,noOfDefeatsColumn,noOfDrawsColumn,noOfGoalsReceivedColumn,noOfGoalsScoredColumn,noOfMatchedPlayedColumn,noOfPointsColumn,seasonsPlayedColumn);

            anchorPane1.getChildren().addAll(clubsTable,backBtn,sortPoints,sortGoalsScored,sortWins);
            Scene scene1 = new Scene(anchorPane1,1800,800);
            scene1.getStylesheets().add(Main.class.getResource("ProjectStyles.css").toExternalForm());
            stage2.setScene(scene1);
            stage2.show();
        });

        searchBtn.setOnAction(event -> {  //to open the search window
            Stage stage = new Stage();
            AnchorPane anchorPane2 = new AnchorPane();
            javafx.scene.control.Label label = new javafx.scene.control.Label("Enter the date");
            label.setLayoutX(200);
            label.setLayoutY(50);
            javafx.scene.control.TextField tf1 = new javafx.scene.control.TextField();
            tf1.setPromptText("Enter date (dd-MM-yyyy)");
            tf1.setLayoutX(200);
            tf1.setLayoutY(100);
            javafx.scene.control.Button src = new javafx.scene.control.Button("Search");  //initialising the search function in the system to get the match played according to the date played
            src.setLayoutX(450);
            src.setLayoutY(100);
            javafx.scene.control.Button back = new javafx.scene.control.Button("");
            back.setLayoutX(20);
            back.setLayoutY(20);

            javafx.scene.image.Image img1 = new javafx.scene.image.Image("homeBtn.png",40,40,false,false);
            ImageView view1 = new ImageView(img1);
            back.setGraphic(view1);

            anchorPane2.getChildren().addAll(tf1,src,label,back);
            anchorPane2.setId("Search");
            Scene scene = new Scene(anchorPane2,600,200);
            scene.getStylesheets().add(Main.class.getResource("ProjectStyles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

            back.setOnAction(event2 -> {
                stage.close();
                primaryStage.show();
            });

            src.setOnAction(event1 -> {

                Stage stage2 = new Stage();
                AnchorPane anchorPane1 = new AnchorPane();
                javafx.scene.control.Label lb2 = new javafx.scene.control.Label();
                stage2.setTitle("Searched Match");

                javafx.scene.control.Button close = new javafx.scene.control.Button("Close");
                close.setLayoutX(980);
                close.setLayoutY(150);

                try {
                    String mDate = tf1.getText();
                    boolean a = tf1.getText().isEmpty();
                    if(a){
                        Alert alert = new Alert(Alert.AlertType.ERROR,"Please enter a Date to continue", ButtonType.CLOSE);
                        alert.showAndWait();
                    }
                    else {
                        boolean b = false;
                        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(mDate);
                        for (Match match : plm.playedMatches) {
                            if (match.getMatchDate().equals(date)) {
                                lb2.setText(String.valueOf(match));
                                b = true;
                            }
                        }
                        if (!b) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "There were no matches played on this day", ButtonType.CLOSE);
                            alert.showAndWait();
                        } else {
                            close.setOnAction(event2 -> stage2.close());
                            anchorPane1.getChildren().addAll(lb2, close);
                            Scene scene1 = new Scene(anchorPane1, 1050, 200);
                            scene1.getStylesheets().add(Main.class.getResource("ProjectStyles.css").toExternalForm());
                            stage2.setScene(scene1);
                            stage2.show();
                        }
                    }
                } catch (ParseException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid date entered, Please try again", ButtonType.CLOSE);
                    alert.showAndWait();
                    tf1.clear();
                }
            });
        });

        matches.setOnAction(event1 -> { //open the matches window
            primaryStage.close();
            Stage stage3 = new Stage();
            stage3.setTitle("Matches List");

            AnchorPane anchorPane2 = new AnchorPane();
            TableColumn<Match, FootballClub> club1column = new TableColumn<>("First Club Name");
            club1column.setMinWidth(200);
            club1column.setCellValueFactory(new PropertyValueFactory<>("Club1"));

            TableColumn<Match, FootballClub> club2column = new TableColumn<>("Second Club Name");
            club2column.setMinWidth(200);
            club2column.setCellValueFactory(new PropertyValueFactory<>("Club2"));

            TableColumn<Match, Integer> club1ScoreColumn = new TableColumn<>("First Club Score");
            club1ScoreColumn.setMinWidth(100);
            club1ScoreColumn.setCellValueFactory(new PropertyValueFactory<>("Club1Score"));

            TableColumn<Match, Integer> club2ScoreColumn = new TableColumn<>("Second Club Score");
            club2ScoreColumn.setMinWidth(100);
            club2ScoreColumn.setCellValueFactory(new PropertyValueFactory<>("Club2Score"));

            TableColumn<Match, Integer> matchDateColumn = new TableColumn<>("Match Date");
            matchDateColumn.setMinWidth(300);
            matchDateColumn.setCellValueFactory(new PropertyValueFactory<>("MatchDate"));

            javafx.scene.control.Button backBtn = new javafx.scene.control.Button("");
            backBtn.setId("btnStyle");
            backBtn.setLayoutX(100);
            backBtn.setLayoutY(730);

            javafx.scene.image.Image img1 = new Image("homeBtn.png",40,40,false,false);
            ImageView view1 = new ImageView(img1);
            backBtn.setGraphic(view1);

            backBtn.setOnAction(event -> {
                stage3.close();
                primaryStage.show();
            });

            javafx.scene.control.Button rndMatch = new javafx.scene.control.Button("Random Match");
            rndMatch.setId("btnStyle");
            rndMatch.setLayoutX(100);
            rndMatch.setLayoutY(600);

            javafx.scene.control.Label rndMatchDetails = new javafx.scene.control.Label();
            rndMatchDetails.setId("lbStyle");
            rndMatchDetails.setLayoutX(600);
            rndMatchDetails.setLayoutY(600);

            rndMatch.setOnAction(event2 -> { //button to generate a random match
                int i = randomClub.nextInt(plm.clubsList.size()); //get the first random club
                int j = randomClub.nextInt(plm.clubsList.size()); //get the second random club

                if(i == j){
                    System.out.println("Same teams got generated please try again");
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Same Teams got generated, Please try again", ButtonType.CLOSE);
                    alert.showAndWait();
                }
                else {
                    Random random = new Random();

                    int club1RandomScore = random.nextInt(11); //get the club 1 random score (0-10)
                    int club2RandomScore = random.nextInt(11); //get the club 2 random score (0-10)
                    int randomDay = 1+random.nextInt(28); //get the random day
                    int randomMonth = 1+random.nextInt(12); //get the random month
                    int randomYear = 2000+random.nextInt(2020-2000); //get the random year

                    String randomMatchDate = randomDay + "-" + randomMonth + "-" + randomYear;

                    try {
                        Date randomDate = new SimpleDateFormat("dd-MM-yyyy").parse(randomMatchDate);
                        Match match = new Match(plm.clubsList.get(i),plm.clubsList.get(j),club1RandomScore,club2RandomScore,randomDate);

                        match.setMatchDate(randomDate);
                        match.setClub1(plm.clubsList.get(i));
                        match.setClub2(plm.clubsList.get(j));
                        match.setClub1Score(club1RandomScore);
                        match.setClub2Score(club2RandomScore);

                        plm.playedMatches.add(match);
                        rndMatchDetails.setText(String.valueOf(match));
                        matchesTable.getItems().add(match);

                        plm.clubsList.get(i).setNumberOfGoalsScored(plm.clubsList.get(i).getNumberOfGoalsScored() + club1RandomScore); //setting the variables from the FootballClub class according to the details entered
                        plm.clubsList.get(j).setNumberOfGoalsScored(plm.clubsList.get(j).getNumberOfGoalsScored() + club2RandomScore);
                        plm.clubsList.get(i).setNumberOfGoalsReceived(plm.clubsList.get(i).getNumberOfGoalsReceived() + club2RandomScore);
                        plm.clubsList.get(j).setNumberOfGoalsReceived(plm.clubsList.get(j).getNumberOfGoalsReceived() + club1RandomScore);

                        if (club1RandomScore > club2RandomScore) { //if the club1 wins the match
                            plm.clubsList.get(i).setPoints(plm.clubsList.get(i).getPoints() + 3);
                            plm.clubsList.get(i).setNumberOfWins(plm.clubsList.get(i).getNumberOfWins() + 1);
                            plm.clubsList.get(j).setNumberOfDefeats(plm.clubsList.get(j).getNumberOfDefeats() + 1);
                            plm.clubsList.get(i).setNumberOfMatchesPlayed(plm.clubsList.get(i).getNumberOfMatchesPlayed() + 1);
                            plm.clubsList.get(j).setNumberOfMatchesPlayed(plm.clubsList.get(j).getNumberOfMatchesPlayed() + 1);
                        } else if (club1RandomScore < club2RandomScore) { //if club2 wins the match
                            plm.clubsList.get(j).setPoints(plm.clubsList.get(j).getPoints() + 3);
                            plm.clubsList.get(j).setNumberOfWins(plm.clubsList.get(j).getNumberOfWins() + 1);
                            plm.clubsList.get(i).setNumberOfDefeats(plm.clubsList.get(i).getNumberOfDefeats() + 1);
                            plm.clubsList.get(i).setNumberOfMatchesPlayed(plm.clubsList.get(i).getNumberOfMatchesPlayed() + 1);
                            plm.clubsList.get(j).setNumberOfMatchesPlayed(plm.clubsList.get(j).getNumberOfMatchesPlayed() + 1);
                        } else { //if the match is a draw
                            plm.clubsList.get(i).setPoints(plm.clubsList.get(i).getPoints() + 1);
                            plm.clubsList.get(j).setPoints(plm.clubsList.get(j).getPoints() + 1);
                            plm.clubsList.get(i).setNumberOfDraws(plm.clubsList.get(i).getNumberOfDraws() + 1);
                            plm.clubsList.get(j).setNumberOfDraws(plm.clubsList.get(j).getNumberOfDraws() + 1);
                            plm.clubsList.get(i).setNumberOfMatchesPlayed(plm.clubsList.get(i).getNumberOfMatchesPlayed() + 1);
                            plm.clubsList.get(j).setNumberOfMatchesPlayed(plm.clubsList.get(j).getNumberOfMatchesPlayed() + 1);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });

            javafx.scene.control.Button sort = new Button("Sort Matches");
            sort.setId("btnStyle");
            sort.setLayoutX(250);
            sort.setLayoutY(600);

            sort.setOnAction(event -> { //sort the matches according to their played date
                matchDateColumn.setSortType(TableColumn.SortType.ASCENDING);
                matchesTable.getSortOrder().add(matchDateColumn);
                matchesTable.sort();
            });

            matchesTable = new TableView<>();

            matchesTable.setLayoutX(100);
            matchesTable.setLayoutY(105);
            matchesTable.setItems(getPlayedMatches());
            matchesTable.getColumns().addAll(club1column,club2column,club1ScoreColumn,club2ScoreColumn,matchDateColumn);

            anchorPane2.getChildren().addAll(matchesTable,backBtn,rndMatch,sort,rndMatchDetails);
            anchorPane2.setId("Matches");
            Scene scene2 = new Scene(anchorPane2,1100,800);
            scene2.getStylesheets().add(Main.class.getResource("ProjectStyles.css").toExternalForm());
            stage3.setScene(scene2);
            stage3.show();
        });

        Scene scene = new Scene(anchorPane,800,450);
        scene.getStylesheets().add(Main.class.getResource("ProjectStyles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static ObservableList<FootballClub> getFootballClubs(){ //getting information of the clubs to add to the clubs table
        ObservableList<FootballClub> footballClubs = FXCollections.observableArrayList();
        while(footballClubs.size() < plm.clubsList.size()){
            footballClubs.addAll(plm.clubsList);
        }
        return footballClubs;
    }
    public static ObservableList<Match> getPlayedMatches(){ //getting information of each played match by clubs to add to the table
        ObservableList<Match> matches = FXCollections.observableArrayList();
        while(matches.size() < plm.playedMatches.size()){
            matches.addAll(plm.playedMatches);
        }
        return matches;
    }


}







