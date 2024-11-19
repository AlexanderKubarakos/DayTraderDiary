package com.group50.projectsrc;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The EODScreenController class controls the end of day screen UI.
 * It displays the previous balance, current balance, balance difference, and list of stocks.
 * @author Josh Spasic
 * @author Duncan Finlayson
 * @author Alexander Kubarakos
 */

public class EODScreenController {

    @FXML
    private Text prevBal;
    @FXML
    private Text currBal;
    @FXML
    private Text balDiff;
    @FXML
    private Text tLevel;
    @FXML
    private Text tUnlock;

    @FXML
    private Text tLevelUp;

    @FXML
    private Text liqBalDiff;

    @FXML
    private Text prevLiqBal;

    @FXML
    private Text liqCurrBal;

    @FXML
    private TextArea sharesList;

    /**
     * Initializes the controller.
     */
    public void initialize() {
        // You can initialize or do further setup here
    }

    /**
     * Loads the balance information and updates the UI.
     * @param prevBalance The previous balance.
     * @param balanceDiff The balance difference.
     * @param currBalanace The current balance.
     * @param liqBalanceDiff difference of liquid balance
     * @param liqCurrBalance current liquid balance
     * @param prevLiqBalance previous liquid balance
     */
    public void loadBal(float prevBalance, float balanceDiff, float currBalanace, float liqBalanceDiff, float liqCurrBalance, float prevLiqBalance ) {
        prevBal.setText("Previous Balance: " + String.format("%.2f", prevBalance));
        balDiff.setText("Balance Difference: " + String.format("%.2f", balanceDiff));
        currBal.setText("Current Balance: " + String.format("%.2f", currBalanace));
        liqBalDiff.setText("Liquid Balance Difference: " + String.format("%.2f", liqBalanceDiff));
        liqCurrBal.setText("Current Liquid Balance: " + String.format("%.2f", liqCurrBalance));
        prevLiqBal.setText("Previous Liquid Balance: " + String.format("%.2f", prevLiqBalance));

        if (balanceDiff<0){
            balDiff.setFill(Color.RED);
        } else {balDiff.setFill(Color.GREEN); }
        if (liqBalanceDiff<0){
            liqBalDiff.setFill(Color.RED);
        } else {liqBalDiff.setFill(Color.GREEN); }
    }

    /**
     * Loads the list of stocks and updates the UI.
     * @param stockList The list of stock holdings.
     */

    public void loadStocks(ArrayList<StockHolding> stockList) {
        String s = "";
        StockMarket market = StockMarket.getInstance(); // REMOVE i think
        for (int i=0; i<stockList.size(); i++) {
            String name = market.getStockByID(stockList.get(i).getStockID()).getName();
            s+= name + ": " + stockList.get(i).getQuantity() + "\n";
        }
        sharesList.setText(s);
    }

    /**
     * Display levelup text
     * @param level what level they are
     * @param levelup what they leveled up to
     * @param nextToLevelUp what is the next level
     */
    public void levelText(int level, int levelup, float nextToLevelUp) {
        tUnlock.setText("");
        switch(level) {
            case 4:
                tLevel.setText("You are the Max level!");
                tLevelUp.setText("Congratulations!");

                if (levelup==1) {
                    tUnlock.setText("You unlocked the LUXURY sector");
                } else if (levelup==2) {
                    tUnlock.setText("You unlocked the BANKING and LUXURY sectors");
                } else if (levelup==3) {
                    tUnlock.setText("You unlocked the FOOD, BANKING and LUXURY sectors");
                }

                break;
            case 3:

                tLevel.setText("Level: 3");
                tLevelUp.setText(String.format("Earn $%.2f more money to unlock the LUXURY sector", nextToLevelUp));
                if (levelup==1) {
                    tUnlock.setText("You unlocked the BANKING sector");
                } else if (levelup==2) {
                    tUnlock.setText("You unlocked the FOOD and BANKING sectors");
                }

                break;
            case 2:
                tLevel.setText("Level: 2");
                tLevelUp.setText(String.format("Earn $%.2f more money to unlock the BANKING sector", nextToLevelUp));
                if (levelup==1) {
                    tUnlock.setText("You unlocked the FOOD sector");
                }

                break;
            default:
                tLevel.setText("Level: 1");
                tLevelUp.setText(String.format("Earn $%.2f more money to unlock the FOOD sector", nextToLevelUp));
        } // TECH FOOD BANKING LUXURY

    }


    /**
     * Handles the action when the user is done viewing the end of day screen.
     */
    @FXML
    private void done() {
        if (GameState.getInstance().getActivePlayerData().getDay() > 5) {
            GameState.getInstance().getActivePlayerData().setFinished(true);
            SceneManager.getInstance().activate("EndGameScreen");
            ((EndGameScreenController)SceneManager.getInstance().getController("EndGameScreen")).updateUI();
            return;
        }
        SceneManager.getInstance().activate("LobbyScreen");
        LobbyScreen controller = (LobbyScreen)SceneManager.getInstance().getController("LobbyScreen");
        controller.updateUI();
    }
}