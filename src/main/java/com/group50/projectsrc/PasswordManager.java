package com.group50.projectsrc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

/*
This file will be in charge of using logins.java to create passwords and storing them onto logins.txt, turning
logins.txt into a HashTable formatted as (username,password), and retrieve keys when players need to log in
 */

/**
 * Manages user logins by storing and verifying usernames and passwords.
 * @author Affan Ahmad
 * @author Duncan Finlayson
 */
public class PasswordManager {
    HashMap<String, String> loginsHash;
    File file;

    /**
     * Constructor to initialize the PasswordManager with login data from a file.
     *
     * @param filepath The filepath to the file containing login data.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public PasswordManager(String filepath) throws FileNotFoundException {
        try {
            file = new File(filepath);
            loginsHash = new HashMap<>();
            Scanner line = new Scanner(file);

            while (line.hasNext()) {
                String currline = line.next();
                String[] login = currline.split(",");
                this.loginsHash.put(login[0], login[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the provided username and password match an existing login.
     *
     * @param username The username to check.
     * @param password The password to check.
     * @return True if the username and password match an existing login, false otherwise.
     */
    public boolean checkLogin(String username, String password) {
        if (loginsHash.containsKey(username)) {
            return Objects.equals(loginsHash.get(username), password);
        }
        return false;
    }

    /**
     * Adds a new login to the system.
     *
     * @param username The username for the new login.
     * @param password The password for the new login.
     * @throws UsernameAlreadyExistsException If the provided username already exists.
     * @throws IOException                    If an error occurs while writing the new login to the file.
     */
    public void addLogin(String username, String password) throws UsernameAlreadyExistsException, IOException {
        if (checkUserDuplicate(username)) {
            throw new UsernameAlreadyExistsException();
        } else {
            String login = formattedLogin(username, password);
            FileWriter fw = new FileWriter(file, true);
            fw.write("\n" + login);
            fw.close();
            loginsHash.put(username, password);
        }
    }

    public String formattedLogin(String username, String password) {
        return username + "," + password;
    }

    public boolean checkUserDuplicate(String username) {
        return loginsHash.containsKey(username);
    }
}