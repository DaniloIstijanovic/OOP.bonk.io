package com.github.daniloistijanovic.bonk.scenes;

import com.github.daniloistijanovic.bonk.Main;
import com.github.daniloistijanovic.bonk.singleplayer.Singleplayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenu {

    @FXML
    private Button logout;
    @FXML
    private Button profile;
    @FXML
    private Button play;

    public void ShowProfile(ActionEvent event) throws IOException {
        Main.instance.changeScene("fxml/Profile.fxml");

    }

    public void launchSinglePlayer(ActionEvent e) {
        new Singleplayer().start();
    }

    public void userLogOut(ActionEvent event) throws IOException {
        Main.instance.changeScene("fxml/Login.fxml");
    }

}
