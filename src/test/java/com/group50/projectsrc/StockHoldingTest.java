package com.group50.projectsrc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockHoldingTest {
    StockHolding h;

    @BeforeEach
    void setUp() {
        h = new StockHolding(1, 2);
    }

    @Test
    void getStockID() {
        assertTrue(h.getStockID() == 1);
    }

    @Test
    void getQuantity() {
        assertTrue(h.getQuantity() == 2);
    }

    @Test
    void setQuantity() {
        h.setQuantity(10);
        assertTrue(h.getQuantity() == 10);
    }
}