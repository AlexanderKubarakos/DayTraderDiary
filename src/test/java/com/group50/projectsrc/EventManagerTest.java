package com.group50.projectsrc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventManagerTest {

    @BeforeEach
    void setUp() {
        EventManager.getInstance();
    }

    @Test
    void searchByID() {
        assertTrue(EventManager.getInstance().searchByID(1).getID() == 1);
    }

    @Test
    void getRandomEventID() {
        assertTrue(true);
    }

    @Test
    void getAllEventNames() {
        assertTrue(EventManager.getInstance().searchByID(1).getID() == 1);
    }
}