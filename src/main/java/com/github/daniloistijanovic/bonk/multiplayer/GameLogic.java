package com.github.daniloistijanovic.bonk.multiplayer;

import com.github.daniloistijanovic.bonk.Main;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.awt.geom.Point2D;

public class GameLogic {

    public static final boolean[] onlyOnePress = {false, false, false, false};
    private static final double MAXVELOCITY = 5;
    private static final int xSize = Main.instance.WINDOWSIZE.x;
    private static final int ySize = Main.instance.WINDOWSIZE.y;
    public final KeyCode[] keys;
    public final Color color;
    private final Circle circle = new Circle(20, Color.RED, 1, Math.random() * xSize, Math.random() * ySize);
    private final Property gravity = new Property.Builder(0.05).build();
    private final double glupostZaVetar = Math.random() * 0.002 - 0.004;
    private final Property wind = new Property.Builder(0).delay(10).delta(glupostZaVetar / 10).limit(glupostZaVetar).build();
    private final String name;
    public boolean[] keyAlreadyProcessed = new boolean[4];
    public boolean[] keyIsDown = new boolean[4];
    private boolean gameover = false;
    private double health;

    public GameLogic(String name, KeyCode[] keys) {
        this.name = name;
        this.keys = keys;
        color = Color.color(Math.random(), Math.random(), Math.random());
    }

    public void addHealth(double x) {
        health += x;
    }

    public void die() {
        gameover = true;
    }

    public Circle getCircle() {
        return circle;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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
        health = 1000;
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
                    Thread.sleep(10);
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
        if (circle.getXVel() < -MAXVELOCITY) {
            circle.setXVel(-MAXVELOCITY);
        } else if (circle.getXVel() > MAXVELOCITY) {
            circle.setXVel(MAXVELOCITY);
        }
        if (circle.getYVel() < -MAXVELOCITY) {
            circle.setYVel(-MAXVELOCITY);
        } else if (circle.getYVel() > MAXVELOCITY) {
            circle.setYVel(MAXVELOCITY);
        }
        // update pos
        circle.move(circle.getXVel(), circle.getYVel());
        // update vel
        circle.setXVel(circle.getXVel() * 0.99 + wind.getWorkingValue());
        circle.setYVel(circle.getYVel() * 0.99 + gravity.getWorkingValue());
    }

}
