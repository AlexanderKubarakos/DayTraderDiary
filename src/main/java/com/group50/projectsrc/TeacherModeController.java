package com.group50.projectsrc;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
//

/**
 * The TeacherModeController class controls the UI interactions and logic for the Teacher Mode of the StockMoney project.
 * @author Lyndon Zhang
 */
public class TeacherModeController {


    @FXML
    private Label pieInfo;

    @FXML
    private Pane finishedInfo;

    @FXML
    private Label playersFinished;

    @FXML
    private Label playersNotFinished;

    @FXML
    private Label totalPlayers;

    @FXML
    private CheckBox showPassBox;

    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameEntered;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordFieldVisible;

    @FXML
    private ChoiceBox<String> sortByStats;

    @FXML
    private PieChart daysGraph;

    @FXML
    private BarChart<String, Number> finishedGraph;

    @FXML
    private Pane graphsScreen;
    @FXML
    private ScatterChart<Number, Number> moneyGraph;

    @FXML
    private TextFlow studentDayText;

    @FXML
    private TextFlow studentFinishedText;

    @FXML
    private TextFlow studentMoneyText;

    @FXML
    private TextFlow studentNameText;

    @FXML
    private TextFlow studentProgLvlText;

    @FXML
    private Pane studentStatsScreen;

    @FXML
    private Pane teacherModeScreen;

    @FXML
    private Pane teacherLoginScreen;

    @FXML
    private Label loginFailedText;

    @FXML
    private Pane moneyInfo;

    @FXML
    private Label highestMoney;

    @FXML
    private Label highestUser;

    @FXML
    private Label lowestMoney;

    @FXML
    private Label lowestUser;

    @FXML
    private Label avgDay1;

    @FXML
    private Label avgDay2;

    @FXML
    private Label avgDay3;

    @FXML
    private Label avgDay4;

    @FXML
    private Label avgDay5;

    ArrayList<PlayerData> playerData;

    /**
     * Updates the user interface with the latest player data.
     */
    public void updateUI() {
        playerData = PlayerDataManager.data;
    }
    /**
     * Refreshes the list of students based on the selected sorting criteria.
     */
    public void refreshStudents(){

        if(!sortByStats.getItems().contains("Username (A-Z)")){
            sortByStats.getItems().add("Username (A-Z)");
            sortByStats.getItems().add("Username (Z-A)");
            sortByStats.getItems().add("Money");
            sortByStats.getItems().add("Level");
            sortByStats.getItems().add("Day");
            sortByStats.getItems().add("Finished");
        }

        studentNameText.getChildren().clear();
        studentMoneyText.getChildren().clear();
        studentProgLvlText.getChildren().clear();
        studentDayText.getChildren().clear();
        studentFinishedText.getChildren().clear();

        for(int i = 0; i < playerData.size(); i++){
            indexSort(i);
        }
    }

