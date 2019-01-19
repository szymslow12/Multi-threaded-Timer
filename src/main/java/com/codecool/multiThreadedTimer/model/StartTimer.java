package com.codecool.multiThreadedTimer.model;

import com.codecool.multiThreadedTimer.controller.AppController;

public class StartTimer implements Runnable {

    private final long startTime;
    private boolean isStarted;
    private final String name;
    public AppController controller;

    public StartTimer(long startTime, String name, AppController controller) {
        this.startTime = startTime;
        this.name = name;
        this.controller = controller;
        isStarted = true;
    }

    @Override
    public void run() {
        try {
            if (isStarted) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        controller.showTimer(this);
        notify();
    }


    public synchronized void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }


    public String toString() {
        return String.format("Timer: %s, Current millis: %s", name, System.currentTimeMillis() - startTime);
    }
}
