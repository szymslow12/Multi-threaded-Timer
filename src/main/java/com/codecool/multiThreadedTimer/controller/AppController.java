package com.codecool.multiThreadedTimer.controller;

import com.codecool.multiThreadedTimer.model.StartTimer;
import com.codecool.multiThreadedTimer.model.Timer;
import com.codecool.multiThreadedTimer.model.UserInputs;

import java.util.HashMap;
import java.util.Map;

public class AppController {

    private boolean flag = false;
    private UserInputs userInputs;
    private String userChoice;
    private Map<String, Timer> timersMap;

    public AppController(UserInputs userInputs) {
        this.userInputs = userInputs;
        timersMap = new HashMap<>();
    }


    public String getUserChoice() {
        return userChoice;
    }


    public synchronized void actionChoice() {
        try {
            if (flag) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        alert("Enter what you want to do?\n\t" +
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
            Timer timer = timersMap.get(timerName);
            if (timer == null) {
                timer = new Timer(timerName);
                timersMap.put(timerName, timer);
                timer.setStartTime(System.currentTimeMillis());
                new StartTimer(timerName, timer).start();
            } else {
                timer.setStartTime(System.currentTimeMillis());
                timer.setFlag(true);
            }
            flag = false;
            notify();
        } else if (action.equalsIgnoreCase("stop") && timerName != null) {
            Timer timer = timersMap.get(timerName);
            timer.setFlag(false);
            flag = false;
            notify();
        } else if (action.equalsIgnoreCase("check")) {
            printTimers();
            flag = false;
            notify();
        } else {
            alert("Bad action!");
            flag = false;
            notify();
        }
    }


    private void alert(String msg) {
        System.out.println(msg);
    }


    private void printTimers() {
        for (String key: timersMap.keySet()) {
            alert(timersMap.get(key).toString());
        }
    }
}
