package com.codecool.multiThreadedTimer.view;

import com.codecool.multiThreadedTimer.model.Timer;

import java.util.Map;
import java.util.stream.Stream;

public class View {

    public void alert(String msg) {
        System.out.println(msg);
    }


    public void printTimers(Map<String, Timer> timers) {
        Stream.of(timers.keySet()).forEach(timer -> alert(timers.get(timer).toString()));
    }
}
