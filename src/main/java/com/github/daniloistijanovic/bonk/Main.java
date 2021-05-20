package com.github.daniloistijanovic.bonk;

import com.github.daniloistijanovic.bonk.scenes.Login;
import com.github.daniloistijanovic.bonk.utils.DebugUtil;
import com.github.daniloistijanovic.bonk.utils.MiscUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

/*
 * pozvo ga preload
 */
public class Main extends Application {

    public static final boolean hocuMuziku = true;
    public static final Point[] COMMONSIZES = {
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
    public static Point WINDOWSIZE = COMMONSIZES[8];
    public static final Font fontBig = Font.loadFont(MiscUtil.getResource("fonts/neo-sans-bold.otf"), Main.WINDOWSIZE.y * 0.1f);
    public static final Font fontSmall = Font.loadFont(MiscUtil.getResource("fonts/neo-sans-bold.otf"), Main.WINDOWSIZE.y * 0.05f);
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {

        if (DebugUtil.debugMethod == DebugUtil.DebugMethod.FILE) {
            try {
                DebugUtil.fw = new FileWriter("data/txt/debug.txt");
            } catch (IOException e) {
                DebugUtil.debugMethod = DebugUtil.DebugMethod.CONSOLE;
                e.printStackTrace();
                DebugUtil.debug(DebugUtil.DebugReason.FILE, "Nece da radi debug u file ");
            }
        }
        if (args.length > 1) {
            WINDOWSIZE = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }
        launch();
    }

    public static void setScene(Scene scene) {
        stage.setScene(scene);
    }

    @Override
    public void start(Stage stage) {
        Main.stage = stage;
        stage.getIcons().add(new Image(MiscUtil.getResource("img/image.png")));
        stage.setTitle("Bonk");

        new Login();
        stage.show();
    }

    //enum GAME ce se koristiti za proveru stanja da li je u igrici ili u main manu-u
    @SuppressWarnings("unused")
    public enum Menu {
        MAINMENU, OPTIONS, ROOM, GAME
    }


} 