    /**
     * Initiates sorting based on the selected sorting criteria.
     */
    public void newSort(){
        int studentAmount = playerData.size();
        if(sortByStats.getValue() != null){
            studentNameText.getChildren().clear();
            studentMoneyText.getChildren().clear();
            studentProgLvlText.getChildren().clear();
            studentDayText.getChildren().clear();
            studentFinishedText.getChildren().clear();

            if(sortByStats.getValue().equals("Username (A-Z)")){
                String[] nameArray = new String[studentAmount];
                for(int i = 0; i < studentAmount; i++){
                    nameArray[i] = playerData.get(i).getUsername();
                }
                for(int c = 0; c < studentAmount; c++) {
                    int findIndex = 0;
                    String smallest = nameArray[findIndex];
                    while(nameArray[findIndex].isEmpty()){
                        findIndex++;
                    }
                    for (int i = 0; i < studentAmount; i++) {
                        if (!nameArray[i].isEmpty() && nameArray[i].compareTo(smallest) < 0) {
                            smallest = nameArray[i];
                            findIndex = i;
                        }
                    }
                    indexSort(findIndex);
                    nameArray[findIndex] = "";
                }
            }
            else if(sortByStats.getValue().equals("Username (Z-A)")){
                String[] nameArray = new String[studentAmount];
                for(int i = 0; i < studentAmount; i++){
                    nameArray[i] = playerData.get(i).getUsername();
                }
                for(int c = 0; c < studentAmount; c++) {
                    int findIndex = 0;
                    String biggest = nameArray[findIndex];
                    while(nameArray[findIndex].isEmpty()){
                        findIndex++;
                    }
                    for (int i = 0; i < studentAmount; i++) {
                        if (nameArray[i].compareTo(biggest) > 0) {
                            biggest = nameArray[i];
                            findIndex = i;
                        }
                    }
                    indexSort(findIndex);
                    nameArray[findIndex] = "";
                }
            }
            else if(sortByStats.getValue().equals("Money")){
                float[] moneyArray = new float[studentAmount];
                for(int i = 0; i < studentAmount; i++){
                    moneyArray[i] = playerData.get(i).getMoney();
                }
                for(int c = 0; c < studentAmount; c++) {
                    float biggest = -1;
                    int index = -1;
                    for (int i = 0; i < studentAmount; i++) {
                        if (moneyArray[i] > biggest) {
                            biggest = moneyArray[i];
                            index = i;
                        }
                    }
                    indexSort(index);
                    moneyArray[index] = -1;
                }
            }
            else if(sortByStats.getValue().equals("Level")){
                int[] levelArray = new int[studentAmount];
                for(int i = 0; i < studentAmount; i++){
                    levelArray[i] = playerData.get(i).getProgresionLevel();
                }
                for(int c = 0; c < studentAmount; c++) {
                    int biggest = -1;
                    int index = -1;
                    for (int i = 0; i < studentAmount; i++) {
                        if (levelArray[i] > biggest) {
                            biggest = levelArray[i];
                            index = i;
                        }
                    }
                    indexSort(index);
                    levelArray[index] = -1;
                }
            }
            else if(sortByStats.getValue().equals("Day")){
                int[] dayArray = new int[studentAmount];
                for(int i = 0; i < studentAmount; i++){
                    dayArray[i] = playerData.get(i).getDay();
                }
                for(int c = 0; c < studentAmount; c++) {
                    int biggest = -1;
                    int index = -1;
                    for (int i = 0; i < studentAmount; i++) {
                        if (dayArray[i] > biggest) {
                            biggest = dayArray[i];
                            index = i;
                        }
                    }
                    indexSort(index);
                    dayArray[index] = -1;
                }
            }
            else {
                for(int i = 0; i < studentAmount; i++){
                   if(playerData.get(i).isFinished()){
                       indexSort(i);
                   }
                }
                for(int i = 0; i < studentAmount; i++){
                    if(!playerData.get(i).isFinished()){
                        indexSort(i);
                    }
                }
            }
        }
    }
    /**
     * Toggles the visibility of the login failure message.
     */
    public void toggleFailed(){
        loginFailedText.setVisible(true);
    }

    /**
     * Sorts and displays the student data at the specified index.
     *
     * @param index The index of the student data to be displayed.
     */
    public void indexSort(int index){
        Text tempName = new Text(playerData.get(index).getUsername() + "\n");
        studentNameText.getChildren().add(tempName);
        studentNameText.autosize();

        Text tempMoney = new Text("$" + playerData.get(index).getMoney() + "\n");
        studentMoneyText.getChildren().add(tempMoney);
        studentMoneyText.autosize();

        Text tempLvl = new Text(playerData.get(index).getProgresionLevel() + "\n");
        studentProgLvlText.getChildren().add(tempLvl);
        studentProgLvlText.autosize();

        Text tempDay = new Text(playerData.get(index).getDay() + "\n");
        studentDayText.getChildren().add(tempDay);
        studentDayText.autosize();

        if(playerData.get(index).isFinished()){
            Text tempFinished = new Text("True"+"\n");
            studentFinishedText.getChildren().add(tempFinished);
        }
        else{
            Text tempFinished = new Text("False"+"\n");
            studentFinishedText.getChildren().add(tempFinished);
        }
        studentFinishedText.autosize();
    }

