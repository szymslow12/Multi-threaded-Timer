package com.codecool.multiThreadedTimer.model.threads;

import com.codecool.multiThreadedTimer.model.Timer;

public class TimerThread extends Thread {

    private Timer timer;

    public TimerThread(String name, Timer timer) {
        this.timer = timer;
        this.setName(name);
        new Thread(this, name).start();
    }

    @Override
    public void run() {
        while(true) {
            timer.startCounting();
        }
    }
}
