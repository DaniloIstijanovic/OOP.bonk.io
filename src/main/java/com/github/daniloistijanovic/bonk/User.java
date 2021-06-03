package com.github.daniloistijanovic.bonk;

import java.io.Serializable;

public class User implements Serializable {

    private final String name;
    private final String pass;
    private int highScore;

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public void tryToSetHighScore(int score) {
        if (this.highScore < score) {
            highScore = score;
        }
    }

}
