package com.group50.projectsrc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

/**
 * The LeaderboardApp class creates and manages the leaderboard GUI.
 * @author Alexander Kubarakos
 * @author Duncan Finlayson
 */
public class LeaderboardApp {

    /**
     * Creates the leaderboard GUI.
     */
    static VBox layout;
    static Scoreboard scoreboard;

    static Scene scene;

    /**
     * Create the leader board
     */
    public void create() {
        // Create labels to display player information
        scoreboard = new Scoreboard();

        // Create a layout to hold the labels
        layout = new VBox(10); // Vertical box layout with 10 pixels spacing
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))); // Set background color



        // Create and style labels for player data
        refreshBoard();


        // Create a scene and set the layout
        Scene scene = new Scene(layout, 960, 720);

        // Set the stage title and scene, then show the stage
        SceneManager.getInstance().addScreenFromScene("Leaderboard", scene);
    }

    /**
     * Refresh the entries on board
     */
    public void refreshBoard() {
        List<PlayerData> playerDataList = scoreboard.sortPlayerData();
        if (playerDataList == null)
            return;

        layout.getChildren().clear();

        // Add leaderboard title
        Font arcadeFont = Font.loadFont(getClass().getResourceAsStream("/fonts/ARCADE_N.TTF"), 25);
        Label titleLabel = new Label("LEADERBOARD");
        titleLabel.setFont(arcadeFont);
        titleLabel.setTextFill(Color.WHITE);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(titleLabel);
        // Create and style labels for player data
        for (int i = 0; i < playerDataList.size(); i++) {
            if (i == 5) {
                break;
            }

            PlayerDataManager.loadPlayerFile(playerDataList.get(i).getUsername());
            var player1 = GameState.getInstance().getActivePlayerData();
            float player1Money = GameState.getInstance().getActivePlayerData().getMoney();

            for (int k = 0; k < 12; k++) {
                player1Money += StockMarket.getInstance().getStockByID(k+1).getPrice() * player1.getStockHolding(k+1).getQuantity();
            }
            PlayerData player = playerDataList.get(i);
            String labelText = (i + 1) + ". " + player.getUsername() + " - $" + String.format("%.2f", player1Money);
            Label playerLabel = new Label(labelText);
            Font arcadeFont2 = Font.loadFont(getClass().getResourceAsStream("/fonts/ARCADE_N.TTF"), 16);
            playerLabel.setFont(arcadeFont2);
            playerLabel.setTextFill(Color.WHITE);
            layout.getChildren().add(playerLabel);
        }
        // Add a button to return to the main menu
        Button returnButton = new Button();
        returnButton.setOnAction(e -> SceneManager.getInstance().activate("MainMenu"));
        returnButton.setText("Return");
        returnButton.setTranslateX(-400);
        returnButton.setTranslateY(-350);
        returnButton.setMinWidth(100);
        returnButton.setMinHeight(25);
        layout.getChildren().add(returnButton);

        // Set padding for the layout
        layout.setPadding(new Insets(20));
    }
}
