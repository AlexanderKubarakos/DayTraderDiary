package com.group50.projectsrc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * The EndGameScreenController class controls the end game screen UI.
 * It handles actions such as logging out and updating the UI with final profit and money.
 * @author Alexander Kubarakos
 * @author Josh Spasic
 */
public class EndGameScreenController {
    @FXML
    private Button logout;
    @FXML
    private Text finalProfit;
    @FXML
    private Text finalMoney;

    /**
     * Logs out the active player and returns to the main menu.
     * @param actionEvent The action event triggered by clicking the logout button.
     */
    public void logOut(ActionEvent actionEvent) {
        AudioPlayer.playClick();
        try {
            GameState.getInstance().getActivePlayerData().setFinished(true);
            PlayerDataManager.savePlayerData(GameState.getInstance().getActivePlayerData().getUsername());
        } catch (Exception e) {
            System.out.println("Failed to save player data");
        }

        GameState.getInstance().setActivePlayerData(null);
        SceneManager.getInstance().activate("MainMenu");
    }

    /**
     * Updates the UI with the final profit and money of the active player.
     */
    public void updateUI() {
        var data = GameState.getInstance().getActivePlayerData();
        float money = data.getMoney();
        for (int i = 0; i < 12; i++) {
            money += data.getStockHoldings().get(i).getQuantity() * StockMarket.getInstance().getStockByID(i+1).getPrice();
        }

        finalMoney.setText(String.format("$%.2f", money));
        if (money-1000<0) {
            finalProfit.setFill(Color.RED);
        } else {
            finalProfit.setFill(Color.GREEN);
        }
        finalProfit.setText(String.format("$%.2f", money - 1000));
    }
}
