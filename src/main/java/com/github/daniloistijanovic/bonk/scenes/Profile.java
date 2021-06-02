package com.github.daniloistijanovic.bonk.scenes;

import com.github.daniloistijanovic.bonk.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import static com.github.daniloistijanovic.bonk.utils.DebugUtil.debugger;
public class Profile {
    @FXML
    private Button back;
    @FXML
    private Label userprint;

    public Profile(){
    	String userID = Main.instance.userID;
    	debugger.info(userID);
    	userprint.setText(userID);
    	
    }
    public void BackMain(ActionEvent event) throws IOException {
        Main.instance.changeScene("MainMenu.fxml");
    }
}
