package com.github.daniloistijanovic.bonk.scenes;

import com.github.daniloistijanovic.bonk.Main;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class Login {

    static final Map<String, String> logininfo = new HashMap<>();

    static {
        logininfo.put("123", "456");
        logininfo.put("abc", "def");
        logininfo.put("xXxEpicGamerxXx", "fortnite");
    }

    final Button loginButton = new Button("Login");
    final Button resetButton = new Button("Reset");
    final TextField userIDField = new TextField();
    final PasswordField userPasswordField = new PasswordField();
    final Label userID = new Label("username:");
    final Label userPasswordLabel = new Label("password:");
    final Label message = new Label();

    public Login() {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, Main.WINDOWSIZE.x, Main.WINDOWSIZE.y);

        setBounds(userID, 50, 100, 500, 500);
        setBounds(userPasswordLabel, 500, 150, 400, 400);
        setBounds(message, 125, 250, 250, 35);
        //message.setFont(new Font(null, Font.ITALIC, 25));
        setBounds(userIDField, 125, 100, 200, 200);
        setBounds(userPasswordField, 125, 150, 200, 300);

        setBounds(loginButton, 125, 200, 600, 25);
        loginButton.setOnMouseClicked(e -> {
            String userID = userIDField.getText();
            String password = userPasswordField.getText();

            if (logininfo.containsKey(userID)) {
                if (logininfo.get(userID).equals(password)) {
                    //message.setForeground(Color.green);
                    message.setText("Radi");
                    new MainMenu();
                } else {
                    //message.setForeground(Color.red);
                    message.setText("Ne radi");
                }

            } else {
                //message.setForeground(Color.red);
                message.setText("nepostojeci user");
            }
        });
        setBounds(resetButton, 225, 200, 100, 25);
        resetButton.setOnMouseClicked(e -> {
            userIDField.setText("");
            userPasswordField.setText("");
        });


        pane.getChildren().add(userID);
        pane.getChildren().add(userPasswordLabel);
        pane.getChildren().add(message);
        pane.getChildren().add(userIDField);
        pane.getChildren().add(userPasswordField);
        pane.getChildren().add(loginButton);
        pane.getChildren().add(resetButton);

        Main.setScene(scene);
    }

    private void setBounds(Node e, int x, int y, int xx, int yy) {
        e.relocate(x, y);
        e.setLayoutX(xx);
        e.setLayoutY(yy);
    }
}
