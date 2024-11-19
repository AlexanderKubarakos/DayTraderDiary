package com.group50.projectsrc;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.io.IOException;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Represents the game state including time, events, and player data.
 * @author Josh Spasic
 * @author Alexander Kubarakos
 */
public class GameState extends AnimationTimer {
    static private GameState instance;
    private PlayerData activePlayerData;

    private int ingameTime = 800;

    private int LEVEL2 = 1500;
    private int LEVEL3 = 2000;
    private int LEVEL4 = 2500;
    private int target = LEVEL2;




    private boolean running = false;

    private int currentlyPlayingNews = 0;

    private long prevFrameStartTime = 0;
    private float initialMoney = 0;
    private float prevLiquidMoney = 0;
    private ArrayList<Integer> randomEventTimes = new ArrayList<>(3);
    private ArrayDeque<Pair<Integer, Integer>> eventsToPlay = new ArrayDeque<>();
    /**
     * Retrieves the singleton instance of the GameState.
     *
     * @return The GameState instance.
     */
    static public GameState getInstance() {
        if (instance == null)
            instance = new GameState();
        return instance;
    }

    /**
     * Sets the active player data.
     *
     * @param data The player data to set as active.
     */
    public void setActivePlayerData(PlayerData data) {
        activePlayerData = data;
    }

    /**
     * Retrieves the active player data.
     *
     * @return The active player data.
     */
    public PlayerData getActivePlayerData() {
        return activePlayerData;
    }

    /**
     * Handles the game loop logic for each frame.
     *
     * @param l The timestamp of the current frame.
     */
    @Override
    public void handle(long l) {
        long time = System.nanoTime();
        if (200000000 <= time - prevFrameStartTime) {
            prevFrameStartTime = time;
            if (ingameTime%5==0) {
                StockMarket.getInstance().tickMarket();
            }
            ingameTime+=1;
            if (ingameTime%100 == 60) {
                ingameTime += 40;
            }

            for (var t : randomEventTimes) {
                if (ingameTime == t) {
                    int id = EventManager.getInstance().getRandomEventID();
                    while (!EventManager.getInstance().searchByID(id).eventPlayableAtProgressionLevel(GameState.getInstance().getActivePlayerData().getProgresionLevel()))
                    {
                        id = EventManager.getInstance().getRandomEventID();
                    }

                    displayEvent(id);
                    System.out.println("Displaying event with id: " + id);
                    eventsToPlay.push(new Pair<>(t, id));
                }
            }

            if (!eventsToPlay.isEmpty() ) {
                int t = eventsToPlay.peek().getKey() + 50;
                if (t % 100 > 59) {
                    t = t - t % 100 % 59;
                }
                if (ingameTime == t) {
                    System.out.println("Playing event with id: " + eventsToPlay.peek().getValue());
                    playEvent(eventsToPlay.pop().getValue());
                }
            }

            if (ingameTime > 1600)
                endDay();
        }
    }

    /**
     * Resumes the simulation.
     */
    public void resumeSimulation() {
        running = true;
        this.start();
    }

    /**
     * Ends the simulation.
     */
    public void endSimulation() {
        running = false;
        this.stop();
    }

    /**
     * Sets the in-game time to the specified value.
     *
     * @param time The new value of the in-game time.
     */
    public void setTime(int time) {
        ingameTime = time;
    }

    /**
     * Retrieves the current in-game time.
     *
     * @return The current in-game time.
     */
    public int getIngameTime() {
        return ingameTime;
    }

    /**
     * Checks whether the game simulation is currently running.
     *
     * @return true if the simulation is running, false otherwise.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * get level 2 cutoff
     * @return level 2 cutoff
     */
    public int getLEVEL2() {
        return LEVEL2;
    }
    /**
     * get level 3 cutoff
     * @return level 3 cutoff
     */
    public int getLEVEL3() {
        return LEVEL3;
    }
    /**
     * get level 4 cutoff
     * @return level 4 cutoff
     */
    public int getLEVEL4() {
        return LEVEL4;
    }
    /**
     * set target
     * @param setter target
     */
    public void setTarget(int setter) {
        target = setter;
    }
    /**
     * Plays the event with the specified ID by applying its effect on the stock market.
     *
     * @param ID The ID of the event to be played.
     */
    public void playEvent(int ID) {
        Event e = EventManager.getInstance().searchByID(ID);
        StockMarket.getInstance().applyEventEffect(e.getEffect());
    }

