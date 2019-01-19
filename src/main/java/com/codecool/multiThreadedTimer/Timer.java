package com.codecool.multiThreadedTimer;

public class Timer implements Runnable {

    private final long startTime;

    public Timer(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public void run() {
        System.out.println(startTime);
    }
}
