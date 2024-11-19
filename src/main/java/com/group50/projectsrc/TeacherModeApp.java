package com.group50.projectsrc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * The TeacherModeApp class represents the entry point for the Teacher Mode application in the StockMoney project.
 * It extends the Application class from JavaFX and sets up the initial stage for the application.
 * @author Lyndon Zhang
 */
public class TeacherModeApp extends Application {

    /**
     * The start method initializes the stage for the Teacher Mode application.
     * @param stage The primary stage for the application.
     * @throws Exception If an error occurs during the initialization process.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(TeacherModeApp.class.getResource("teacher-mode.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 720);
        stage.setTitle("Teacher Mode - StockMoney");
        stage.setScene(scene);
        stage.show();
    }
}