package com.group50.projectsrc;

import java.util.ArrayList;
import java.util.NoSuchElementException;

// Represents a stock market
/**
 * The StockMarket class represents a virtual stock market system that manages various stocks and their behaviors.
 * It provides methods to generate a random market, access stocks by different identifiers, apply event effects to stocks,
 * retrieve information about stocks, and manage stock histories.
 * @author Alexander Kubarakos
 * @author Duncan Finlayson
 */
public class StockMarket {

    static StockMarket instance;

    /**
     * ArrayList to hold all the stocks in the market.
     */
    ArrayList<Stock> stocks = new ArrayList<>();

    /**
     * Private constructor to ensure Singleton pattern is followed.
     * Generates a random market upon instantiation.
     */
    private StockMarket() {
        generateRandomMarket();
    }

    /**
     * Retrieves the singleton instance of the StockMarket.
     * @return The singleton instance of the StockMarket.
     */
    static StockMarket getInstance() {
        if (instance == null) {
            instance = new StockMarket();
        }

        return instance;
    }

    /**
     * Retrieves all stocks present in the market.
     * @return An ArrayList containing all the stocks in the market.
     */
    ArrayList<Stock> getAllStocks() {
        return stocks;
    }

    /**
     * Generates a random market by adding various stocks to the market.
     */
    public void generateRandomMarket() {
        stocks.clear();
        addStock(new Stock(1, "APL", "Apple", StockGroup.TECH))
                .addStock(new Stock(2, "NVDA", "Nvidia", StockGroup.TECH))
                .addStock(new Stock(3, "INT", "Intel", StockGroup.TECH))

                .addStock(new Stock(4, "LOB", "Loblaws", StockGroup.FOOD))
                .addStock(new Stock(5, "MET", "Metro Foods", StockGroup.FOOD))
                .addStock(new Stock(6, "GOR", "Gordon Foods", StockGroup.FOOD))

                .addStock(new Stock(7, "TDB", "Toronto Dominion Bank", StockGroup.BANKING))
                .addStock(new Stock(8, "SCO", "Scotia Bank", StockGroup.BANKING))
                .addStock(new Stock(9, "BMO", "Bank Of Montreal", StockGroup.BANKING))

                .addStock(new Stock(10, "SUN", "Sunwing Airlines", StockGroup.LUXURY))
                .addStock(new Stock(11, "BEW", "Best Western", StockGroup.LUXURY))
                .addStock(new Stock(12, "ROR", "Rolls-Royce", StockGroup.LUXURY));
    }

    /**
     * Updates the market by ticking each stock present in the market.
     */
    public void tickMarket() {
        for (var stock : stocks) {
            stock.tick();
        }
    }

    /**
     * Retrieves a stock based on its label.
     * @param label The label of the stock to be retrieved.
     * @return The stock with the specified label.
     * @throws NoSuchElementException If no stock with the specified label is found.
     */
    public Stock getStockByLabel(String label) throws NoSuchElementException {
        for (var stock : stocks) {
            if (stock.getLabel().equals(label)) {
                return stock;
            }
        }

        throw new NoSuchElementException("No stock with label: " + label);
    }

    /**
     * Retrieves a stock based on its name.
     * @param name The name of the stock to be retrieved.
     * @return The stock with the specified name.
     * @throws NoSuchElementException If no stock with the specified name is found.
     */
    public Stock getStockByName(String name) throws NoSuchElementException {
        for (var stock : stocks) {
            if (stock.getName().equals(name)) {
                return stock;
            }
        }

        throw new NoSuchElementException("No stock with name: " + name);
    }

    /**
     * Retrieves a stock based on its ID.
     * @param ID The ID of the stock to be retrieved.
     * @return The stock with the specified ID.
     * @throws NoSuchElementException If no stock with the specified ID is found.
     */
    public Stock getStockByID(int ID) throws NoSuchElementException {
        for (var stock : stocks) {
            if (stock.getID() == ID) {
                return stock;
            }
        }

        throw new NoSuchElementException("No stock with ID: " + ID);
    }

    /**
     * Adds a stock to the market.
     * @param s The stock to be added.
     * @return The StockMarket instance.
     */
    public StockMarket addStock(Stock s) {
        stocks.add(s);
        return this;
    }

    /**
     * Applies an event effect to the stocks in the market based on the provided EventEffect object.
     * @param effect The EventEffect object representing the effect to be applied.
     */
    public void applyEventEffect(EventEffect effect) {
        ArrayList<Stock> stocksToEffect = new ArrayList<>();

        if (effect.isGroupEffect()) {
            if (effect.getStockGroupToAffect() == null) {
                System.out.println("Invalid EventEffect stock group to effect is null");
            }

            for (var stock : stocks) {
                if (stock.getGroup() == effect.getStockGroupToAffect()) {
                    stocksToEffect.add(stock);
                }
            }
        } else {
            if (effect.getStockNameToAffect() == null) {
                System.out.println("Invalid EventEffect stock name to effect is null");
            }

            for (var stock : stocks) {
                if (stock.getName().equals(effect.getStockNameToAffect())) {
                    stocksToEffect.add(stock);
                    break;
                }
            }
        }

        for (var stock : stocksToEffect) {
            stock.applyEventEffect(effect);
        }
    }

    /**
     * Retrieves the names of all stocks present in the market.
     * @return An ArrayList containing the names of all stocks in the market.
     */
    ArrayList<String> getAllStockNames() {
        ArrayList<String> r = new ArrayList<>();
        for (var stock : stocks) {
            r.add(stock.getName());
        }

        return r;
    }

    /**
     * Clears the historical data of all stocks in the market.
     */
    public void clearAllStockHistory() {
        for (var stock : stocks) {
            stock.restartHistory();
        }
    }
}
