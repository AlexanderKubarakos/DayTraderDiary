package com.group50.projectsrc;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * This controls the audio of our game.
 * @author Lyndon Zhang
 * @author Josh Spasic
 * @author Alexander Kubarakos
 */

public class AudioPlayer {

    private static String clickSoundStr = "audio/click.mp3";     // For example
    private static Media clickSound = new Media(new File(clickSoundStr).toURI().toString());
    private static MediaPlayer click;
    private static String mainSoundStr = "audio/main-menu.mp3";     // For example
    private static Media mainSound = new Media(new File(mainSoundStr).toURI().toString());
    private static MediaPlayer mainMenu;
    private static String gamemplaySoundStr = "audio/gameplay-music.mp3";     // For example
    private static Media gameplaySound = new Media(new File(gamemplaySoundStr).toURI().toString());
    private static MediaPlayer gameplay;

    /**
     * Plays Click sound
     * Only works for Windows
     */
    public static void playClick() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            click = new MediaPlayer(clickSound);
            click.play();
        }
    }

    /**
     * Plays Menu Music
     * Only works for Windows
     */
    public static void playMenuMusic() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            mainMenu = new MediaPlayer(mainSound);
            mainMenu.play();
        }
    }

    /**
     * Plays Game play Music
     * Only works for Windows
     */
    public static void playGameMusic() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            gameplay = new MediaPlayer(gameplaySound);
            gameplay.play();
        }
    }
    /**
     * Stops all Music
     * Only works for Windows
     */
    public static void stopMusic(){
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            if(gameplay != null) {
                gameplay.stop();
            }
            if(mainMenu != null) {
                mainMenu.stop();
            }
        }
    }
    /**
     * Adjusts Music Volume
     * Only works for Windows
     */
    public static void setMusic(double volume){
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            if(gameplay != null){
                gameplay.setVolume(volume / 100);
            }
            if(mainMenu != null){
                mainMenu.setVolume(volume / 100);
            }
        }
    }
    /**
     * Adjusts SFX volume
     * Only works for Windows
     */
    public static void setSounds(double volume){
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            if(click != null) {
                click.setVolume(volume / 100);
            }
        }
    }
}
