package com.github.daniloistijanovic.bonk.multiplayer;

import com.github.daniloistijanovic.bonk.Main;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.awt.geom.Point2D;

public class GameLogic {

    public static final boolean[] onlyOnePress = {false, false, false, false};
    private static final int xSize = Main.instance.WINDOWSIZE.x;
    private static final int ySize = Main.instance.WINDOWSIZE.y;
    public final KeyCode[] keys;
    public final Color color;
    private final Circle circle = new Circle(20, Color.RED, 1, Math.random() * xSize, Math.random() * ySize);
    private final Property gravity = new Property.Builder(0.05).build();
    private final Property wind = new Property.Builder(0).delay(10).delta(0.002).limit(0.02).build();
    private final String name;
    public boolean[] keyAlreadyProcessed = new boolean[4];
    public boolean[] keyIsDown = new boolean[4];
    private boolean gameover = false;

    public GameLogic(String name, KeyCode[] keys) {
        this.name = name;
        this.keys = keys;
        color = Color.color(Math.random(), Math.random(), Math.random());
    }

    public void die() {
        gameover = true;
    }

    public Circle getCircle() {
        return circle;
    }

    public String getName() {
        return name;
    }

    public Point2D getPlayerPos() {
        return new Point2D.Double(circle.getX(), circle.getY());
    }

    public int getRadius() {
        return circle.getRadius();
    }

    public void start() {
        gameLoop();
    }

    private void doAction(int i) {
        double speed = 0.2;
        switch (i) {
            case 0:
                circle.addXVel(-speed);
                break;
            case 1:
                circle.addXVel(speed);
                break;
            case 2:
                circle.addYVel(speed);
                break;
            case 3:
                circle.addYVel(-speed);
                break;
        }
    }

    private void gameLoop() {
        new Thread(() -> {
            gravity.start();
            wind.start();
            while (!gameover) {
                processKeys();

                updatePos();

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void processKeys() {
        for (int i = 0; i < keys.length; i++) {
            if (keyIsDown[i] && !(onlyOnePress[i] && keyAlreadyProcessed[i])) {
                doAction(i);
                keyAlreadyProcessed[i] = true;
            }
        }
    }

    private void updatePos() {

        // terminal vel
        if (circle.getXVel() < -20) {
            circle.setXVel(-20);
        } else if (circle.getXVel() > 20) {
            circle.setXVel(20);
        }
        if (circle.getYVel() < -20) {
            circle.setYVel(-20);
        } else if (circle.getYVel() > 20) {
            circle.setYVel(20);
        }
        // update pos
        circle.move(circle.getXVel(), circle.getYVel());
        // update vel
        circle.setXVel(circle.getXVel() * 0.99 + wind.getWorkingValue());
        circle.setYVel(circle.getYVel() * 0.99 + gravity.getWorkingValue());
/*
        // oob
        if (playerPos.getX() < 0) {
            velocity.setLocation(-velocity.getX(), velocity.getY());
            playerPos.setLocation(-playerPos.getX(), playerPos.getY());
        } else if (playerPos.getX() > xSize - getRadius() * 2) {
            velocity.setLocation(-velocity.getX(), velocity.getY());
            playerPos.setLocation(xSize - getRadius() * 2, playerPos.getY());
        }
        // oob
        if (playerPos.getY() < 0) {
            velocity.setLocation(velocity.getX(), -velocity.getY());
            playerPos.setLocation(playerPos.getX(), -playerPos.getY());
        } else if (playerPos.getY() > ySize - getRadius() * 2) {
            velocity.setLocation(velocity.getX(), -velocity.getY());
            playerPos.setLocation(playerPos.getX(), ySize - getRadius() * 2);
        }

 */
    }

}
