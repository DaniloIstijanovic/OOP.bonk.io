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
    private final Point2D playerPos = new Point2D.Double(Math.random() * xSize, Math.random() * ySize);
    private final Point2D velocity = new Point2D.Double();
    private final Property gravity = new Property.Builder(0.05).build();
    private final Property wind = new Property.Builder(0).delay(10).delta(0.002).limit(0.02).build();
    private final int radius = 20;
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

    public String getName() {
        return name;
    }

    public Point2D getPlayerPos() {
        return playerPos;
    }

    public int getRadius() {
        return radius;
    }

    public void start() {
        gameLoop();
    }

    private void doAction(int i) {
        double speed = 0.2;
        switch (i) {
            case 0:
                velocity.setLocation(velocity.getX() - speed, velocity.getY());
                break;
            case 1:
                velocity.setLocation(velocity.getX() + speed, velocity.getY());
                break;
            case 2:
                velocity.setLocation(velocity.getX(), velocity.getY() + speed);
                break;
            case 3:
                velocity.setLocation(velocity.getX(), velocity.getY() - speed);
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
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private Point2D getVelocity() {
        return velocity;
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
        if (velocity.getX() < -5) {
            velocity.setLocation(-5, velocity.getY());
        } else if (velocity.getX() > 5) {
            velocity.setLocation(5, velocity.getY());
        }

        // terminal vel
        if (velocity.getY() < -5) {
            velocity.setLocation(velocity.getX(), -5);
        } else if (velocity.getY() > 5) {
            velocity.setLocation(velocity.getX(), 5);
        }
        // update pos
        playerPos.setLocation(playerPos.getX() + velocity.getX(), playerPos.getY() + velocity.getY());
        // update vel
        velocity.setLocation(velocity.getX() * 0.99 + wind.getWorkingValue(),
            velocity.getY() * 0.99 + gravity.getWorkingValue());

        // oob
        if (playerPos.getX() < 0) {
            velocity.setLocation(-velocity.getX(), velocity.getY());
            playerPos.setLocation(0, playerPos.getY());
        } else if (playerPos.getX() > xSize - getRadius() * 2) {
            velocity.setLocation(-velocity.getX(), velocity.getY());
            playerPos.setLocation(xSize - getRadius() * 2, playerPos.getY());
        }
        // oob
        if (playerPos.getY() < 0) {
            velocity.setLocation(velocity.getX(), -velocity.getY());
            playerPos.setLocation(playerPos.getX(), 0);
        } else if (playerPos.getY() > ySize - getRadius() * 2) {
            velocity.setLocation(velocity.getX(), -velocity.getY());
            playerPos.setLocation(playerPos.getX(), ySize - getRadius() * 2);
        }
    }

}
