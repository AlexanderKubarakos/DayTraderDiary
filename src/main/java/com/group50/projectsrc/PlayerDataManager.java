package com.group50.projectsrc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages player data including loading, saving, and retrieving player information.
 * @author Alexander Kubarakos
 * @author Affan Ahmad
 */
public class PlayerDataManager {
    static ArrayList<PlayerData> data = new ArrayList<PlayerData>();

    private PlayerDataManager() {
        data = new ArrayList<>();
        reloadPlayerData();
    }

    private void reloadPlayerData() {
        File file = new File("PlayerData/");
        String[] paths;
        paths = file.list();

        if (paths == null) {
            System.out.println("Couldn't load files from directory /PlayerData/");
            return;
        }

        for (var path : paths) {
            PlayerData playerData = loadDataFile(path);
            if (playerData != null)
                data.add(playerData);
        }
    }

    private PlayerData loadDataFile(String filepath) {
        File file = new File("PlayerData/"+filepath);
        PlayerData playerData = null;
        try {
            Scanner scanner = new Scanner(file);
            String name = scanner.nextLine();
            String password = scanner.nextLine();
            float money = scanner.nextFloat();
            boolean finished = scanner.nextBoolean();
            int day = scanner.nextInt();
            int progression = scanner.nextInt();

            ArrayList<StockHolding> holdings = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int ID = scanner.nextInt();
                int quantity = scanner.nextInt();
                holdings.add(new StockHolding(ID, quantity));
            }

            playerData = new PlayerData(name, password, money, finished, holdings, day, progression);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to read playerdata file " + filepath);
        }