    /**
     * Toggles the visibility of the Teacher Mode screen.
     */
    public void toggleTeacherVisibility() {
        teacherModeScreen.setVisible(!teacherModeScreen.isVisible());
    }
    /**
     * Toggles the visibility of the login screen.
     */
    public void toggleLoginVisibility() {
        teacherLoginScreen.setVisible(!teacherLoginScreen.isVisible());
    }
    /**
     * Toggles the visibility of the student statistics screen.
     */
    public void toggleStatsVisibility() {
        studentStatsScreen.setVisible(!studentStatsScreen.isVisible());
    }
    /**
     * Toggles the visibility of the graphs screen.
     */
    public void toggleGraphsVisibility() {
        graphsScreen.setVisible(!graphsScreen.isVisible());
    }
    /**
     * Toggles the visibility of the days graph.
     */

    public void toggleDaysVisibility() {
        daysGraph.setVisible(!daysGraph.isVisible());
        pieInfo.setVisible(!pieInfo.isVisible());
    }
    /**
     * Toggles the visibility of the money graph and related information.
     */

    public void toggleMoneyVisibility() {
        moneyGraph.setVisible(!moneyGraph.isVisible());
        moneyInfo.setVisible(!moneyInfo.isVisible());
    }
    /**
     * Toggles the visibility of the finished graph.
     */
    public void toggleFinishedVisibility() {
        finishedGraph.setVisible(!finishedGraph.isVisible());
        finishedInfo.setVisible(!finishedInfo.isVisible());
    }

    /**
     * Handles the event when the user clicks on the exit button in Teacher Mode.
     */
    @FXML
    public void onExitTeacherModeClick() {
        AudioPlayer.playClick();
        toggleLoginVisibility();
        toggleTeacherVisibility();
        usernameField.setText("");
        passwordField.setText("");
        passwordFieldVisible.setText("");
        passwordField.setVisible(true);
        passwordFieldVisible.setVisible(false);
        loginFailedText.setVisible(false);
        showPassBox.setSelected(false);
    }

    /**
     * Handles the event when the user checks/unchecks the show password checkbox.
     */
    @FXML
    public void showPassChecked(){
        if(showPassBox.isSelected()){
            passwordField.setVisible(false);
            passwordFieldVisible.setVisible(true);
            passwordFieldVisible.setText(passwordField.getText());
        }
        else{
            passwordField.setVisible(true);
            passwordFieldVisible.setVisible(false);
            passwordField.setText(passwordFieldVisible.getText());
        }
    }

