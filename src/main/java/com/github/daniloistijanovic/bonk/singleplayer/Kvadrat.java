package com.github.daniloistijanovic.bonk.singleplayer;

public class Kvadrat {

    double x;
    double y;
    double width;
    double height;

    public Kvadrat() {
        this.setPosition(0, 0);
        this.setSize(1, 1);
    }

    public Kvadrat(double x, double y, double w, double h) {
        this.setPosition(x, y);
        this.setSize(w, h);
    }

    public boolean overlaps(Kvadrat other) {
        boolean ne = this.x + this.width < other.x || other.x + other.width < this.x || this.y + this.height < other.y || other.y + other.height < this.y;
        return !ne;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(double w, double h) {
        this.width = w;
        this.height = h;
    }
}