        return playerData;
    }

    /**
     * Loads player data file.
     * @param givenUsername The username of the player whose data is to be loaded.
     * @return if we loaded the file with no errors
     */
    public static boolean loadPlayerFile(String givenUsername) {
        File file = new File("PlayerData/" + givenUsername + ".data");
        try {
            Scanner line = new Scanner(file);
            readAllPlayersPlayerData("PlayerData/");
            PlayerData playerData = new PlayerData("","",0,false,null,0,0);
            for (PlayerData playerDataFile : data){
                if (playerDataFile.getUsername().equals(givenUsername)){
                    playerData = playerDataFile;
                }
            }
            while (!line.nextLine().equals("Stock Market Data - ID - Price - Expected Return - SD")){
            }
            StockMarket.getInstance().generateRandomMarket();
            for (int i = 0; i < 12; i++) {
                String currStockLine = line.nextLine();
                String[] currStock = currStockLine.split(" ");
                Stock stock = StockMarket.getInstance().getStockByID(i + 1);
                stock.setPrice(Float.parseFloat(currStock[1]));
                stock.setExpectedReturn(Float.parseFloat(currStock[2]));
                stock.setStandardDeviation(Float.parseFloat(currStock[3]));
            }
            GameState.getInstance().setActivePlayerData(playerData);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find save file for: " + givenUsername);
        }

        return false;
    }
    /**
     * Creates new player data.
     * @param username The username of the new player.
     * @param password The password of the new player.
     * @throws IOException If an I/O error occurs while creating the player data file.
     */
    public static void createNewPlayerData(String username, String password) throws IOException {
        String filePathAndName = "PlayerData/" + username + ".data";
        File newUserData = new File(filePathAndName);
        boolean ableToWrite = newUserData.createNewFile();
        if (ableToWrite) {
            System.out.println("was able to create new file");
        }
        try {
            FileWriter file = new FileWriter(filePathAndName);
            file.write(username + "\n"); // username
            file.write(password + "\n"); // password
            file.write("1000.0\n"); // money
            file.write("false\n"); // finished?
            file.write("1\n"); // day (level)
            file.write("1\n"); // progression level
            file.write("Stock Holdings\n");
            for (int i = 0; i < 12; i++) {
                file.write(i + 1 + " " + "0\n");
            }
            file.write("Stock Market Data - ID - Price - Expected Return - SD\n");
            StockMarket.getInstance().generateRandomMarket();
            for (int i = 0; i < 11; i++) {
                Stock s = StockMarket.getInstance().getStockByID(i + 1);
                file.write(s.getID() + " " + s.getPrice() + " " + s.getExpectedReturn() + " " + s.getStandardDeviation() + "\n");
            }
            Stock s = StockMarket.getInstance().getStockByID(12);
            file.write(s.getID() + " " + s.getPrice() + " " + s.getExpectedReturn() + " " + s.getStandardDeviation());
            file.close();
            readAllPlayersPlayerData("PlayerData/");
        } catch (Exception e) {
            System.out.println("Failed to create new player save file");
        }


    }
    /**
     * Reads all players' player data from files.
     * @param path The path to the directory containing player data files.
     */
    public static void readAllPlayersPlayerData(String path) {
        try {
            data = new ArrayList<PlayerData>();
            File directory = new File(path);
            File[] fileNames = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".data"));;
            assert fileNames != null;
            for (File file : fileNames) {
                Scanner line = new Scanner(file);
                String username = line.nextLine();
                String password = line.nextLine();
                String moneyStr = line.nextLine();
                float money = Float.parseFloat(moneyStr);
                String finishedStr = line.nextLine();
                boolean finished = Boolean.parseBoolean(finishedStr);
                String dayStr = line.nextLine();
                int day = Integer.parseInt(dayStr);
                String progStr = line.nextLine();
                int prog = Integer.parseInt(progStr);
                line.nextLine();
                ArrayList<StockHolding> stockHoldings = new ArrayList<StockHolding>();
                for (int i = 0; i < 12; i++) {
                    StockHolding s = new StockHolding(i + 1, 0);
                    String currStockHolding = line.nextLine();
                    String[] numbers = currStockHolding.split(" ");
                    s.setQuantity(Integer.parseInt(numbers[1]));
                    stockHoldings.add(s);
                }
                PlayerData playerData = new PlayerData(username, password, money, finished, stockHoldings, day, prog);
                data.add(playerData);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to read all player data in directory: " + path);
        }
    }
    /**
     * Saves player data to a file.
     *
     * @param username The username of the player whose data is to be saved.
     * @return
     * @throws IOException If an I/O error occurs while saving the player data.
     */
    public static boolean savePlayerData(String username) throws IOException {
        try{
            PlayerData playerData = GameState.getInstance().getActivePlayerData();
            ArrayList<Stock> stockMarket = StockMarket.getInstance().getAllStocks();
            File fileToUpdate = new File("PlayerData/" + username + ".data");
            FileWriter writer = new FileWriter(fileToUpdate);
            writer.write(playerData.getUsername() + "\n");
            writer.write(playerData.getPassword() + "\n");
            writer.write(playerData.getMoney() + "\n");
            writer.write(playerData.isFinished() + "\n");
            writer.write(playerData.getDay() + "\n");
            writer.write(playerData.getProgresionLevel() + "\n");
            writer.write("Stock Holdings\n");
            for (int i = 0; i < 12; i++) {
                writer.write(i+1 + " " + playerData.getStockHolding(i+1).getQuantity() + "\n");
            }
            writer.write("Stock Market Data - ID - Price - Expected Return - SD\n");
            for (Stock currStock : stockMarket) {
                writer.write(currStock.getID() + " " + currStock.getPrice() + " " + currStock.getExpectedReturn() + " " + currStock.getStandardDeviation() + "\n");
            }
            writer.close();
            readAllPlayersPlayerData("PlayerData/");
            return true;
        }
        catch (IOException e){
            System.out.println("error reading file");
        }
        return false;

    }
    /**
     * Retrieves the list of player data.
     *
     * @return The list of player data.
     */
    public static ArrayList<PlayerData> getData() {
        return data;
    }
}
