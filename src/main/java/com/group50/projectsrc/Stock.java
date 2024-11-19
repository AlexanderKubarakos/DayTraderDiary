package com.group50.projectsrc;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Represents a stock that can be traded on the stock market.
 * This class maintains information about the stock's ID, label, name, group, price,
 * expected return, standard deviation of returns, and price history.
 * @author Duncan Finlayson
 * @author Alexander Kubarakos
 */
public class Stock {

    private int ID;
    private String label; // 3-letter code name ex. APL
    private String name; // name of company ex. Apple
    private StockGroup group; // what group stock is part of

    private float price; // how much is it worth
    private float expectedReturn; // What price this stock should trend towards
    private float SDOfReturns; // Standard deviation of stock, this is the jitter in the price

    private Integer frameCount = 0;
    private ArrayList<Pair<Integer, Float>> priceHistory;

    /**
     * Constructs a Stock object with randomly generated price, expected return, and standard deviation of returns.
     *
     * @param ID    The unique identifier of the stock.
     * @param label The 3-letter code name of the stock.
     * @param name  The name of the company.
     * @param group The group the stock belongs to.
     */
    public Stock(int ID, String label, String name, StockGroup group) {
        price = randomRange(25, 75);
        this.ID = ID;
        this.label = label;
        this.name = name;
        this.group = group;
        expectedReturn = randomRange(-15, 15);
        SDOfReturns = randomRange(5.0f, 10.0f);
        priceHistory = new ArrayList<>();
    }

    /**
     * Constructs a Stock object with specified price, expected return, and standard deviation of returns.
     *
     * @param ID             The unique identifier of the stock.
     * @param label          The 3-letter code name of the stock.
     * @param name           The name of the company.
     * @param group          The group the stock belongs to.
     * @param price          The price of the stock.
     * @param expectedReturn The expected return of the stock.
     * @param SDOfReturns    The standard deviation of returns of the stock.
     */
    public Stock(int ID, String label, String name, StockGroup group, float price, float expectedReturn, float SDOfReturns) {
        this.price = price;
        this.ID = ID;
        this.label = label;
        this.name = name;
        this.group = group;
        this.expectedReturn = expectedReturn;
        this.SDOfReturns = SDOfReturns;
        priceHistory = new ArrayList<>();
    }

    /**
     * Generates a random number within a specified range.
     *
     * @param low  The lower bound of the range.
     * @param high The upper bound of the range.
     * @return A random number within the specified range.
     */
    float randomRange(float low, float high) {
        return (float) (low + Math.random() * (high - low));
    }

    /**
     * Simulates a single time unit tick for the stock, updating its price.
     */
    public void tick() {
        final float dt = 0.0005f;
        priceHistory.add(new Pair<>(frameCount++, price));
        if (priceHistory.size() > 80)
            priceHistory.remove(0);
        price += (float) (price * (expectedReturn * dt + SDOfReturns * (Math.random() - 0.5) * Math.sqrt(dt)));
        if (price < 5) {
            expectedReturn = 7;
            price = 5;
        }
    }

    /**
     * Applies the effect of an event on the stock's price, expected return, and standard deviation of returns.
     *
     * @param effect The effect of the event on the stock.
     */
    public void applyEventEffect(EventEffect effect) {
        price += effect.getDeltaPrice();
        expectedReturn += effect.getDeltaExpectedReturn();
        SDOfReturns += effect.getDeltaStandardDeviation();
    }

    /**
     * Gets the ID of the stock.
     *
     * @return The ID of the stock.
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the label of the stock.
     *
     * @return The label of the stock.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets the name of the company associated with the stock.
     *
     * @return The name of the company.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the group to which the stock belongs.
     *
     * @return The group of the stock.
     */
    public StockGroup getGroup() {
        return group;
    }

    /**
     * Gets the current price of the stock.
     *
     * @return The current price of the stock.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the price of the stock.
     *
     * @param price The new price of the stock.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets the expected return of the stock.
     *
     * @return The expected return of the stock.
     */
    public float getExpectedReturn() {
        return expectedReturn;
    }

    /**
     * Sets the expected return of the stock.
     *
     * @param expectedReturn The new expected return of the stock.
     */
    public void setExpectedReturn(float expectedReturn) {
        this.expectedReturn = expectedReturn;
    }

    /**
     * Gets the standard deviation of returns of the stock.
     *
     * @return The standard deviation of returns of the stock.
     */
    public float getStandardDeviation() {
        return SDOfReturns;
    }

    /**
     * Sets the standard deviation of returns of the stock.
     *
     * @param SDOfReturns The new standard deviation of returns of the stock.
     */
    public void setStandardDeviation(float SDOfReturns) {
        this.SDOfReturns = SDOfReturns;
    }

    /**
     * Gets the price history of the stock.
     *
     * @return The price history of the stock.
     */
    public ArrayList<Pair<Integer, Float>> getPriceHistory() {
        return priceHistory;
    }

    /**
     * Restarts the price history of the stock.
     */
    public void restartHistory() {
        frameCount = 0;
        priceHistory.clear();
    }
}
