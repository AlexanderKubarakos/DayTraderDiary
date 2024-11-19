package com.group50.projectsrc;

/**
 * Represents a holding of a particular stock by a player.
 * Contains information about the stock ID and the quantity held.
 * @author Alexander Kubarakos
 * @author Duncan Finlayson
 */
public class StockHolding {
    private int stockID;    // The ID of the stock held.
    private int quantity;   // The quantity of the stock held.

    /**
     * Constructs a new StockHolding object with the given stock ID and quantity.
     * @param stockID The ID of the stock.
     * @param quantity The quantity of the stock held.
     */
    public StockHolding(int stockID, int quantity) {
        this.stockID = stockID;
        this.quantity = quantity;
    }

    /**
     * Retrieves the ID of the stock.
     * @return The ID of the stock.
     */
    public int getStockID() {
        return stockID;
    }

    /**
     * Retrieves the quantity of the stock held.
     * @return The quantity of the stock held.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the stock held.
     * @param quantity The quantity of the stock held.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
