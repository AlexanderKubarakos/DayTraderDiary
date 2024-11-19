package com.group50.projectsrc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDataTest {

    PlayerData data;
    @BeforeEach
    void setUp() {
        ArrayList<StockHolding> holdings = new ArrayList<>();
        holdings.add(new StockHolding(1, 1));
        data = new PlayerData("Alex", "Password", 100.0f, true, holdings, 2, 2);
    }

    @Test
    void addStockHolding() {

    }

    @Test
    void getStockHolding() {
        assertTrue(data.getStockHolding(1).getQuantity() == 1);
    }

    @Test
    void setStockHolding() {
        data.getStockHolding(1).setQuantity(4);
        assertTrue(data.getStockHolding(1).getQuantity() == 4);
    }

    @Test
    void getProgresionLevel() {
        assertTrue(data.getProgresionLevel() == 2);
    }

    @Test
    void setProgressionLevel() {
        data.setProgressionLevel(2);
        assertTrue(data.getProgresionLevel() == 2);
    }

    @Test
    void getUsername() {
        assertTrue(data.getUsername().equals("Alex"));
    }

    @Test
    void setUsername() {
        data.setUsername("Alex");
        assertTrue(data.getUsername().equals("Alex"));
    }

    @Test
    void getMoney() {
        assertTrue(data.getMoney() == 100.0f);
    }

    @Test
    void setMoney() {
        data.setMoney(1.0f);
        assertTrue(data.getMoney() == 1.0f);
    }

    @Test
    void isFinished() {
        assertTrue(data.isFinished());
    }

    @Test
    void setFinished() {
        data.setFinished(false);
        assertTrue(!data.isFinished());
    }

    @Test
    void getPassword() {
        assertTrue(data.getPassword().equals("Password"));
    }

    @Test
    void getDay() {
        assertTrue(data.getDay() == 2);
    }

    @Test
    void getStockHoldings() {
        assertTrue(!data.getStockHoldings().isEmpty());
    }

    @Test
    void setDay() {
        data.setDay(3);
        assertTrue(data.getDay() == 3);
    }
}