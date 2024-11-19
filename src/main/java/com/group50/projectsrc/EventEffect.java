package com.group50.projectsrc;


/**
 * The EventEffect class contains all the effects an event will have on a stock.
 * @author Alexander Kubarakos
 * @author Josh Spasic
 * @author Duncan Finlayson
 */
public class EventEffect {
    private boolean groupEffect;
    private String stockNameToAffect;
    private StockGroup stockGroupToAffect;
    private float deltaExpectedReturn;
    private float deltaStandardDeviation;
    private float deltaPrice;

    /**
     * Constructs a new EventEffect object.
     * @param groupEffect Indicates whether this event affects a group of stocks.
     * @param stockNameToAffect The name of the individual stock this event will affect.
     * @param stockGroupToAffect The stock group to be affected by this event.
     * @param deltaExpectedReturn The change in expected return to apply to the stock.
     * @param deltaStandardDeviation The change in standard deviation to apply to the stock.
     * @param deltaPrice The change in price to apply to the stock.
     */
    public EventEffect(boolean groupEffect, String stockNameToAffect, StockGroup stockGroupToAffect,
                       float deltaExpectedReturn, float deltaStandardDeviation, float deltaPrice) {
        this.groupEffect = groupEffect;
        this.stockGroupToAffect = stockGroupToAffect;
        this.stockNameToAffect = stockNameToAffect;
        this.deltaExpectedReturn = deltaExpectedReturn;
        this.deltaStandardDeviation = deltaStandardDeviation;
        this.deltaPrice = deltaPrice;
    }

    /**
     * Checks if this event affects a group of stocks.
     * @return True if this event affects a group of stocks, false otherwise.
     */
    public boolean isGroupEffect() {
        return groupEffect;
    }

    /**
     * Gets the name of the individual stock this event will affect.
     * @return The name of the individual stock.
     */
    public String getStockNameToAffect() {
        return stockNameToAffect;
    }

    /**
     * Gets the stock group to be affected by this event.
     * @return The stock group to be affected.
     */
    public StockGroup getStockGroupToAffect() {
        return stockGroupToAffect;
    }

    /**
     * Gets the change in expected return to apply to the stock.
     * @return The change in expected return.
     */
    public float getDeltaExpectedReturn() {
        return deltaExpectedReturn;
    }

    /**
     * Gets the change in standard deviation to apply to the stock.
     * @return The change in standard deviation.
     */
    public float getDeltaStandardDeviation() {
        return deltaStandardDeviation;
    }

    /**
     * Gets the change in price to apply to the stock.
     * @return The change in price.
     */
    public float getDeltaPrice() {
        return deltaPrice;
    }

    /**
     * Returns a string representation of the event effect.
     * @return The string representation of the event effect.
     */
    @Override
    public String toString() {
        return "EventEffect{" +
                "groupEffect=" + groupEffect +
                ", stockNameToAffect='" + stockNameToAffect + '\'' +
                ", stockGroupToAffect=" + stockGroupToAffect +
                ", deltaExpectedReturn=" + deltaExpectedReturn +
                ", deltaStandardDeviation=" + deltaStandardDeviation +
                ", deltaPrice=" + deltaPrice +
                '}';
    }
}