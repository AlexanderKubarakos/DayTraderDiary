package com.group50.projectsrc;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.group50.projectsrc.PlayerDataManager.createNewPlayerData;
import static com.group50.projectsrc.PlayerDataManager.loadPlayerFile;


/**
 * Controller class for the main frame of the application.
 * @author Affan Ahmad
 * @author Alexander Kubarakos
 */
public class MainFrameController {

    // Variables in the main root Pane
    @FXML
    private Button toTitleScreen;

    @FXML
    private Button settings;

    // Variables in the mainMenuPane Pane
    @FXML
    private Pane mainMenuPane;

    @FXML
    private Button newGame;

    @FXML
    private Button loadgame;

    @FXML
    private Text loadGameHoverText;

    @FXML
    private Text newGameHoverText;

    // Variables in the loginPane Pane
    @FXML
    private Pane loginPane;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Text invalidLoginText;

    // Variables in the registerPane Pane
    @FXML
    private Pane registerPane;

    @FXML
    private PasswordField passwordRegister;

    @FXML
    private TextField usernameRegister;

    @FXML
    private Button registerButton;

    @FXML
    private Text usernameAlreadyTakenText;

    @FXML
    private Text loginCreated;

    // Storing all current passwords
    private PasswordManager logins = new PasswordManager("./logins.txt");

    /**
     * Default constructor.
     * @throws FileNotFoundException If the file for storing logins is not found.
     */
    public MainFrameController() throws FileNotFoundException {
    }

    /**
     *  Plays a click sound (used for all buttons)
     */
    @FXML
    private void playClick(){
        AudioPlayer.playClick();
    }

    // all main menu pane methods
    /**
     * Handles the action of going back.
     */
    @FXML
    private void goBack() {
        if (registerPane.isVisible() || loginPane.isVisible()) {
            registerPane.setVisible(false);
            loginPane.setVisible(false);
            mainMenuPane.setVisible(true);
        } else {
            SceneManager.getInstance().goBack();
        }
    }

    /**
     * Switches to the login pane.
     */
    @FXML
    private void switchToLoginPane() {
        mainMenuPane.setVisible(false);
        loginPane.setVisible(true);
    }

    /**
     * Switches to the register pane.
     */
    @FXML
    private void switchToRegisterPane() {
        mainMenuPane.setVisible(false);
        registerPane.setVisible(true);
    }

    // Register Screen Exclusive Method

    /**
     * Handles the registration attempt.
     */
    @FXML
    private void registerAttempt() {
        String username = usernameRegister.getText();
        String password = passwordRegister.getText();
        if (username.isEmpty())
            return;
        if (password.isEmpty())
            return;
        if (username.equalsIgnoreCase("username")) {
            loginCreated.setVisible(false);

        } else if (username.trim().equals("")) {
            loginCreated.setVisible(false);

        } else {
            try {
                logins.addLogin(username, password);
                createNewPlayerData(username, password);

                loginCreated.setVisible(true);
            } catch (UsernameAlreadyExistsException e) {
                usernameAlreadyTakenText.setVisible(true);
                loginCreated.setVisible(false);

            } catch (IOException e) {
                System.out.println("Error inputting login in register screen");
                goBack();
            } catch (NullPointerException e){
                usernameAlreadyTakenText.setVisible(false);
                loginCreated.setVisible(true);
            }finally {
                usernameRegister.clear();
                passwordRegister.clear();
            }
        }
    }

    // Login Screen Exclusive Method
    /**
     * Handles the login attempt.
     * @throws FileNotFoundException If the file for storing logins is not found.
     */
    @FXML
    private void loginAttempt() throws FileNotFoundException {
        String username = loginUsername.getText();
        String password = loginPassword.getText();
        try {
            if (logins.checkLogin(username, password)) {
                loginUsername.clear();
                loginPassword.clear();
                System.out.println("Username and Password are correct");
                AudioPlayer.stopMusic();
                if (!loadPlayerFile(username)) {
                    return;
                }
                PlayerData currPlayer = GameState.getInstance().getActivePlayerData();
                int day = currPlayer.getDay();
                if(day > 5){
                    SceneManager.getInstance().activate("EndGameScreen");
                    EndGameScreenController controller = (EndGameScreenController) SceneManager.getInstance().getController("EndGameScreen");
                    controller.updateUI();
                }
                else{
                    SceneManager.getInstance().activate("LobbyScreen");
                    LobbyScreen controller = (LobbyScreen) SceneManager.getInstance().getController("LobbyScreen");
                    controller.updateUI();
                }
            }
            else {
                invalidLoginText.setVisible(true);
                loginPassword.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("something went wrong");
        }
    }

}