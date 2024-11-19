package com.group50.projectsrc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controls main menu
 * @author Lyndon Zhang
 * @author Alexander Kubarakos
 */
public class MainController {
    @FXML
    private Slider sfxSlider;

    @FXML
    private Slider musicSlider;
    @FXML
    private Label titleText;
    @FXML
    private Pane titleScreen;
    @FXML
    private Pane leavingScreen;
    @FXML
    private Pane tutorialScreen;
    private int slideNum = 1;
    @FXML
    private Button tutorialLeftButton;
    @FXML
    private Button tutorialRightButton;
    @FXML
    private Pane tutorial1;
    @FXML
    private Pane tutorial2;
    @FXML
    private Pane tutorial3;
    @FXML
    private Pane tutorial4;
    @FXML
    private Pane tutorial5;
    @FXML
    private Pane tutorial6;
    @FXML
    private Pane tutorial7;
    @FXML
    private Pane tutorial8;
    @FXML
    private Pane settings;
    private final int[] title = new int[4];
    private int titleCount = 0;

    LeaderboardApp leaderboardApp;

    /**
     * Sets title of window
     */
    public void checkTitle(){
        if(titleCount == 4){
            if(title[0] == 1 && title[1] == 2 && title[2] == 1 && title[3] == 2) {
                titleText.setText("  Stock Monkey");
                titleCount = 0;
            }
            else{
                titleCount = 0;
            }
        }
    }
    private void toggleSettingVisibility(){settings.setVisible(!settings.isVisible());}
    private void toggleTitleVisibility() {
        titleScreen.setVisible(!titleScreen.isVisible());
    }
    private void toggleLeavingVisibility() {
        leavingScreen.setVisible(!leavingScreen.isVisible());
    }
    private void toggleTutorialVisibility() {
        tutorialScreen.setVisible(!tutorialScreen.isVisible());
    }

    /**
     * updates text on tutorial screen
     */
    private void updateTutorialSliders() {
        tutorialLeftButton.setVisible(slideNum != 1);
        tutorialRightButton.setVisible(slideNum != 8);
        tutorial1.setVisible(slideNum == 1);
        tutorial2.setVisible(slideNum == 2);
        tutorial3.setVisible(slideNum == 3);
        tutorial4.setVisible(slideNum == 4);
        tutorial5.setVisible(slideNum == 5);
        tutorial6.setVisible(slideNum == 6);
        tutorial7.setVisible(slideNum == 7);
        tutorial8.setVisible(slideNum == 8);
    }
    private void addTutorialSliders() {
        slideNum++;
    }
    private void subTutorialSliders() {
        slideNum--;
    }

    //Tutorial screen buttons.
    @FXML
    protected void onTutorialExitButtonClick() {
        AudioPlayer.playClick();
        toggleTitleVisibility();
        toggleTutorialVisibility();
    }

    @FXML
    protected void onTutorialLeftButtonClick() {
        AudioPlayer.playClick();
        subTutorialSliders();
        updateTutorialSliders();
    }
    @FXML
    protected void onTutorialRightButtonClick() {
        AudioPlayer.playClick();
        addTutorialSliders();
        updateTutorialSliders();
    }

    // Title screen Buttons.
    @FXML
    protected void onExitButtonClick() {
        AudioPlayer.playClick();
        //Terminate game
        title[titleCount] = 1;
        titleCount++;
        checkTitle();
        toggleTitleVisibility();
        toggleLeavingVisibility();
    }
    @FXML
    protected void onPlayButtonClick() {
        AudioPlayer.playClick();
        //Play game
        SceneManager.getInstance().activate("LoginScreen");
    }
    @FXML
    protected void onLeaderboardsButtonClick() {
        AudioPlayer.playClick();
        //Display Leaderboards
        leaderboardApp.refreshBoard();
        SceneManager.getInstance().activate("Leaderboard");
    }
    @FXML
    protected void onSettingsButtonClick() {
        toggleSettingVisibility();

    }
    @FXML
    protected void onTeacherModeButtonClick() {
        AudioPlayer.playClick();
        //Go to TeacherMode app.
        SceneManager.getInstance().activate("TeacherScreen");
    }
    @FXML
    protected void onTutorialButtonClick() {
        AudioPlayer.playClick();
        //Display Tutorial
        slideNum = 1;
        title[titleCount] = 2;
        titleCount++;
        checkTitle();
        toggleTitleVisibility();
        toggleTutorialVisibility();
        updateTutorialSliders();
    }

    // Leaving prompt screen buttons.
    @FXML
    private void onLeaveExitButtonClick() {
        AudioPlayer.playClick(); System.exit(0);
    }
    @FXML
    private void onLeaveCancelButtonClick() {
        //Display Tutorial
        AudioPlayer.playClick();
        toggleTitleVisibility();
        toggleLeavingVisibility();
    }
    @FXML
    public void audioConfirm() {
        double SFXLevel = sfxSlider.getValue();
        double MusicLevel = musicSlider.getValue();
        AudioPlayer.setMusic(MusicLevel);
        AudioPlayer.setSounds(SFXLevel);
    }

    @FXML
    public void ExitBtn(){
        toggleSettingVisibility();
    }

    /**
     * Start leaderboard
     */
    public void initialize() {
        leaderboardApp = new LeaderboardApp();
        leaderboardApp.create();
    }
}
