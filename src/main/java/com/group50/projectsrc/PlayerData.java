package com.group50.projectsrc;

import java.util.ArrayList;

// Encapsulates all the data for a single player that needs to be saved after days
// so that it can be loaded and saved
// their stocks
// their money
/**
 * Encapsulates all the data for a single player that needs to be saved after days
 * so that it can be loaded and saved, including their stocks and money.
 */
public class PlayerData {
    private String username;
    private String password;
    private float money;
    private boolean finished; // has this player finished the game
    ArrayList<StockHolding> stockHoldings;
    private int day; // what day are they on
    private int progressionLevel; // 1 - 4

    /**
     * Constructor for initializing a PlayerData object with all attributes.
     *
     * @param username         The username of the player.
     * @param password         The password of the player.
     * @param money            The amount of money the player has.
     * @param finished         Whether the player has finished the game or not.
     * @param stockHoldings    The list of stock holdings of the player.
     * @param day              The day the player is currently on.
     * @param progressionLevel The progression level of the player.
     */
    public PlayerData(String username, String password, float money, boolean finished, ArrayList<StockHolding> stockHoldings, int day, int progressionLevel) {
        this.username = username;
        this.password = password;
        this.money = money;
        this.finished = finished;
        this.stockHoldings = stockHoldings;
        this.day = day;
        this.progressionLevel = progressionLevel;
    }

    /**
     * Checks if the provided password matches the player's password.
     *
     * @param password The password to check.
     * @return True if the password matches, false otherwise.
     */
    boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Static method to get test data for players.
     *
     * @return An ArrayList containing test PlayerData objects.
     */
    static ArrayList<PlayerData> getTestData() {
        var data = new ArrayList<PlayerData>();
        ArrayList<StockHolding> stockHoldings = new ArrayList<>();
        stockHoldings.add(new StockHolding(1, 2));
        stockHoldings.add(new StockHolding(11, 12412));
        stockHoldings.add(new StockHolding(6, 98));
        data.add(new PlayerData("Alex", "pass", 0, true, stockHoldings, 2, 3));
        data.add(new PlayerData("Josh", "pass", 41, true, stockHoldings, 1, 1));
        data.add(new PlayerData("Duncan", "pass", 6234, false, stockHoldings, 1, 3));
        data.add(new PlayerData("Affan", "pass", 1407955, false, stockHoldings, 3, 2));
        data.add(new PlayerData("Lyndon", "pass", 1297600, true, stockHoldings, 365, 70));
        return data;
    }

    /**
     * Adds a stock holding to the player's stock holdings list.
     *
     * @param h The stock holding to add.
     */
    public void addStockHolding(StockHolding h) {
        int id = h.getStockID();
        for (var stock : stockHoldings) {
            if (stock.getStockID() == id) {
                stock.setQuantity(stock.getQuantity() + h.getQuantity());
                return;
            }
        }

        stockHoldings.add(h);
    }

    /**
     * Retrieves the stock holding with the given ID from the player's stock holdings list.
     * If not found, creates a new stock holding with zero quantity.
     *
     * @param ID The ID of the stock holding to retrieve.
     * @return The stock holding object.
     */
    public StockHolding getStockHolding(int ID) {
        for (var holding : stockHoldings) {
            if (holding.getStockID() == ID)
                return holding;
        }

        stockHoldings.add(new StockHolding(ID, 0));
        return getStockHolding(ID);
    }

    /**
     * Sets the stock holding for the player.
     *
     * @param h The stock holding to set.
     */
    public void setStockHolding(StockHolding h) {
        int id = h.getStockID();
        for (var stock : stockHoldings) {
            if (stock.getStockID() == id) {
                stock.setQuantity(h.getQuantity());
                return;
            }
        }

        stockHoldings.add(h);
    }

    /**
     * Getter method for the progression level of the player.
     *
     * @return The progression level.
     */
    public int getProgresionLevel() {
        return progressionLevel;
    }

    /**
     * Setter method for the progression level of the player.
     *
     * @param progressionLevel The progression level to set.
     */
    public void setProgressionLevel(int progressionLevel) {
        this.progressionLevel = progressionLevel;
    }

    /**
     * Getter method for the username of the player.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for the username of the player.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter method for the amount of money the player has.
     *
     * @return The amount of money.
     */
    public float getMoney() {
        return money;
    }

    /**
     * Setter method for the amount of money the player has.
     *
     * @param money The amount of money to set.
     */
    public void setMoney(float money) {
        this.money = money;
    }

    /**
     * Getter method for the game completion status of the player.
     *
     * @return True if the player has finished the game, false otherwise.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Setter method for the game completion status of the player.
     *
     * @param finished The game completion status to set.
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Getter method for the password of the player.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter method for the current day the player is on.
     *
     * @return The current day.
     */
    public int getDay() {
        return day;
    }

    /**
     * Getter method for the list of stock holdings of the player.
     *
     * @return The list of stock holdings.
     */
    public ArrayList<StockHolding> getStockHoldings() {
        return stockHoldings;
    }

    /**
     * Setter method for setting a players day
     * @param d sets the day for the player
     */
    public void setDay(int d) {
        day = d;
    }
}