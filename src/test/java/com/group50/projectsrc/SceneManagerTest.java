package com.group50.projectsrc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SceneManagerTest {

    @BeforeEach
    void setUp() {
        SceneManager.getInstance();
    }

    @Test
    void getInstance() {
        SceneManager.getInstance();
        assertTrue(true);
    }
}