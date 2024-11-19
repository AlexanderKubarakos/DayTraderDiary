package com.group50.projectsrc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * The MainApp class represents the entry point of the application.
 * @author Lyndon Zhang
 * @author Duncan Finlayson
 */
public class MainApp extends Application {

    /**
     * The start method initializes the main menu scene.
     * @param stage The primary stage for the application.
     * @throws Exception If an error occurs during scene loading.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 720);
        stage.setTitle("Main Menu - StockMoney");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method launches the application.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
