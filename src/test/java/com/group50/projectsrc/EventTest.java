package com.group50.projectsrc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    Event e;
    @BeforeEach
    void setUp() {
        EventEffect effect = new EventEffect(true, "Apple", StockGroup.TECH, 10, 10,10);
        e = new Event(effect, 1, "a.jpg", "a", "b");
    }

    @Test
    void getEffect() {
        assertTrue(e.getEffect().isGroupEffect());
    }

    @Test
    void getID() {
        assertTrue(e.getID()==1);
    }

    @Test
    void getImageLocation() {
        assertTrue(e.getImageLocation().equals("a.jpg"));
    }

    @Test
    void getTitle() {
        assertTrue(e.getTitle().equals("a"));
    }

    @Test
    void getDescription() {
        assertTrue(e.getDescription().equals("b"));
    }
}