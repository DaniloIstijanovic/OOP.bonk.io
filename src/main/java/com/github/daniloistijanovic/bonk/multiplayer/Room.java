package com.github.daniloistijanovic.bonk.multiplayer;

import com.github.daniloistijanovic.bonk.Main;
import com.github.daniloistijanovic.bonk.utils.MusicUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private static final int xSize = Main.instance.WINDOWSIZE.x;
    private static final int ySize = Main.instance.WINDOWSIZE.y;
    private static final ArrayList<KeyCode[]> preCon = new ArrayList<>();
    private static final ArrayList<Platform> platforms = new ArrayList<>();

    static {
        preCon.add(new KeyCode[]{KeyCode.LEFT, KeyCode.RIGHT, KeyCode.DOWN, KeyCode.UP});
        preCon.add(new KeyCode[]{KeyCode.A, KeyCode.D, KeyCode.S, KeyCode.W});
        preCon.add(new KeyCode[]{KeyCode.C, KeyCode.B, KeyCode.V, KeyCode.F});
        preCon.add(new KeyCode[]{KeyCode.J, KeyCode.L, KeyCode.K, KeyCode.I});
    }

    private final List<GameLogic> players = new ArrayList<>();
    private final GraphicsContext gc;

    public Room() {
        BorderPane pane = new BorderPane();
        Canvas canvas = new Canvas(xSize, ySize);
        gc = canvas.getGraphicsContext2D();
        pane.setCenter(canvas);
        Scene scene = new Scene(pane, xSize, ySize);

        canvas.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            for (GameLogic p : players) {
                KeyCode[] keys = p.keys;
                for (int i = 0; i < keys.length; i++) {
                    if (
                        key.equals(keys[i])) {
                        p.keyIsDown[i] = true;
                        break;
                    }
                }
            }
        });

        canvas.setOnKeyReleased(e -> {
            KeyCode key = e.getCode();
            for (GameLogic p : players) {
                KeyCode[] keys = p.keys;
                for (int i = 0; i < keys.length; i++) {
                    if (key.equals(keys[i])) {
                        p.keyIsDown[i] = false;
                        p.keyAlreadyProcessed[i] = false;
                        break;
                    }
                }
            }

        });

        Main.instance.setScene(scene);
        canvas.setFocusTraversable(true);

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> draw()), new KeyFrame(Duration.millis(16)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        //privremeno
        players.add(new GameLogic("glupan", preCon.get(0)));
        players.add(new GameLogic("jos jedan glupan", preCon.get(1)));
    }

    public void addPlayer(GameLogic player) {
        players.add(player);
    }

    public void removePlayer(GameLogic player) {
        players.remove(player);
    }

    public void start() {
        for (GameLogic player : players) {
            player.start();
        }
        MusicUtil.playLoopInternal("sway.mp3");
    }

    public void stop() {
        for (GameLogic player : players) {
            player.die();
        }
        MusicUtil.stopThis();
    }

    private void draw() {
        gc.clearRect(0, 0, xSize, ySize);
        gc.setFill(Color.DARKBLUE);
        gc.fillRect(0, 0, xSize, ySize);
        for (GameLogic p : players) {
            gc.setFill(p.color);
            gc.fillOval((int) p.getPlayerPos().getX(), (int) p.getPlayerPos().getY(), p.getRadius() * 2, p.getRadius() * 2);
            gc.fillText(p.getName(), (int) p.getPlayerPos().getX(), (int) p.getPlayerPos().getY());
        }
    }
}
