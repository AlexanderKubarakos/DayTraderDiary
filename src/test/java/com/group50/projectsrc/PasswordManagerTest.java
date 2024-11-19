package com.group50.projectsrc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PasswordManagerTest {

    private PasswordManager passwordManager;
    private File tempFile;

    @BeforeEach
    public void setup() throws IOException{
        tempFile = File.createTempFile("test",".txt");
        passwordManager = new PasswordManager(tempFile.getAbsolutePath());
    }

    @Test
    public void testCheckLoginValid() throws IOException, UsernameAlreadyExistsException {
        String username = "user1";
        String password = "password1";
        passwordManager.addLogin(username, password);
        assertTrue(passwordManager.checkLogin(username, password));
    }

    @Test
    public void testCheckLoginInvalidUsername() throws IOException {
        assertFalse(passwordManager.checkLogin("nonexistentUser", "password"));
    }

    @Test
    public void testCheckLoginInvalidPassword() throws IOException, UsernameAlreadyExistsException {
        String username = "user2";
        String password = "password2";
        passwordManager.addLogin(username, password);
        assertFalse(passwordManager.checkLogin(username, "wrongPassword"));
    }

    @Test
    public void testAddLogin() throws IOException, UsernameAlreadyExistsException {
        String username = "user3";
        String password = "password3";
        passwordManager.addLogin(username, password);
        assertTrue(passwordManager.checkLogin(username, password));
    }

    @Test
    public void testAddLoginThrowsExceptionWhenUsernameExists() throws IOException, UsernameAlreadyExistsException {
        String username = "existingUser";
        String password = "password";
        passwordManager.addLogin(username, password);
        assertThrows(UsernameAlreadyExistsException.class, () -> passwordManager.addLogin(username, "newPassword"));
    }

    @Test
    public void testCheckUserDuplicate() throws IOException, UsernameAlreadyExistsException {
        String username = "user4";
        assertFalse(passwordManager.checkUserDuplicate(username));
        passwordManager.addLogin(username, "password4");
        assertTrue(passwordManager.checkUserDuplicate(username));
    }

    @Test
    public void testFormattedLogin() {
        String username = "user5";
        String password = "password5";
        String expected = "user5,password5";
        assertEquals(expected, passwordManager.formattedLogin(username, password));
    }
}