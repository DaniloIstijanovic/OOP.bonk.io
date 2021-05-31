package com.github.daniloistijanovic.bonk;

import java.util.Timer;
import java.util.TimerTask;

import static com.github.daniloistijanovic.bonk.utils.DebugUtil.debugger;

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
                tick();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            workingValue = limit;
        }
    });

    private Property(Builder builder) {
        debugger.memory("Instantiate " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
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

    public void start() {
        workingValue = base;
        if (delta == 0) {
            debugger.warning("delta je 0, treba nesto uraditi");
        } else {
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

    public void unPause() {
        thread.start();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        debugger.memory("Finalize " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
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

        Builder(double base) {
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
