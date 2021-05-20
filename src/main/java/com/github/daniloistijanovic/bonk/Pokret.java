package com.github.daniloistijanovic.bonk;

import com.github.daniloistijanovic.bonk.scenes.MainMenu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Pokret {

    private static GraphicsContext gc;
    final int gravitacija = 1;
    int x = 0;
    int y = 0;
    int x2 = 0;
    int y2 = 0;
    int velX = 0;
    int velY = 0;


    public Pokret() {

        BorderPane pane = new BorderPane();
        javafx.scene.canvas.Canvas canvas = new Canvas(Main.WINDOWSIZE.x, Main.WINDOWSIZE.y);
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        pane.setCenter(canvas);
        Scene scene = new Scene(pane, Main.WINDOWSIZE.x, Main.WINDOWSIZE.y);

        canvas.setOnKeyPressed(e -> {
            KeyCode c = e.getCode();

            if (c == KeyCode.LEFT) {
                velX = -5;
                velY = 0;
            }
            if (c == KeyCode.RIGHT) {
                velX = 5;
                velY = 0;
            }
            if (c == KeyCode.UP) {
                velX = 0;
                velY = -5;
            }
            if (c == KeyCode.DOWN) {
                velX = 0;
                velY = 5;
            }
            if (c == KeyCode.ESCAPE) {
                new MainMenu();
            }
        });
        canvas.setOnKeyReleased(e -> {
            velX = 0;
            velY = 0;
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> draw()), new KeyFrame(Duration.millis(16)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Main.setScene(scene);
    }

    public void draw() {
        gc.clearRect(0, 0, Main.WINDOWSIZE.x, Main.WINDOWSIZE.y);
        gc.setFill(Color.RED);
        gc.fillRect(x, y, 50, 30);
        uradi();
    }

    public void uradi() {
        if (x < 0) {
            velX = 0;
            x = 0;
        }
        if (y < 0) {
            velY = 0;
            y = 0;
        }

        if (x > 930) {
            velX = 0;
            x = 930;
        }
        if (y > 792) {
            velY = 0;
            y = 791;
        }

        if (x < 212 && y > 559)
            if (y < 610) {
                velY = 0;
                y = 558;
            }

        if (x < 212 && y < 650)
            if (y > 559) {
                velY = 0;
                y = 650;
            }


        if (x > 280 && x < 610 && y > 380) {
            if (y < 440) {
                velY = 0;
                y = 379;
            }
        }
        if (x > 280 && x < 610 && y < 450) {
            if (y > 380) {
                velY = 0;
                y = 450;
            }
        }

        if (x > 655 && y > 545) {
            if (y < 560) {
                velY = 0;
                y = 544;
            }
        }
        if (x > 655 && y < 600) {
            if (y > 545) {
                velY = 0;
                y = 600;
            }
        }

        x = x + velX;
        y = y + velY + gravitacija;
    }

}
