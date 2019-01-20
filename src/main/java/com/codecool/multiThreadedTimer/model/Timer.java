package com.codecool.multiThreadedTimer.model;

public class Timer {

    private boolean flag;
    private String name;

    private long startTime;
    private long seconds;

    private long threadId;

    public Timer(String name) {
        this.name = name;
        this.flag = true;
    }


    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public String toString() {
        return String.format("Timer: %s, ThreadID: %s, Seconds: %s", name, threadId, seconds);
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


    public String getName() {
        return name;
    }
}
