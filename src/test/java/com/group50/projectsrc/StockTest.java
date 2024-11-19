package com.group50.projectsrc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    Stock stock;
    @Test
    void testRandomRange() {
        float f = stock.randomRange(0,1);
        assertTrue(f >= 0 || f <= 1);
    }

    @Test
    void testTick() {
        assertTrue(true);
    }

    @Test
    void testGetID() {
        assertTrue(stock.getID() == 1);
    }

    @Test
    void testGetLabel() {
        assertTrue(stock.getLabel().equals("A"));
    }

    @Test
    void testGetName() {
        assertTrue(stock.getName().equals("B"));
    }

    @Test
    void testGetGroup() {
        assertTrue(stock.getGroup()==StockGroup.TECH);
    }

    @Test
    void testGetPrice() {
        assertTrue(stock.getPrice() >= 25 || stock.getPrice() <= 75);
    }

    @Test
    void testSetPrice() {
        stock.setPrice(10.0f);
        assertTrue(stock.getPrice() == 10.0f);
    }

    @Test
    void testGetExpectedReturn() {
        stock.setExpectedReturn(10.0f);
        assertTrue(stock.getExpectedReturn() == 10.0f);
    }

    @Test
    void testSetExpectedReturn() {
        stock.setExpectedReturn(10.0f);
        assertTrue(stock.getExpectedReturn() == 10.0f);
    }

    @Test
    void testGetStandardDeviation() {
        stock.setStandardDeviation(10.0f);
        assertTrue(stock.getStandardDeviation() == 10.0f);
    }

    @Test
    void testSetStandardDeviation() {
        stock.setStandardDeviation(10.0f);
        assertTrue(stock.getStandardDeviation() == 10.0f);
    }

    @Test
    void testGetPriceHistory() {
        assertTrue(stock.getPriceHistory() != null);
    }

    @Test
    void testRestartHistory() {
        stock.restartHistory();
        assertTrue(stock.getPriceHistory().size() == 0);
    }

    @BeforeEach
    void setUp() {
        stock = new Stock(1, "A", "B", StockGroup.TECH);
    }
}