package com.codecool.multiThreadedTimer.model;

public class StartTimer extends Thread {

    private long startTime;
    private long seconds;

    public StartTimer(long startTime, String name) {
        this.startTime = startTime;
        this.setName(name);
    }

    @Override
    public void run() {
        while(!this.isInterrupted()) {
            seconds = ((System.currentTimeMillis() - startTime) / 1000);
        }
    }


    public String toString() {
        return String.format("Timer: %s, Seconds: %s", this.getName(), seconds);
    }
}
