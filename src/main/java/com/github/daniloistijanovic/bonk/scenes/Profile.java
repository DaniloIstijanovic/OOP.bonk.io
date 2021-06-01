package com.github.daniloistijanovic.bonk.scenes;

import com.github.daniloistijanovic.bonk.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Profile {
    @FXML
    private Button back;

    public void BackMain(ActionEvent event) throws IOException {
        Main.instance.changeScene("MainMenu.fxml");
    }
}
