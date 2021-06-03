package com.github.daniloistijanovic.bonk.scenes;

import com.github.daniloistijanovic.bonk.Main;
import com.github.daniloistijanovic.bonk.User;
import com.github.daniloistijanovic.bonk.utils.MusicUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.github.daniloistijanovic.bonk.utils.DebugUtil.debugger;

public class Login {

    private static final String userFile = "bonk-users.dat";
    static Map<String, User> logininfo;
    private static Scanner x;

    static {
        loadUsers();
    }

    @FXML
    private Button button;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button button2;

    private static void loadUsers() {
        try {
            File load = new File(userFile);
            FileInputStream fis = new FileInputStream(load);
            ObjectInputStream ois = new ObjectInputStream(fis);
            logininfo = (HashMap<String, User>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            debugger.file("Greska u ucitavanju usera: " + e.getMessage());
            logininfo = new HashMap<>();
            saveUsers();
        }
    }

    private static void saveUsers() {
        try {
            File save = new File(userFile);
            FileOutputStream fos = new FileOutputStream(save);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(logininfo);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            debugger.file("Greska u cuvanju usera: " + e.getMessage());
        }
    }

    public void userLogIn(ActionEvent event) {
        checkLogin();

    }

    public void userRegister(ActionEvent event) {
        checkRegister();

    }

    private void checkLogin() {

        String userID = username.getText();
        String pass = password.getText();
        if (logininfo.containsKey(userID)) {
            User user = logininfo.get(userID);
            if (user.getPass().equals(pass)) {
                wrongLogIn.setText("Success!");
                try {
                    Main.instance.loggedInUser = user;
                    Main.instance.changeScene("MainMenu.fxml");
                    Main.instance.setTitle("Main Menu");
                    MusicUtil.playLoopInternal("Industrial.mp3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                wrongLogIn.setText("Wrong username or password!");
            }
        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogIn.setText("Enter the data.");
        } else {
            wrongLogIn.setText("Wrong username or password!");
        }
    }

    private void checkRegister() {
        String userID = username.getText();
        String pass = password.getText();
        if (logininfo.containsKey(userID)) {
            wrongLogIn.setText("Username already exists");
        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogIn.setText("Enter the data.");
        } else {
            User newUser = new User(userID, pass);
            logininfo.put(userID, newUser);
            wrongLogIn.setText("Success!");
            saveUsers();
            try {
                Main.instance.loggedInUser = newUser;
                Main.instance.changeScene("MainMenu.fxml");
                Main.instance.setTitle("Main Menu");
                MusicUtil.playLoopInternal("Industrial.mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}