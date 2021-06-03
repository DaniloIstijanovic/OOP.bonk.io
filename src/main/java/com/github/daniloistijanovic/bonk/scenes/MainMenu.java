package com.github.daniloistijanovic.bonk.scenes;

import com.github.daniloistijanovic.bonk.Main;
import com.github.daniloistijanovic.bonk.Pokret.Pokret;
import com.github.daniloistijanovic.bonk.multiplayer.Room;
import com.github.daniloistijanovic.bonk.utils.MusicUtil;
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
    @FXML
    private Button multiplayer;

    public void ShowProfile(ActionEvent event) throws IOException {
        Main.instance.changeScene("Profile.fxml");
        Main.instance.setTitle("Profile");
    }

    public void launchSinglePlayer(ActionEvent e) {
        MusicUtil.playLoopInternal("Parent 4.mp3");
        new Pokret().start();
        Main.instance.setTitle("Singleplayer");
    }

    public void playMultiplayer(ActionEvent e) {
        new Room().start();
        Main.instance.setTitle("Multiplayer");
    }

    public void userLogOut(ActionEvent event) throws IOException {
        Main.instance.loggedInUser = null;
        MusicUtil.stopThis();
        Main.instance.changeScene("Login.fxml");
        Main.instance.setTitle("Login Page");
    }

}
