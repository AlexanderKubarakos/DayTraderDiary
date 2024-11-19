package com.group50.projectsrc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventEffectTest {
    EventEffect e;
    @BeforeEach
    void setUp() {
        e = new EventEffect(true, "Apple", StockGroup.TECH, 10, 10, 10);
    }
    @Test
    void isGroupEffect() {
        assertTrue(e.isGroupEffect());
    }

    @Test
    void getStockNameToAffect() {
        assertTrue(e.getStockNameToAffect().equals("Apple"));
    }

    @Test
    void getStockGroupToAffect() {
        assertTrue(e.getStockGroupToAffect() == StockGroup.TECH);
    }

    @Test
    void getDeltaExpectedReturn() {
        assertTrue(e.getDeltaExpectedReturn() == 10);
    }

    @Test
    void getDeltaStandardDeviation() {
        assertTrue(e.getDeltaStandardDeviation() == 10);
    }

    @Test
    void getDeltaPrice() {
        assertTrue(e.getDeltaPrice() == 10);
    }
}