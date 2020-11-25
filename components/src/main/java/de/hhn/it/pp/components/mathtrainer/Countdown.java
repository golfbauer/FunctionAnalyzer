package de.hhn.it.pp.components.mathtrainer;

import java.util.Timer;
import java.util.TimerTask;


public class Countdown {
    private int count = 5;

    public Countdown() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(count);
                if (count > 0) {
                    count--;
                }

                if (count == 0) {

                }
            }
        };
        timer.schedule(task, 0, 1000);
    }
}
