package com.group50.projectsrc;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.io.IOException;

/**
 * Controls the main game screen, all its graphs, stocks, and event displaying
 * @author Josh Spasic
 * @author Duncan Finlayson
 * @author Alexander Kubarakos
 */
public class GameScreenController {
    private int STOCK_LIMIT = 15;
    @FXML private LineChart<Number, Number> gAPL; @FXML private NumberAxis xAxisAPL; @FXML private NumberAxis yAxisAPL;
    @FXML private LineChart<Number, Number> gNVDA; @FXML private NumberAxis xAxisNVDA; @FXML private NumberAxis yAxisNVDA;
    @FXML private LineChart<Number, Number> gINT; @FXML private NumberAxis xAxisINT; @FXML private NumberAxis yAxisINT;
    @FXML private LineChart<Number, Number> gLOB; @FXML private NumberAxis xAxisLOB; @FXML private NumberAxis yAxisLOB;
    @FXML private LineChart<Number, Number> gMET; @FXML private NumberAxis xAxisMET; @FXML private NumberAxis yAxisMET;
    @FXML private LineChart<Number, Number> gGOR; @FXML private NumberAxis xAxisGOR; @FXML private NumberAxis yAxisGOR;
    @FXML private LineChart<Number, Number> gTDB; @FXML private NumberAxis xAxisTDB; @FXML private NumberAxis yAxisTDB;
    @FXML private LineChart<Number, Number> gSCO; @FXML private NumberAxis xAxisSCO; @FXML private NumberAxis yAxisSCO;
    @FXML private LineChart<Number, Number> gBMO; @FXML private NumberAxis xAxisBMO; @FXML private NumberAxis yAxisBMO;
    @FXML private LineChart<Number, Number> gSUN; @FXML private NumberAxis xAxisSUN; @FXML private NumberAxis yAxisSUN;
    @FXML private LineChart<Number, Number> gBEW; @FXML private NumberAxis xAxisBEW; @FXML private NumberAxis yAxisBEW;
    @FXML private LineChart<Number, Number> gROR; @FXML private NumberAxis xAxisROR; @FXML private NumberAxis yAxisROR;
    @FXML private ImageView newsImage;

    @FXML private Text tAPLhold; @FXML private Text tAPLprice;
    @FXML private Text tNVDAhold; @FXML private Text tNVDAprice;
    @FXML private Text tINThold; @FXML private Text tINTprice;
    @FXML private Text tLOBhold; @FXML private Text tLOBprice;
    @FXML private Text tMEThold; @FXML private Text tMETprice;
    @FXML private Text tGORhold; @FXML private Text tGORprice;
    @FXML private Text tTDBhold; @FXML private Text tTDBprice;
    @FXML private Text tSCOhold; @FXML private Text tSCOprice;
    @FXML private Text tBMOhold; @FXML private Text tBMOprice;
    @FXML private Text tSUNhold; @FXML private Text tSUNprice;
    @FXML private Text tBEWhold; @FXML private Text tBEWprice;
    @FXML private Text tRORhold; @FXML private Text tRORprice;
    @FXML private Text tLevel;
    @FXML private Button saAPL; @FXML private Button saNVDA; @FXML private Button saINT;
    @FXML private Button saLOB; @FXML private Button saMET; @FXML private Button saGOR;
    @FXML private Button saTDB; @FXML private Button saSCO; @FXML private Button saBMO;
    @FXML private Button saSUN; @FXML private Button saBEW; @FXML private Button saROR;


    @FXML private Tab foodButton;
    @FXML private Tab techButton;
    @FXML private Tab bankingButton;
    @FXML private Tab luxuryButton;
    @FXML private Pane setLevelScreen;
    @FXML private Text newsTitle;

    @FXML private Slider sfxSlider;

    @FXML private Slider musicSlider;

