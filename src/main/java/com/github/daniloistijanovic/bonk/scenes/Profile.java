package com.github.daniloistijanovic.bonk.scenes;

import com.github.daniloistijanovic.bonk.Main;
import com.github.daniloistijanovic.bonk.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class Profile {
    @FXML
    private Button back;
    @FXML
    private Label userprint;
    @FXML
    private Label scoreprint;

    public void BackMain(ActionEvent event) throws IOException {
        Main.instance.changeScene("MainMenu.fxml");
    }

    @FXML
    private void initialize() {
        User user = Main.instance.loggedInUser;
        userprint.setText(user.getName());
        scoreprint.setText("" + user.getHighScore());
    }
}
