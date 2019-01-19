package com.codecool.multiThreadedTimer.controller;

import com.codecool.multiThreadedTimer.view.View;
import com.codecool.multiThreadedTimer.model.threads.StartTimer;
import com.codecool.multiThreadedTimer.model.Timer;
import com.codecool.multiThreadedTimer.model.UserInputs;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class AppController {

    private boolean flag = false;
    private UserInputs userInputs;
    private View view;
    private String userChoice;
    private Map<String, Timer> timersMap;

    public AppController(UserInputs userInputs, View view) {
        this.userInputs = userInputs;
        timersMap = new HashMap<>();
        this.view = view;
    }


    public synchronized void actionChoice() {
        try {
            if (flag) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        view.alert("Enter what you want to do?\n\t" +
                "'start timerName' - start new timer\n\t" +
                "'stop timerName' - stop timer");
        userChoice = userInputs.getValue();
        flag = true;
        notify();
    }


    public synchronized void handleAction() {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        handleUserChoice();
    }


    private void handleUserChoice() {
        String[] splitedUserChoice = userChoice.split(" ");
        String action = splitedUserChoice[0];
        String timerName = splitedUserChoice.length > 1 ? splitedUserChoice[1]: null;
        if (action.equalsIgnoreCase("start") && timerName != null) {
            handleStart(timerName);
        } else if (action.equalsIgnoreCase("stop") && timerName != null) {
            Timer timer = timersMap.get(timerName);
            timer.setFlag(false);
            Set<Thread> threads = Thread.getAllStackTraces().keySet();
            Optional<Thread> optional = threads.stream().filter(thread -> thread.getName().equals(timerName)).findFirst();
            if (optional.isPresent()) {
                Thread thread = optional.get();
                thread.interrupt();
            }
            view.alert("Stopped " + timer);
            flag = false;
            notify();
        } else if (action.equalsIgnoreCase("check")) {
            view.printTimers(timersMap);
            flag = false;
            notify();
        } else {
            view.alert("Bad action!");
            flag = false;
            notify();
        }
    }


    private void handleStart(String timerName) {
        Timer timer = timersMap.get(timerName);
        if (timer == null) {
            createNewTimer(timerName);
        } else {
            resumeOldTimer(timer);
        }
        flag = false;
        notify();
    }

    private void createNewTimer(String timerName) {
        Timer timer = new Timer(timerName);
        timer.setStartTime(System.currentTimeMillis());
        timersMap.put(timerName, timer);
        new StartTimer(timerName, timer);
        view.alert("Created " + timer);
    }


    private void resumeOldTimer(Timer timer) {
        timer.setStartTime(System.currentTimeMillis());
        timer.setFlag(true);
        view.alert("Resumed " + timer);
    }
}