    /**
     * Handles the event when the user clicks on the login button.
     *
     * @throws FileNotFoundException If the file for teacher login data is not found.
     */
    @FXML
    public void onLoginClick() throws FileNotFoundException {
        AudioPlayer.playClick();
        updateUI();
        String currentName = usernameField.getText().toLowerCase();
        String currentPass;
        if(showPassBox.isSelected()) {
            currentPass = passwordFieldVisible.getText();
        }
        else{
            currentPass = passwordField.getText();
        }

        File login = new File("teachlogin.txt");
        Scanner loginReader = new Scanner(login);
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();

        while(loginReader.hasNextLine()){
            String data = loginReader.nextLine();
            usernames.add(data.split(",")[0]);
            passwords.add(data.split(",")[1]);
        }

        boolean loginSuccess = false;
        for(int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).toLowerCase().equals(currentName)) {
                if (passwords.get(i).equals(currentPass)) {
                    loginSuccess = true;
                    break;
                }
            }
        }
        if(loginSuccess){
            char[] displayName = currentName.toCharArray();
            String cap = String.valueOf(displayName[0]);
            displayName[0] = cap.toUpperCase().charAt(0);
            StringBuilder finalDisplayName = new StringBuilder();
            for (char c : displayName) {
                finalDisplayName.append(c);
            }
            usernameEntered.setText("Welcome back, "+finalDisplayName+"!");
            toggleLoginVisibility();
            toggleTeacherVisibility();
        }
        else{
            toggleFailed();
        }
    }
    private int dayCounter = 0;
    /**
     * Handles the event when the user clicks on the day graph.
     */
    @FXML
    public void onGraphsDayClick() {
        daysGraph.setTitle("Day Graph");
        if(dayCounter == 0){
            int playerTotal = 0;
            ArrayList<Integer> daysListed = new ArrayList<Integer>();
            ArrayList<PieChart.Data> pieData = new ArrayList<>();
            for(int i = 0; i < playerData.size(); i++){
                if(!daysListed.contains(playerData.get(i).getDay())){
                    daysListed.add(playerData.get(i).getDay());
                    pieData.add(new PieChart.Data("Day "+playerData.get(i).getDay(), 1));
                    playerTotal++;
                }
                else{
                    for(int j = 0; j < pieData.size(); j++){
                        if(pieData.get(j).getName().equals("Day "+playerData.get(i).getDay())){
                            pieData.set(j, new PieChart.Data("Day "+playerData.get(i).getDay(), pieData.get(j).getPieValue()+1));
                            playerTotal++;
                        }
                    }
                }
            }
            for(int i = 0; i < pieData.size(); i++){
                daysGraph.getData().add(pieData.get(i));
            }
            dayCounter++;
            pieInfo.setText("Total Amount of Players: "+playerTotal);
        }
        toggleDaysVisibility();
        if(moneyGraph.isVisible()){
            toggleMoneyVisibility();
        }
        if(finishedGraph.isVisible()){
            toggleFinishedVisibility();
        }
    }
    /**
     * Handles the event when the user clicks on the exit button in the graphs screen.
     */
    @FXML
    public void onGraphsExitClick() {
        AudioPlayer.playClick();
        toggleTeacherVisibility();
        toggleGraphsVisibility();
    }

    private int finishedCounter = 0;
    /**
     * Handles the event when the user clicks on the finished graph.
     */
    @FXML
    public void onGraphsFinishedClick() {
        if (finishedCounter == 0) {
            finishedGraph.setTitle("Is Finished Graph");
            XYChart.Series<String, Number> finishedData = new XYChart.Series<>();
            finishedData.setName("Is finished");
            int trueCount = 0;
            int falseCount = 0;
            for (PlayerData playerDatum : playerData) {
                if (playerDatum.isFinished()) {
                    trueCount++;
                } else {
                    falseCount++;
                }
            }
            int totalPlayers = trueCount+falseCount;
            finishedData.getData().add(new XYChart.Data<>("True", trueCount));
            finishedData.getData().add(new XYChart.Data<>("False", falseCount));
            finishedGraph.getData().add(finishedData);
            finishedGraph.setCategoryGap(20);
            finishedGraph.setBarGap(5);
            finishedGraph.setAnimated(false);
            finishedCounter++;
            playersFinished.setText("Number of Players Finished: "+trueCount);
            playersNotFinished.setText("Number of Players Not Finished: "+falseCount);
            this.totalPlayers.setText("Total Amount of Players: "+totalPlayers);
        }
        toggleFinishedVisibility();
        if(daysGraph.isVisible()){
            toggleDaysVisibility();
        }
        if(moneyGraph.isVisible()){
            toggleMoneyVisibility();
        }
    }

    private int moneyCounter = 0;
    /**
     * Handles the event when the user clicks on the money graph.
     */
    @FXML
    public void onGraphsMoneyClick() {
        toggleMoneyVisibility();
        if(finishedGraph.isVisible()){
            toggleFinishedVisibility();
        }
        if(daysGraph.isVisible()){
            toggleDaysVisibility();
        }
    }

    /**
     * Handles the event when the user clicks on the back button.
     */
    @FXML
    public void onBackButtonClick() {
        AudioPlayer.playClick();
        SceneManager.getInstance().activate("MainMenu");
    }

    /**
     * Handles the event when the user clicks on the "Show Graphs" button.
     */
    @FXML
    public void onShowGraphsClick() {
        AudioPlayer.playClick();
        if(moneyCounter == 0){
            moneyGraph.setTitle("Money Graph");
            XYChart.Series<Number, Number> moneyData = new XYChart.Series<>();
            float highestMoney = playerData.get(0).getMoney();
            String highestUser = playerData.get(0).getUsername();
            float lowestMoney = playerData.get(0).getMoney();
            String lowestUser = playerData.get(0).getUsername();

            float avgDay1 = 0, avgDay2 = 0, avgDay3 = 0, avgDay4 = 0, avgDay5 = 0;
            float day1total = 0, day2total = 0, day3total = 0, day4total = 0, day5total = 0;

            for (PlayerData playerDatum : playerData) {
                moneyData.getData().add(new XYChart.Data<>(playerDatum.getDay(), playerDatum.getMoney()));
                if(playerDatum.getDay() == 1){
                    avgDay1 += playerDatum.getMoney();
                    day1total++;
                }
                else if(playerDatum.getDay() == 2){
                    avgDay2 += playerDatum.getMoney();
                    day2total++;
                }
                else if(playerDatum.getDay() == 3){
                    avgDay3 += playerDatum.getMoney();
                    day3total++;
                }
                else if(playerDatum.getDay() == 4){
                    avgDay4 += playerDatum.getMoney();
                    day4total++;
                }
                else{
                    avgDay5 += playerDatum.getMoney();
                    day5total++;
                }
                if(playerDatum.getMoney()>highestMoney){
                    highestMoney = playerDatum.getMoney();
                    highestUser = playerDatum.getUsername();
                }
                if(playerDatum.getMoney()<lowestMoney){
                    lowestMoney = playerDatum.getMoney();
                    lowestUser = playerDatum.getUsername();
                }
            }
            //
            avgDay1 = avgDay1/day1total;
            avgDay2 = avgDay2/day2total;
            avgDay3 = avgDay3/day3total;
            avgDay4 = avgDay4/day4total;
            avgDay5 = avgDay5/day5total;
            this.avgDay1.setText("Avg for Day 1: " +avgDay1);
            this.avgDay2.setText("Avg for Day 2: " +avgDay2);
            this.avgDay3.setText("Avg for Day 3: " +avgDay3);
            this.avgDay4.setText("Avg for Day 4: " +avgDay4);
            this.avgDay5.setText("Avg for Day 5: " +avgDay5);
            this.highestMoney.setText("Highest Money: "+highestMoney);
            this.highestUser.setText("User: "+highestUser);
            this.lowestMoney.setText("Lowest Money: "+lowestMoney);
            this.lowestUser.setText("User: "+lowestUser);
            moneyData.setName("Player");
            moneyGraph.getData().add(moneyData);
            moneyGraph.setAnimated(false);
            moneyCounter++;
        }
        toggleTeacherVisibility();
        toggleGraphsVisibility();
    }

    /**
     * Handles the event when the user clicks on the "Show Student Stats" button.
     */
    @FXML
    public void onShowStudentStatsClick() {
        AudioPlayer.playClick();
        toggleTeacherVisibility();
        toggleStatsVisibility();
        refreshStudents();
    }

    /**
     * Handles the event when the user is finished viewing the student stats.
     */
    @FXML
    public void onStudentStatsFinishedClick() {
        toggleTeacherVisibility();
        toggleStatsVisibility();
    }

    /**
     * Handles the event when the user clicks on the "Refresh" button.
     */
    @FXML
    public void onRefreshClick(){
        AudioPlayer.playClick();
        newSort();
    }
}