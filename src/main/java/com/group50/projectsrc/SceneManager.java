package com.group50.projectsrc;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

/**
 * Manages the scenes and navigation within the application.
 * @author Alexander Kubarakos
 * @author Duncan Finlayson
 */
public class SceneManager {
    static private SceneManager instance;
    private HashMap<String, Pair<Scene, Object>> screenMap = new HashMap<>();
    private Stage stage;
    private Stack<Scene> history = new Stack<>();

    private SceneManager() {

    }

    /**
     * Retrieves the singleton instance of the SceneManager.
     *
     * @return The SceneManager instance.
     */
    static public SceneManager getInstance() {
        if (instance == null)
            instance = new SceneManager();
        return instance;
    }

    /**
     * Sets the primary stage for the application.
     *
     * @param stage The primary stage.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
        stage.show();
    }

    /**
     * Adds a screen to the screen map from an FXML file.
     *
     * @param name     The name of the screen.
     * @param filePath The file path to the FXML file.
     */
    public void addScreen(String name, String filePath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource(filePath));
            Scene scene = new Scene(fxmlLoader.load(), 960, 720);
            screenMap.put(name, new Pair<>(scene, fxmlLoader.getController()));
        } catch (IOException e) {
            System.out.println("ERROR couldn't open a fxml file! " + filePath);
        }
    }

    /**
     * Adds a screen to the screen map from an existing Scene.
     *
     * @param name The name of the screen.
     * @param s    The Scene to be added.
     */
    public void addScreenFromScene(String name, Scene s) {
        screenMap.put(name, new Pair<>(s, null));
    }

    /**
     * Activates a screen by setting it as the current scene on the stage.
     *
     * @param name The name of the screen to activate.
     */
    public void activate(String name) {
        history.add(stage.getScene());
        stage.setScene(screenMap.get(name).getKey());
    }

    /**
     * Navigates back to the previous scene.
     */
    public void goBack() {
        if (!history.isEmpty()) {
            stage.setScene(history.pop());
        }
    }

    /**
     * Clears the navigation history.
     */
    public void clearHistory() {
        history.clear();
    }

    /**
     * Retrieves the controller associated with a specific screen.
     *
     * @param name The name of the screen.
     * @return The controller object associated with the screen.
     */
    public Object getController(String name) {
        if (screenMap.containsKey(name))
            return screenMap.get(name).getValue();
        return null;
    }
}
