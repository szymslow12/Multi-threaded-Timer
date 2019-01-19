package com.codecool.multiThreadedTimer.model;

public class StartTimer extends Thread {

    private Timer timer;

    public StartTimer(String name, Timer timer) {
        this.timer = timer;
        this.setName(name);
    }

    @Override
    public void run() {
        while(true) {
            timer.startCounting();
        }
    }
}
