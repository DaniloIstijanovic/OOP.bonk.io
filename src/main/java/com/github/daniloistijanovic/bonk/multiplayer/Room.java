package com.github.daniloistijanovic.bonk.multiplayer;

import com.github.daniloistijanovic.bonk.Main;
import com.github.daniloistijanovic.bonk.utils.MusicUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.daniloistijanovic.bonk.utils.DebugUtil.debugger;

public class Room {

    private static final ArrayList<KeyCode[]> preCon = new ArrayList<>();

    static {
        preCon.add(new KeyCode[]{KeyCode.LEFT, KeyCode.RIGHT, KeyCode.DOWN, KeyCode.UP});
        preCon.add(new KeyCode[]{KeyCode.A, KeyCode.D, KeyCode.S, KeyCode.W});
        preCon.add(new KeyCode[]{KeyCode.C, KeyCode.B, KeyCode.V, KeyCode.F});
        preCon.add(new KeyCode[]{KeyCode.J, KeyCode.L, KeyCode.K, KeyCode.I});
    }

    private final int xSize = Main.instance.WINDOWSIZE.x;
    private final int ySize = Main.instance.WINDOWSIZE.y;
    private final ArrayList<Rectangle> rectangles = new ArrayList<>();
    private final List<GameLogic> players = new ArrayList<>();
    private final GraphicsContext gc;
    private boolean isRunning;
    private int playersAlive;

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
        players.add(new GameLogic("Player 1 (arrows)", preCon.get(0)));
        players.add(new GameLogic("Player 2 (wasd)", preCon.get(1)));
        rectangles.add(new Rectangle((int) (Math.random() * 300) + 100, 10, Color.DARKRED, 0.7, 100 + Math.random() * 100, 100));
        rectangles.add(new Rectangle((int) (Math.random() * 300) + 100, 20, Color.DARKRED, 0.7, 200 + Math.random() * 100, 200));
        rectangles.add(new Rectangle((int) (Math.random() * 300) + 100, 35, Color.DARKRED, 0.7, 300 + Math.random() * 100, 300));
        rectangles.add(new Rectangle(25, 300, Color.DARKRED, 0.7, 600 + Math.random() * 100, 200));

    }

    public void addPlayer(GameLogic player) {
        players.add(player);
    }

    public void removePlayer(GameLogic player) {
        players.remove(player);
    }

    public void start() {
        for (GameLogic player : players) {
            playersAlive++;
            player.start();
        }
        MusicUtil.playLoopInternal("poisoned_atmosphere.mp3");
        isRunning = true;
        new Thread(() -> {
            while (isRunning) {
                //sudaranje loptica i gleamo gde su
                for (int i = 0; i < players.size(); i++) {
                    GameLogic p1 = players.get(i);
                    Circle c1 = p1.getCircle();
                    for (int j = i + 1; j < players.size(); j++) {
                        GameLogic p2 = players.get(j);
                        Circle c2 = p2.getCircle();
                        //jel se sudaraju
                        if (c1.isCollide(c2)) {
                            //skidamo health
                            double crash = c1.makeVector() - c2.makeVector();
                            if (crash < 0) {
                                //prvi je slabiji
                                p1.addHealth(-Math.abs(crash));
                            } else if (crash > 0) {
                                //drugi je slabiji
                                p2.addHealth(-Math.abs(crash));
                            }

                            c1.onCollide(c2);
                        }
                    }
                    //jel se ne vide
                    if (c1.getX() < 0 || c1.getX() + c1.getRadius() > xSize || c1.getY() < 0 || c1.getY() + c1.getRadius() > ySize) {
                        p1.addHealth(-0.1);
                    }
                }

                //sudaranje lopte i platforme
                for (GameLogic g : players) {
                    Circle lopta = g.getCircle();
                    for (Rectangle p : rectangles) {
                        if (lopta.isCollide(p)) {
                            lopta.onCollide(p);
                        }
                    }
                }

                for (GameLogic p : players) {
                    if (p.getHealth() <= 0) {
                        p.die();
                        playersAlive--;
                    }
                }
                if (playersAlive < 2) {
                    for (GameLogic p : players) {
                        if (p.getHealth() > 0) {
                            debugger.info("Pobednik je " + p.getName());
                        }
                    }
                    stop();
                    Platform.runLater(() -> {
                        try {
                            Main.instance.changeScene("MainMenu.fxml");
                            Main.instance.setTitle("Main Menu");
                            MusicUtil.playLoopInternal("Industrial.mp3");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void stop() {
        for (GameLogic player : players) {
            player.die();
        }
        MusicUtil.stopThis();
        isRunning = false;
    }

    private void draw() {
        gc.clearRect(0, 0, xSize, ySize);
        gc.setFill(Color.CORAL);
        gc.fillRect(0, 0, xSize, ySize);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        for (GameLogic p : players) {
            Circle c = p.getCircle();
            gc.setFill(p.color);
            gc.fillOval((int) c.getX(), (int) c.getY(), c.getRadius() * 2, c.getRadius() * 2);
            gc.fillText(p.getName(), (int) c.getRealX(), (int) c.getRealY() + c.getRadius() * 2);
            gc.fillText("Health: " + (int) p.getHealth(), (int) c.getRealX(), (int) c.getRealY() + c.getRadius() * 2 + 15);
        }
        for (Rectangle p : rectangles) {
            gc.setFill(p.getColor());
            gc.fillRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
        }
    }
}
