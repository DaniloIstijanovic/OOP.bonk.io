package com.github.daniloistijanovic.bonk.scenes;

import com.github.daniloistijanovic.bonk.Main;
import com.github.daniloistijanovic.bonk.utils.MusicUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Login {

    static final Map<String, String> logininfo = new HashMap<>();
    private static Scanner x;

    static {
        logininfo.put("123", "456");
        logininfo.put("abc", "def");
        logininfo.put("xXxEpicGamerxXx", "fortnite");
    }

    @FXML
    private Button button;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public Login() {

    }

    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();

    }

    private void checkLogin() {
        String st, stl;

        String userID = username.getText();
        String pass = password.getText();
        if (logininfo.containsKey(userID)) {
            if (logininfo.get(userID).equals(pass)) {
                wrongLogIn.setText("Success!");
                try {
                    Main.instance.changeScene("fxml/MainMenu.fxml");
                    new Thread(() -> {
                        MusicUtil.playLoop("Industrial.mp3");
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogIn.setText("Enter the data.");
        } else {
            wrongLogIn.setText("Wrong username or password!");
        }
    }
}