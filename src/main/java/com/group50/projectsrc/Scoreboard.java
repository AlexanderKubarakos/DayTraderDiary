package com.group50.projectsrc;

import java.util.ArrayList;
import java.util.List;

/**
 * The Scoreboard class represents a scoreboard used in the leaderboards section of the game.
 * It sorts player data based on their scores and displays the top 5 scores of players who have finished the game.
 * @author Duncan Finlayson
 * @author Alexander Kubarakos
 */
public class Scoreboard {

    /**
     * Constructs a new Scoreboard object.
     */

    // Scoreboard Class
    // Creates a new scoreboard used in leaderboards section of our game

    public Scoreboard() {
        // Blank Constructor
    }


    // SortPlayerData Method
    // This method takes data from a player's save file and puts it into an array
    // This array first checks for players who have FINISHED the game then puts those into its own array
    // Then that array is sorted highest to lowest score
    // Finally it displays the top 5 scores

    /**
     * Sorts player data based on their scores and returns the top 5 scores of players who have finished the game.
     * @return A list of PlayerData objects representing the top 5 scores.
     */
    public List<PlayerData> sortPlayerData() {
        ArrayList<PlayerData> playerDataArray = PlayerDataManager.getData();
        if (playerDataArray == null)
            return null;

        // Filter out players who haven't finished the game
        List<PlayerData> finishedPlayers = new ArrayList<>();
        for (PlayerData player : playerDataArray) {
            if (player.isFinished()) {
                finishedPlayers.add(player);
            }
        }
        for (int i = 0; i < finishedPlayers.size() - 1; i++) {
            for (int j = 0; j < finishedPlayers.size() - i - 1; j++) {
                // sell all stocks for both players
                PlayerDataManager.loadPlayerFile(finishedPlayers.get(i).getUsername());
                var player1 = GameState.getInstance().getActivePlayerData();
                float player1Money = GameState.getInstance().getActivePlayerData().getMoney();

                for (int k = 0; k < 12; k++) {
                    player1Money += StockMarket.getInstance().getStockByID(k+1).getPrice() * player1.getStockHolding(k+1).getQuantity();
                }

                PlayerDataManager.loadPlayerFile(finishedPlayers.get(i+1).getUsername());
                var player2 = GameState.getInstance().getActivePlayerData();
                float player2Money = GameState.getInstance().getActivePlayerData().getMoney();
                for (int k = 0; k < 12; k++) {
                    player2Money += StockMarket.getInstance().getStockByID(k+1).getPrice() * player1.getStockHolding(k+1).getQuantity();
                }


                // Check if player at index j has more money than player
                if (player1Money < player2Money) {
                    // Swap players
                    PlayerData temp = finishedPlayers.get(j);
                    finishedPlayers.set(j, finishedPlayers.get(j + 1));
                    finishedPlayers.set(j + 1, temp);
                }
            }
        }
        return finishedPlayers;
    }
}

