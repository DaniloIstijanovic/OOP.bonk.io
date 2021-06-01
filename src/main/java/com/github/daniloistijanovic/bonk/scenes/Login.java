package com.github.daniloistijanovic.bonk.scenes;

import com.github.daniloistijanovic.bonk.Main;
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
    static Map<String, String> logininfo;
    private static Scanner x;

    static {
        try {
            File load = new File(userFile);
            FileInputStream fis = new FileInputStream(load);
            ObjectInputStream ois = new ObjectInputStream(fis);
            logininfo = (HashMap<String, String>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            //umesto ovoga treba da napravi prazan fajl ali evo za sad trpa jednog usera jer jos nema register
            debugger.file("Greska u ucitavanju usera: " + e.getMessage());
            logininfo = new HashMap<>();
            logininfo.put("123", "456");
            try {
                File save = new File(userFile);
                FileOutputStream fos = new FileOutputStream(save);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(logininfo);
                oos.flush();
                oos.close();
                fos.close();
            } catch (Exception e2) {
                debugger.file("Greska u cuvanju usera: " + e2.getMessage());
            }
        }
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

    public void userLogIn(ActionEvent event) {
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
                    Main.instance.changeScene("MainMenu.fxml");
                    Main.instance.setTitle("Main Menu");
                    MusicUtil.playLoopInternal("Industrial.mp3");
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