    @FXML private TextArea newsText;
    @FXML private Text currBalance; @FXML private Text currTime; @FXML private Text currDay;
    @FXML private ComboBox<String> selectCompanySetShare;
    @FXML private ComboBox<String> selectEvent;
    @FXML private ComboBox<String> selectDisplayEvent;
    @FXML private Button exitwoSave;
    @FXML private Label balanceTitle;
    @FXML private Button confirmEvent;
    @FXML private Button displayEvent;
    @FXML private Button pauseButton;
    @FXML private Button confirmDaily;
    @FXML private Button confirmDate;
    @FXML private Button confirmNewBalance;
    @FXML private Button confirmSetShare;
    @FXML private Button confirmTime;
    @FXML private Button confirmTomorrow;
    @FXML private TextField enterBalance;
    @FXML private TextField enterLevel;
    @FXML private PasswordField passwordInput;
    @FXML private TextField enterDay;
    @FXML private TextField timeTextBox;

    @FXML private Button exitDevMode;

    @FXML private Pane news;

    @FXML private Button devModeOpen; @FXML private Button devModeExit;

    @FXML private Button setDayOpen; @FXML private Button setDateExit;
    @FXML private Button setShareOpen; @FXML private Button exitSetSharesMenu;
    @FXML private Button setBalanceOpen; @FXML private Button setBalanceMenuExit;
    @FXML private Button setEventOpen; @FXML private Button setEventExit;
    @FXML private Button displayEventOpen; @FXML private Button displayEventExit;
    @FXML private Button setTimeOpen; @FXML private Button setTimeExit;
    @FXML private Button settingsOpen; @FXML private Button settingsExit;

    @FXML private Button displayPasswordConfirm; @FXML private Button displayPasswordExit;

    @FXML private Button setLevelOpen ; @FXML private Button setLevelExit;
    @FXML private TextField selectShareAmountSetShare;
    @FXML private Pane setBalanceScreen;
    @FXML private Pane setEventScreen;
    @FXML private Pane displayEventScreen;
    @FXML private Pane setDailyNewsScreen;
    @FXML private Label setDailyNewsTitle;
    @FXML private Pane setDateScreen;
    @FXML private Label setDateTitle;
    @FXML private Pane setShareAmountScreen;
    @FXML private Label setShareTitle;
    @FXML private Pane devModePane;

    @FXML private Label setStockRatesTitle;
    @FXML private Pane setTimeScreen;
    @FXML private Label setTimeTitle;
    @FXML private Pane settings;
    @FXML private ComboBox<String> stockIndividualDrop;
    @FXML private ComboBox<String> stockSectorDropDown;
    @FXML private TabPane stocks;
    @FXML private Pane displayPassword;

    /**
     * Handles the exit buttons for all the menus.
     * @param event fx event
     */
    @FXML
    private void ExitBtn(ActionEvent event) {
        AudioPlayer.playClick();
        String input = event.getSource().toString();
        String result = "";
        int startIndex = input.indexOf('=') + 1;
        for (int i = startIndex; i<input.length(); i++) {
            if (input.charAt(i)!=',') {
                result = result + input.charAt(i);
            } else {
                break;
            }
        }
        if (result.equals("exitSetSharesMenu")) {
            exitSetSharesMenu.getParent().setVisible(false);
        } else if (result.equals("devModeExit")) {
            devModeExit.getParent().setVisible(false);
        } else if (result.equals("settingsExit")) {
            settingsExit.getParent().setVisible(false);
        } else if (result.equals("setBalanceMenuExit")) {
            setBalanceMenuExit.getParent().setVisible(false);
        } else if (result.equals("setDateExit")) {
            setDateExit.getParent().setVisible(false);
        } else if (result.equals("setEventExit")) {
            setEventExit.getParent().setVisible(false);
        } else if (result.equals("setTimeExit")) {
            setTimeExit.getParent().setVisible(false);
        } else if (result.equals("exitDevMode")) {
            devModePane.setVisible(false);
            devModeOpen.setVisible(false);
        }else if (result.equals("displayEventExit")) {
            displayEventExit.getParent().setVisible(false);
        } else if (result.equals("displayPasswordExit")) {
            displayPasswordExit.getParent().setVisible(false);
        } else if (result.equals("exitwoSave")) {
            AudioPlayer.stopMusic();
            AudioPlayer.playMenuMusic();
            GameState.getInstance().setActivePlayerData(null);
            SceneManager.getInstance().activate("MainMenu");
            SceneManager.getInstance().clearHistory();
            GameState.getInstance().endSimulation();
            exitwoSave.getParent().setVisible(false);
        } else if (result.equals("setLevelExit")) {
            setLevelExit.getParent().setVisible(false);
        }
    }

