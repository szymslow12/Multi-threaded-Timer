package com.codecool.multiThreadedTimer.view;

import com.codecool.multiThreadedTimer.model.Timer;

import java.util.Map;

public class View {

    public void alert(Object msg) {
        System.out.println(msg);
    }


    public void printTimers(Map<String, Timer> timers) {
        timers.keySet().forEach(timer -> alert(timers.get(timer)));
    }
}
