package com.github.daniloistijanovic.bonk.multiplayer;

import java.util.Timer;
import java.util.TimerTask;

/*
 * univerzalna klasa za automatsko menjanje promenljivih:
 * posle odredjenog vremena se aktivira
 * zatim se uvecava ili smanjuje dok se ne dodje do granice
 * planirano: dodatak za pseudo kod koji korisnik moze da unese
 * koriscen je builder pattern
 */
public class Property {

    private final double base;
    // ako je negativno nez nam sta onda recimo da je korisnik glup za sad
    private final int delay;
    // povecanje ili smanjenje po sekundi
    private final double delta;
    private final double limit;

    // false ako je delta < 0, true ako je delta > 0, ubrzava kod
    private final boolean mode;
    // promenljiva koju zapravo aktivno koristimo
    private double workingValue;

    private final Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (mode ? (getWorkingValue() < getLimit()) : (getLimit() < getWorkingValue())) {
                try {
                    tick();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            workingValue = limit;
        }
    });

    private Property(Builder builder) {
        this.base = builder.base;
        this.delay = builder.delay;
        this.delta = builder.delta;
        this.limit = builder.limit;

        mode = delta > 0;
    }

    public double getWorkingValue() {
        return workingValue;
    }

    public void pause() {
        thread.interrupt();
    }

    public void resume() {
        thread.start();
    }

    public void start() {
        workingValue = base;
        if (delta != 0) {
            Timer timer = new Timer();

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    thread.start();
                }
            };

            timer.schedule(task, getIncreaseDelay() * 1000L);
        }
    }

    private int getIncreaseDelay() {
        return delay;
    }

    private double getLimit() {
        return limit;
    }

    private void tick() {
        workingValue += delta;
    }

    public static class Builder {

        private double base;
        private int delay;
        private double delta;
        private double limit;

        public Builder(double base) {
            this.base = base;
        }

        public Builder base(double base) {
            this.base = base;
            return this;
        }

        public Property build() {
            return new Property(this);
        }

        public Builder delay(int delay) {
            this.delay = delay;
            return this;
        }

        public Builder delta(double increase) {
            this.delta = increase;
            return this;
        }

        public Builder limit(double limit) {
            this.limit = limit;
            return this;
        }
    }
}
