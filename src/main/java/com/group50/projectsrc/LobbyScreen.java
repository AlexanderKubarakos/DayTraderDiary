package com.group50.projectsrc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The LobbyScreen class manages the lobby screen GUI.
 * @author Alexander Kubarakos
 * @author Josh Spasic
 * @author Lyndon Zhang
 */
public class LobbyScreen {

    @FXML
    private Button logOutButton;
    @FXML
    private Text currentDay;
    @FXML
    private Text tLevel;
    @FXML
    private Text currentMoney;
    @FXML
    private Text daysLeft;

    @FXML
    private Text tLiqMoney;

    /**
     * Handles the action to proceed to the next day.
     */
    @FXML
    private void nextDay() {
        SceneManager.getInstance().activate("GameScreen");
        SceneManager.getInstance().clearHistory();
        GameState.getInstance().startDay();
        AudioPlayer.stopMusic();
        AudioPlayer.playGameMusic();
    }

    /**
     * Handles the action to log out.
     */
    @FXML
    private void logOut() {
        GameState.getInstance().setActivePlayerData(null);
        SceneManager.getInstance().activate("MainMenu");
        SceneManager.getInstance().clearHistory();
        AudioPlayer.stopMusic();
        AudioPlayer.playMenuMusic();
    }

    /**
     * Updates the lobby screen UI.
     */
    public void updateUI() {
        currentDay.setText(String.valueOf(GameState.getInstance().getActivePlayerData().getDay()));
        currentMoney.setText(String.format("$%.2f", GameState.getInstance().getActivePlayerData().getMoney()));
        daysLeft.setText(String.valueOf(6 - GameState.getInstance().getActivePlayerData().getDay()));
        tLevel.setText("Current Level: " + String.valueOf(GameState.getInstance().getActivePlayerData().getProgresionLevel()));

        float liqCurrBalance = GameState.getInstance().getActivePlayerData().getMoney();
        for (int i = 0; i < 12; i++) {
            liqCurrBalance += GameState.getInstance().getActivePlayerData().getStockHoldings().get(i).getQuantity() * StockMarket.getInstance().getStockByID(i+1).getPrice();
        }
        tLiqMoney.setText(String.format("Liquidated Balance: $%.2f", liqCurrBalance));

    }

    /**
     * Restarts the player's account.
     * @param actionEvent The action event triggering the account restart.
     */
    public void restartAccount(ActionEvent actionEvent) {
        StockMarket.getInstance().generateRandomMarket();
        PlayerData data = GameState.getInstance().getActivePlayerData();
        for (var holding : data.getStockHoldings()) {
            holding.setQuantity(0);
        }
        data.setMoney(1000);
        data.setDay(1);
        data.setProgressionLevel(1);
        try {
            PlayerDataManager.savePlayerData(GameState.getInstance().getActivePlayerData().getUsername());
        } catch (IOException e) {
            System.out.println("Failed to save player data on restart account");
        }
        updateUI();
    }
}