    /**
     * Dev Mode Button
     * Ends the current day.
     * @param event fx event
     */
    @FXML
    private void endDay(ActionEvent event) {
        devModePane.setVisible(false);
        GameState.getInstance().endDay();
    }

    /**
     * Handles the buttons to open different menus.
     * @param event fx event
     */
    @FXML
    private void OpenBtn(ActionEvent event) {
        AudioPlayer.playClick();
        String input = event.getSource().toString();
        String result = "";
        int startIndex = input.indexOf('=') + 1;
        for (int i = startIndex; i<input.length(); i++) {
            if (input.charAt(i)!=',') {
                result = result + input.charAt(i);
            } else {
                break;
            }
        }

        if (result.equals("settingsOpen")) {
            if (settings.isVisible()) {
                settings.setVisible(false);
            } else {
                settings.setVisible(true);
            }
        } else if (result.equals("devModeOpen")) {
            if (devModePane.isVisible()) {
                devModePane.setVisible(false);
            } else {
                devModePane.setVisible(true);
            }
        } else if (result.equals("setShareOpen")) {
            setShareAmountScreen.setVisible(true);
        } else if (result.equals("setEventOpen")) {
            setEventScreen.setVisible(true);
        } else if (result.equals("setBalanceOpen")) {
            setBalanceScreen.setVisible(true);
        } else if (result.equals("setTimeOpen")) {
            setTimeScreen.setVisible(true);
        } else if (result.equals("setDayOpen")) {
            setDateScreen.setVisible(true);
        }else if (result.equals("displayEventOpen")) {
            displayEventScreen.setVisible(true);
        } else if (result.equals("setLevelOpen")) {
            setLevelScreen.setVisible(true);
        }

    }

    /**
     * Sets up the graphs as well as blocking out sectors based off progression level
     * @param xAxis graph x axis
     * @param yAxis graph y axis
     * @param chart chart
     * @param ID stock id
     */
    private void setMarketGraph(NumberAxis xAxis, NumberAxis yAxis, LineChart<Number, Number> chart, int ID) {
        chart.setAnimated(false);
        yAxis.setAutoRanging(true);
        yAxis.setForceZeroInRange(false);
        xAxis.setForceZeroInRange(false);
        chart.setCreateSymbols(false);



        new AnimationTimer(){

            @Override
            public void handle(long now) {
                XYChart.Series series = new XYChart.Series();
                for (var pair : StockMarket.getInstance().getStockByID(ID).getPriceHistory()) {
                    float f =pair.getValue().floatValue();
                    series.getData().add(new XYChart.Data<>(pair.getKey().intValue(), f));
                }
                chart.getData().clear();
                chart.getData().addAll(series);

                luxuryButton.setDisable(true);
                bankingButton.setDisable(true);
                foodButton.setDisable(true);

                if (GameState.getInstance().getActivePlayerData() != null) {
                    switch (GameState.getInstance().getActivePlayerData().getProgresionLevel()) {
                        case 4:
                            luxuryButton.setDisable(false);
                        case 3:
                            bankingButton.setDisable(false);
                        case 2:
                            foodButton.setDisable(false);
                        case 1:
                        default:
                            techButton.setDisable(false);
                    }
                }
            }

        }.start();
    }


    /**
     * Opens up Dev Mode Password Screen
     * @param event fx event
     */
    @FXML
    private void actDevMode(ActionEvent event) {
        AudioPlayer.playClick();
        displayPassword.setVisible(true);
        settings.setVisible(false);
        passwordInput.clear();
        passwordInput.setPromptText("Enter Password");


    }

    /**
     * Password Confirm Button
     * Checks if the inputted Password is correct.
     * @param event fx event
     */
    @FXML
    private void displayPasswordConfirm(ActionEvent event) {

        AudioPlayer.playClick();
        String input = passwordInput.getText();
        if (input!=null) {
           if (input.equals("password123")) {
               displayPassword.setVisible(false);
               devModeOpen.setVisible(true);
           }
           else {
               passwordInput.setText("");
               passwordInput.setPromptText("Password Incorrect");
           }
        }
    }