    /**
     * Displays the event with the specified ID on the game screen.
     *
     * @param ID The ID of the event to be displayed.
     */
    public void displayEvent(int ID) {
        Event e = EventManager.getInstance().searchByID(ID);
        var controller = (GameScreenController) SceneManager.getInstance().getController("GameScreen");
        controller.displayNews(e);
    }

    /**
     * Starts a new day in the game, resetting the in-game time and initiating random events.
     */
    public void startDay() {
        setTime(800);
        StockMarket.getInstance().clearAllStockHistory();
        resumeSimulation();
        initialMoney = GameState.getInstance().getActivePlayerData().getMoney();
        prevLiquidMoney = initialMoney;
        for (int i = 0; i < 12; i++) {
            prevLiquidMoney += GameState.getInstance().activePlayerData.getStockHoldings().get(i).getQuantity() * StockMarket.getInstance().getStockByID(i+1).getPrice();
        }
        randomEventTimes.clear();
        randomEventTimes.add(Math.round(randomTimeRange(850, 1000)));
        randomEventTimes.add(Math.round(randomTimeRange(1050, 1250)));
        randomEventTimes.add(Math.round(randomTimeRange(1300, 1400)));
    }

    /**
     * Generates a random time within the specified range.
     *
     * @param low  The lower bound of the time range.
     * @param high The upper bound of the time range.
     * @return The randomly generated time within the specified range.
     */
    float randomTimeRange(float low, float high) {
        Random ra = new Random(System.currentTimeMillis());
        float r = (float) (low + ra.nextFloat() * (high - low));
        //850 - 100
        // 860-899
        if (r % 100 > 59 )
            return r - r % 100 % 59;
        return r;
    }

    /**
     * Ends the current day in the game, updating player data and transitioning to the end-of-day screen.
     */
    public void endDay() {
        endSimulation();
        int levelUp=0;
        getActivePlayerData().setDay(getActivePlayerData().getDay() + 1);

        EODScreenController controller = (EODScreenController) SceneManager.getInstance().getController("EndOfDayScreen");
        controller.loadStocks(activePlayerData.getStockHoldings());


        float newBalance = activePlayerData.getMoney();
        float liqCurrBalance = activePlayerData.getMoney();
        for (int i = 0; i < 12; i++) {
            liqCurrBalance += activePlayerData.getStockHoldings().get(i).getQuantity() * StockMarket.getInstance().getStockByID(i+1).getPrice();
        }


        controller.loadBal(initialMoney, newBalance - initialMoney, newBalance, liqCurrBalance - prevLiquidMoney, liqCurrBalance, prevLiquidMoney);

        // PROGRESSION LEVEL
        if (liqCurrBalance>=LEVEL2&&activePlayerData.getProgresionLevel()<=1) {
            activePlayerData.setProgressionLevel(2);
            levelUp++;
            target = LEVEL3;
        }
        if (liqCurrBalance>=LEVEL3&&activePlayerData.getProgresionLevel()<=2) {
            activePlayerData.setProgressionLevel(3);
            levelUp++;
            target = LEVEL4;
        }
        if (liqCurrBalance>=LEVEL4&&activePlayerData.getProgresionLevel()<=3) {
            activePlayerData.setProgressionLevel(4);
            levelUp++;
        }


        controller.levelText(activePlayerData.getProgresionLevel(), levelUp, target-liqCurrBalance);
        // SAVING
        try {
            PlayerDataManager.savePlayerData(activePlayerData.getUsername());
        } catch (Exception e) {
            System.out.println(e);
        }

        SceneManager.getInstance().activate("EndOfDayScreen");
    }
}