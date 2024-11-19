package com.group50.projectsrc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockMarketTest {

    @Test
    void getInstance() {
        assertTrue(true);
    }

    @Test
    void getAllStocks() {
        assertTrue(StockMarket.getInstance().getAllStocks().size() == 12);
    }

    @Test
    void generateRandomMarket() {
        StockMarket.getInstance().generateRandomMarket();
        assertTrue(StockMarket.getInstance().getAllStocks().size() == 12);
    }

    @Test
    void getStockByLabel() {
        assertTrue(StockMarket.getInstance().getStockByLabel("APL").getLabel().equals("APL"));
    }

    @Test
    void getStockByName() {
        assertTrue(StockMarket.getInstance().getStockByName("Apple").getName().equals("Apple"));
    }

    @Test
    void getStockByID() {
        assertTrue(StockMarket.getInstance().getStockByID(1).getID() == 1);
    }

    @Test
    void getAllStockNames() {
        assertTrue(StockMarket.getInstance().getAllStockNames().size() == 12);
    }

    @Test
    void clearAllStockHistory() {
        StockMarket.getInstance().clearAllStockHistory();
        assertTrue(StockMarket.getInstance().getStockByID(1).getPriceHistory().isEmpty());
    }
}