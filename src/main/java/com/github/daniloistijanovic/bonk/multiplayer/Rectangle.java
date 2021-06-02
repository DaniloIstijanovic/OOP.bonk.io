package com.github.daniloistijanovic.bonk.multiplayer;

import javafx.scene.paint.Color;

import static com.github.daniloistijanovic.bonk.utils.DebugUtil.debugger;

public class Rectangle extends Shape {
    private final int width;
    private final int height;

    public Rectangle(int width, int height, Color color, double bounciness, double x, double y) {
        super(color, bounciness, x, y);
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public boolean isCollide(Shape shape) {
        return false;
    }

    @Override
    public void onCollide(Shape shape) {
        if (shape instanceof Circle) {
            debugger.warning("treba obrnuto");
        } else if (shape instanceof Rectangle) {
            debugger.critical("dve platforme se sudaraju to ne sme");
        }
    }
}
