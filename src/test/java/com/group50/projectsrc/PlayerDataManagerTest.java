package com.group50.projectsrc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.group50.projectsrc.PlayerDataManager.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerDataManagerTest {
    PlayerData data;
    @BeforeEach
    public void setup() throws IOException {
        createNewPlayerData("Tester","TesterPass");
        ArrayList<StockHolding> stockHoldingsArray = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            StockHolding s = new StockHolding(i+1,0);
            stockHoldingsArray.add(s);
        }
        this.data = new PlayerData("Tester","TesterPass", 1000.0F, false, stockHoldingsArray,1,1);
    }

    @Test
    public void checkLoadingDataFile(){
        loadPlayerFile("Tester");
        assertEquals(data.getUsername(),GameState.getInstance().getActivePlayerData().getUsername());
        assertEquals(data.getPassword(),GameState.getInstance().getActivePlayerData().getPassword());
        assertEquals(data.getMoney(),GameState.getInstance().getActivePlayerData().getMoney());
        assertEquals(data.isFinished(),GameState.getInstance().getActivePlayerData().isFinished());
        assertEquals(data.getDay(),GameState.getInstance().getActivePlayerData().getDay());
        assertEquals(data.getProgresionLevel(),GameState.getInstance().getActivePlayerData().getProgresionLevel());
        for (int i = 0; i < 12; i++) {
            assertEquals(data.getStockHolding(i+1).getQuantity(),GameState.getInstance().getActivePlayerData().getStockHolding(i+1).getQuantity());
        }
    }

    @Test
    public void checkCreateNewPlayerData() throws IOException{
        createNewPlayerData("TestCreateUsername","TestCreatePassword");
        assertTrue(loadPlayerFile("TestCreateUsername"));
    }

    @Test
    public void checkSavePlayerData() throws IOException {
    assertTrue(savePlayerData("Tester"));

    }


    @AfterAll
    public static void tearDown() throws IOException {
        File file1 = new File("PlayerData/TestCreateUsername.data");
        File file2 = new File("PlayerData/Tester.data");
        file1.delete();
        file2.delete();

    }
}