    /**
     * Dev Mode Button
     * Sets new share amount for any given company.
     * @param event fx event
     */
    @FXML
    private void confirmNewShareAmount(ActionEvent event) {
        AudioPlayer.playClick();
        String input = selectShareAmountSetShare.getText();
        try {
            int value = Integer.parseInt(input);
            if (value >= 1) {
                //TODO: is this line even needed??
                selectShareAmountSetShare.setText(String.valueOf(value));
                String selectedStock = selectCompanySetShare.getValue();
                if (selectedStock != null) {
                    StockHolding h = new StockHolding(StockMarket.getInstance().getStockByName(selectedStock).getID(), value);
                    GameState.getInstance().getActivePlayerData().setStockHolding(h);
                }
            } else {
                // Notify the user that the input should be a positive integer
                // You can show a message, change the text color, etc.
                System.out.println("Please enter a positive integer greater than or equal to 1.");
            }
        } catch (NumberFormatException e) {
            // Notify the user that the input should be a positive integer
            // You can show a message, change the text color, etc.
            System.out.println("Please enter a positive integer greater than or equal to 1.");
        }
        selectCompanySetShare.getSelectionModel().clearSelection();
        setShareAmountScreen.setVisible(false);
    }

    /**
     * initialize the main game screen
     */
    public void initialize() {
        // Create a list of companies
        selectCompanySetShare.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(selectCompanySetShare.getPromptText());
                } else {
                    setText(item);
                }
            }
        });

        ObservableList<String> companies = FXCollections.observableArrayList(
                StockMarket.getInstance().getAllStockNames()
        );

        // Add the list of companies to the ComboBox
        selectCompanySetShare.setItems(companies);

        selectEvent.setButtonCell(new ListCell<> () {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(selectEvent.getPromptText());
                } else {
                    setText(item);
                }
            }
        });

        selectDisplayEvent.setButtonCell(new ListCell<> () {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(selectEvent.getPromptText());
                } else {
                    setText(item);
                }
            }
        });

        ObservableList<String> events = FXCollections.observableArrayList(
                EventManager.getInstance().getAllEventNames()
        );

        selectEvent.setItems(events);
        selectDisplayEvent.setItems(events);

        new AnimationTimer(){

            @Override
            public void handle(long now) {
                if (GameState.getInstance().getActivePlayerData()!=null) {
                    if (GameState.getInstance().getIngameTime()%5==0){
                        setTime();
                    }

                    setBalance();
                    updateText();
                }
            }

        }.start();

        setMarketGraph(xAxisAPL, yAxisAPL, gAPL, 1);
        setMarketGraph(xAxisNVDA, yAxisNVDA, gNVDA, 2);
        setMarketGraph(xAxisINT, yAxisINT, gINT, 3);
        setMarketGraph(xAxisLOB, yAxisLOB, gLOB, 4);
        setMarketGraph(xAxisMET, yAxisMET, gMET, 5);
        setMarketGraph(xAxisGOR, yAxisGOR, gGOR, 6);
        setMarketGraph(xAxisTDB, yAxisTDB, gTDB, 7);
        setMarketGraph(xAxisSCO, yAxisSCO, gSCO, 8);
        setMarketGraph(xAxisBMO, yAxisBMO, gBMO, 9);
        setMarketGraph(xAxisSUN, yAxisSUN, gSUN, 10);
        setMarketGraph(xAxisBEW, yAxisBEW, gBEW, 11);
        setMarketGraph(xAxisROR, yAxisROR, gROR, 12);
    }

    /**
     * Dev Mode Button
     * Sets new Event to take place
     * @param event fx event
     */
    @FXML
    private void confirmEvent(ActionEvent event) {
        AudioPlayer.playClick();

        String selectedEvent = selectEvent.getValue();
        if (selectedEvent!=null) {
            Event eventToPlay = EventManager.getInstance().searchByTitle(selectedEvent);
            StockMarket.getInstance().applyEventEffect(eventToPlay.getEffect());
            displayNews(eventToPlay);
        }
        selectEvent.getSelectionModel().clearSelection();
        setEventScreen.setVisible(false);
    }

    /**
     * Dev Mode Button
     * Displays new Event to take place
     * @param event fx event
     */
    @FXML
    private void displayEventConfirm(ActionEvent event) {
        AudioPlayer.playClick();

        String selectedEvent = selectDisplayEvent.getValue();
        if (selectedEvent!=null) {
            Event eventToPlay = EventManager.getInstance().searchByTitle(selectedEvent);
            displayNews(eventToPlay);
        }
        selectDisplayEvent.getSelectionModel().clearSelection();
        displayEventScreen.setVisible(false);
    }

    /**
     * Dev Mode Button
     * Sets new player balance
     * @param event fx event
     */
    @FXML
    private void confirmNewBalance(ActionEvent event) {
        AudioPlayer.playClick();
        setBalanceScreen.setVisible(false);
        try {
            int b = Integer.parseInt(enterBalance.getText());
            GameState.getInstance().getActivePlayerData().setMoney(b);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered in balance box");
        }

    }

    /**
     * Dev Mode Button
     * Sets new day
     * @param event fx event
     */
    @FXML
    private void confirmNewDate(ActionEvent event) {
        AudioPlayer.playClick();
        setDateScreen.setVisible(false);
        try {
            int day = Integer.parseInt(enterDay.getText());
            GameState.getInstance().endDay();
            GameState.getInstance().getActivePlayerData().setDay(day);

        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered in balance box");
        }
    }

    /**
     * Dev Mode Button
     * Sets a new progression level, updates sectors accordingly
     * @param event fx event
     */
    @FXML
    private void confirmNewLevel(ActionEvent event) {
        AudioPlayer.playClick();
        setLevelScreen.setVisible(false);
        try {
            int level = Integer.parseInt(enterLevel.getText());
            if ((level<=4)&&(level>=1)) {
                switch (level) {
                    case 4:
                        luxuryButton.setDisable(false);
                    case 3:
                        GameState.getInstance().setTarget(GameState.getInstance().getLEVEL4());
                        luxuryButton.setDisable(true);
                        bankingButton.setDisable(false);
                    case 2:
                        GameState.getInstance().setTarget(GameState.getInstance().getLEVEL3());
                        luxuryButton.setDisable(true);
                        bankingButton.setDisable(true);
                        foodButton.setDisable(false);
                    case 1:
                    default:
                        GameState.getInstance().setTarget(GameState.getInstance().getLEVEL2());
                        luxuryButton.setDisable(true);
                        bankingButton.setDisable(true);
                        foodButton.setDisable(true);
                        techButton.setDisable(false);
                }
                GameState.getInstance().getActivePlayerData().setProgressionLevel(level);
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    /**
     * Dev Mode Button
     * Unlocks all sectors
     * @param event fx event
     */
    @FXML
    private void unlockAll(ActionEvent event) {
        AudioPlayer.playClick();
        luxuryButton.setDisable(false);
        bankingButton.setDisable(false);
        foodButton.setDisable(false);
        techButton.setDisable(false);
    }


    /**
     * Dev Mode Button
     * Sets new in game time
     * @param event fx event
     */
    @FXML
    private void confirmNewTime(ActionEvent event) {
        AudioPlayer.playClick();
        try {
            int newTime = Integer.parseInt(timeTextBox.getText());
            GameState.getInstance().setTime(newTime);
        } catch (NumberFormatException e) {
            System.out.println("Invalid time input");
        }

        setTimeScreen.setVisible(false);
    }


    /**
     * Dev Mode Button
     * Pauses / Unpauses the in game time
     * @param event fx event
     */
    @FXML
    private void pauseUnpause(ActionEvent event) {
        AudioPlayer.playClick();
        if (GameState.getInstance().isRunning())
            GameState.getInstance().endSimulation();
        else
            GameState.getInstance().resumeSimulation();
    }

    /**
     * Sell All Button
     * Sells all of any given stock
     * @param event button that clicked it
     */
    @FXML
    private void saButton(ActionEvent event) {
        AudioPlayer.playClick();
        String input = event.getSource().toString();
        PlayerData Player = GameState.getInstance().getActivePlayerData();
        String stockLabel = input.substring(12, 16);
        if (stockLabel.contains(",")) {
            stockLabel = stockLabel.substring(0,3);
        }
        Stock stock = StockMarket.getInstance().getStockByLabel(stockLabel);
        int stockID = stock.getID();
        int currentHoldings = Player.getStockHolding(stockID).getQuantity();
        float stockPrice = StockMarket.getInstance().getStockByID(stockID).getPrice();
        StockHolding stockToSell = new StockHolding(stockID, -currentHoldings);
        if (currentHoldings>0) {
            Player.addStockHolding(stockToSell);
            Player.setMoney(Player.getMoney()+(stockPrice*currentHoldings));
        }


    }
    /**
     * Buy / Sell buttons for all the stocks
     * @param event button that clicked it
     */
    @FXML
    private void bsButton(ActionEvent event) {
        AudioPlayer.playClick();
        String input = event.getSource().toString();

        PlayerData Player = GameState.getInstance().getActivePlayerData();
        char action = input.charAt(10);

        String stockLabel = input.substring(11, 15);
        if (stockLabel.contains(",")) {
            stockLabel = stockLabel.substring(0,3);
        }

        Stock stock = StockMarket.getInstance().getStockByLabel(stockLabel);
        int stockID = stock.getID();
        int currentHoldings = Player.getStockHolding(stock.getID()).getQuantity();
        float stockPrice = StockMarket.getInstance().getStockByID(stockID).getPrice();
        if (action=='b') { //BUY
            if (Player.getMoney()>stockPrice) {
                boolean eligible = false;
                if (stockID<=3) {
                    if ((Player.getStockHolding(1).getQuantity()+Player.getStockHolding(2).getQuantity()+Player.getStockHolding(3).getQuantity())<STOCK_LIMIT) {
                        eligible = true;
                    }
                } else if (stockID<=6){
                    if ((Player.getStockHolding(4).getQuantity()+Player.getStockHolding(5).getQuantity()+Player.getStockHolding(6).getQuantity())<STOCK_LIMIT) {
                        eligible = true;
                    }
                } else if (stockID<=9){
                    if ((Player.getStockHolding(7).getQuantity()+Player.getStockHolding(8).getQuantity()+Player.getStockHolding(9).getQuantity())<STOCK_LIMIT) {
                        eligible = true;
                    }
                } else if (stockID<=12){
                    if ((Player.getStockHolding(10).getQuantity()+Player.getStockHolding(11).getQuantity()+Player.getStockHolding(12).getQuantity())<STOCK_LIMIT) {
                        eligible = true;
                    }
                }
                if (eligible) {
                    StockHolding stockToAdd = new StockHolding(stockID, 1);
                    Player.addStockHolding(stockToAdd);
                    Player.setMoney(Player.getMoney() - stockPrice);
                }
            }

        } else { //SELL
            StockHolding stockToSell = new StockHolding(stockID, -1);
            if (currentHoldings>0) {
                Player.addStockHolding(stockToSell);
                Player.setMoney(Player.getMoney()+stockPrice);
            }
        }
    }

    /**
     * Sets the balance text
     */
    private void setBalance() {
        if (GameState.getInstance().getActivePlayerData() == null)
            return;
        String moneyRound =  String.format("%.2f", GameState.getInstance().getActivePlayerData().getMoney());
        currBalance.setText("$" + String.valueOf(moneyRound));
    }

    /**
     * Updates the Text of all the stock prices / current holdings
     */
    private void updateText() {
        String APLprice =  String.format("%.2f", StockMarket.getInstance().getStockByID(1).getPrice());
        String NVDAprice =  String.format("%.2f", StockMarket.getInstance().getStockByID(2).getPrice());
        String INTprice =  String.format("%.2f", StockMarket.getInstance().getStockByID(3).getPrice());
        String LOBprice =  String.format("%.2f", StockMarket.getInstance().getStockByID(4).getPrice());
        String METprice =  String.format("%.2f", StockMarket.getInstance().getStockByID(5).getPrice());
        String GORprice =  String.format("%.2f", StockMarket.getInstance().getStockByID(6).getPrice());
        String TDBprice =  String.format("%.2f", StockMarket.getInstance().getStockByID(7).getPrice());
        String SCOprice =  String.format("%.2f", StockMarket.getInstance().getStockByID(8).getPrice());
        String BMOprice =  String.format("%.2f", StockMarket.getInstance().getStockByID(9).getPrice());
        String SUNprice =  String.format("%.2f", StockMarket.getInstance().getStockByID(10).getPrice());
        String BEWprice =  String.format("%.2f", StockMarket.getInstance().getStockByID(11).getPrice());
        String RORprice =  String.format("%.2f", StockMarket.getInstance().getStockByID(12).getPrice());

        tAPLhold.setText("Current Holdings: " + String.valueOf(GameState.getInstance().getActivePlayerData().getStockHolding(1).getQuantity()));
        tAPLprice.setText("Current Price: " + APLprice);
        tNVDAhold.setText("Current Holdings: " + String.valueOf(GameState.getInstance().getActivePlayerData().getStockHolding(2).getQuantity()));
        tNVDAprice.setText("Current Price: " + NVDAprice);
        tINThold.setText("Current Holdings: " + String.valueOf(GameState.getInstance().getActivePlayerData().getStockHolding(3).getQuantity()));
        tINTprice.setText("Current Price: " + INTprice);
        tLOBhold.setText("Current Holdings: " + String.valueOf(GameState.getInstance().getActivePlayerData().getStockHolding(4).getQuantity()));
        tLOBprice.setText("Current Price: " + LOBprice);
        tMEThold.setText("Current Holdings: " + String.valueOf(GameState.getInstance().getActivePlayerData().getStockHolding(5).getQuantity()));
        tMETprice.setText("Current Price: " + METprice);
        tGORhold.setText("Current Holdings: " + String.valueOf(GameState.getInstance().getActivePlayerData().getStockHolding(6).getQuantity()));
        tGORprice.setText("Current Price: " + GORprice);
        tTDBhold.setText("Current Holdings: " + String.valueOf(GameState.getInstance().getActivePlayerData().getStockHolding(7).getQuantity()));
        tTDBprice.setText("Current Price: " + TDBprice);
        tSCOhold.setText("Current Holdings: " + String.valueOf(GameState.getInstance().getActivePlayerData().getStockHolding(8).getQuantity()));
        tSCOprice.setText("Current Price: " + SCOprice);
        tBMOhold.setText("Current Holdings: " + String.valueOf(GameState.getInstance().getActivePlayerData().getStockHolding(9).getQuantity()));
        tBMOprice.setText("Current Price: " + BMOprice);
        tSUNhold.setText("Current Holdings: " + String.valueOf(GameState.getInstance().getActivePlayerData().getStockHolding(10).getQuantity()));
        tSUNprice.setText("Current Price: " + SUNprice);
        tBEWhold.setText("Current Holdings: " + String.valueOf(GameState.getInstance().getActivePlayerData().getStockHolding(11).getQuantity()));
        tBEWprice.setText("Current Price: " + BEWprice);
        tRORhold.setText("Current Holdings: " + String.valueOf(GameState.getInstance().getActivePlayerData().getStockHolding(12).getQuantity()));
        tRORprice.setText("Current Price: " + RORprice);
    }

    /**
     * Dev Mode Button
     * Sets the Time
     */
    private void setTime(){
        tLevel.setText("Level: " + GameState.getInstance().getActivePlayerData().getProgresionLevel());
        currDay.setText("Day: " + GameState.getInstance().getActivePlayerData().getDay());
        String time = String.valueOf(GameState.getInstance().getIngameTime());
        String hours;
        String minutes;
        if (time.length() == 2)
        {
            hours = "0";
            minutes = time;
        }
        else if (time.length()==3) {
            hours = time.substring(0,1);
            minutes = time.substring(1,3);
        } else {
            hours = time.substring(0,2);
            minutes = time.substring(2,4);
        }
        currTime.setText(hours + ":" + minutes);

    }

    /**
     * Displays news in the news box
     * @param currEvent event to display
     */
    public void displayNews(Event currEvent) {
        AudioPlayer.playClick();
        newsText.setVisible(true);
        newsTitle.setVisible(true);
        newsImage.setVisible(true);
        newsTitle.setText(currEvent.getTitle());
        newsText.setText(currEvent.getDescription());
        newsImage.setImage(new Image("file:" + currEvent.getImageLocation()));
    }

    /**
     * Confirm new audio settings
     * @param event fx event
     */
    @FXML
    public void audioConfirm(ActionEvent event) {
        double SFXLevel = sfxSlider.getValue();
        double MusicLevel = musicSlider.getValue();
        AudioPlayer.setMusic(MusicLevel);
        AudioPlayer.setSounds(SFXLevel);
    }


}