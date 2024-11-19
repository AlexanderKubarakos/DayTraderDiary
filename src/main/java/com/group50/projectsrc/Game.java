package com.group50.projectsrc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

/**
 * This is the main class of the game, it starts all systems needed to run game. Entry point.
 * @author Alexander Kubarakos
 * @author Lyndon Zhang
 */
public class Game extends Application {

    /**
     * Start of game
     * @param stage fxml parameter
     */
    @Override
    public void start(Stage stage) {
        setupStockMarket();
        stage.setResizable(false);
        setupScenes(stage);
        PlayerDataManager.readAllPlayersPlayerData("PlayerData/");
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Sets up stock market
     */
    private void setupStockMarket() {
        StockMarket.getInstance();
    }

    /**
     * sets up all fxml files for game
     * @param stage stage to set up scenes for
     */
    private void setupScenes(Stage stage) {
        SceneManager manager = SceneManager.getInstance();
        manager.setStage(stage);
        manager.addScreen("MainMenu", "main-menu.fxml");
        manager.addScreen("TeacherScreen", "teacher-mode.fxml");
        manager.addScreen("GameScreen", "GameScreen.fxml");
        manager.addScreen("LoginScreen", "MainFrame.fxml");
        manager.addScreen("LobbyScreen", "LobbyScreen.fxml");
        manager.addScreen("EndOfDayScreen", "EODScreen.fxml");
        manager.addScreen("EndGameScreen", "EndGameScreen.fxml");
        manager.activate("MainMenu");
        AudioPlayer.playMenuMusic();
    }
}