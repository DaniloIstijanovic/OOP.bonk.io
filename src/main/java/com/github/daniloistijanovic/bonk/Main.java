package com.github.daniloistijanovic.bonk;

import com.github.daniloistijanovic.bonk.utils.DebugUtil;
import com.github.daniloistijanovic.bonk.utils.MiscUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;

/*
 * pozvo ga preload
 */
public class Main extends Application {
    private static final Point[] COMMONSIZES = {
        //4:3
        new Point(640, 480),
        new Point(800, 600),
        new Point(960, 720),
        new Point(1024, 768),
        new Point(1280, 960),
        //16:9
        new Point(640, 360),
        new Point(854, 480),
        new Point(960, 540),
        new Point(1280, 720),
        new Point(1920, 1080),
    };
    public static Main instance;
    public static boolean hocuMuziku = true;
    public final Point WINDOWSIZE = COMMONSIZES[8];
    public final Font fontBig = Font.loadFont(MiscUtil.getResource("fonts/neo-sans-bold.otf"), WINDOWSIZE.y * 0.1f);
    public final Font fontSmall = Font.loadFont(MiscUtil.getResource("fonts/neo-sans-bold.otf"), WINDOWSIZE.y * 0.05f);
    private Stage stage;

    public static void main(String[] args) {

        if (DebugUtil.debugger.debugMethod == DebugUtil.DebugMethod.FILE) {
            try {
                DebugUtil.debugger.fw = new FileWriter("data/txt/debug.txt");
            } catch (IOException e) {
                DebugUtil.debugger.debugMethod = DebugUtil.DebugMethod.CONSOLE;
                e.printStackTrace();
                DebugUtil.debugger.file("Nece da radi debug u file ");
            }
        }
        launch();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(MiscUtil.getResourceURL(fxml));
        stage.getScene().setRoot(pane);
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
    }

    @Override
    public void start(Stage primaryStage) {
        instance = this;
        stage = primaryStage;
        primaryStage.setResizable(false);
        Parent root = null;
        try {
            root = FXMLLoader.load(MiscUtil.getResourceURL("fxml/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(new Scene(root, WINDOWSIZE.x, WINDOWSIZE.y));
        primaryStage.show();
    }

    @SuppressWarnings("unused")
    public enum Menu {
        MAINMENU, OPTIONS, ROOM, GAME
    }


} 