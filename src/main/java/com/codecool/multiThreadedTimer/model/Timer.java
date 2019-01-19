package com.codecool.multiThreadedTimer.model;

public class Timer {

    private boolean flag;
    private String name;

    private long startTime;
    private long seconds;

    public Timer(String name) {
        this.name = name;
        this.flag = true;
    }

    public String toString() {
        return String.format("Timer: %s, Seconds: %s", name, seconds);
    }


    public synchronized void startCounting() {
        if (flag) {
            seconds = (System.currentTimeMillis() - startTime) / 1000;
        }
    }


    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }


    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}