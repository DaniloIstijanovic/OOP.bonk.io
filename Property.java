//jos neznamo kako ce da se zove package

//namena: promenljiva koja se menja kako prolazi vreme, recimo ako prodje 10 minuta polako pocinje da se gubi health
class Property {
        private double base;
        //ako je negativno nez nam sta onda recimo da je korisnik glup za sad
        private int delay;
        //povecanje ili smanjenje po sekundi
        private double delta;
        private double limit;
        
        //false ako je delta < 0, true ako je delta > 0, ubrzava kod
        private boolean mode;
        //promenljiva koju zapravo aktivno koristimo
        private double workingValue;

        private Property(double base, int delay, double delta, double limit) {
            this.base = base;
            this.delay = delay;
            this.delta = delta;
            if (delta > 0) {
                mode = true;
            } else {
                mode = false;
            }
            this.limit = limit;
        }

        private double getWorkingValue() {
            return workingValue;
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

        private void start() {
            workingValue = base;

            Timer timer = new Timer();

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    new Thread(new Runnable() {
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
                    }).start();
                }
            };

            timer.schedule(task, getIncreaseDelay() * 1000);
        }

    }
/*todo
poboljsati Thread.sleep da bude preciznije
funkcija stop()
setter za delta mozda???
*/
