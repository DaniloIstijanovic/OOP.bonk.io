package com.github.daniloistijanovic.bonk.multiplayer;

import javafx.scene.paint.Color;

public abstract class Shape {
    private final Color color;
    private final double bounciness;
    private double x;
    private double y;

    public Shape(Color color, double bounciness, double x, double y) {
        this.color = color;
        this.bounciness = bounciness;
        this.x = x;
        this.y = y;
    }

    public double getBounciness() {
        return bounciness;
    }

    public Color getColor() {
        return color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract boolean isCollide(Shape shape);

    public void move(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public abstract void onCollide(Shape shape);
}
