package com.codecool.multiThreadedTimer.controller;

import com.codecool.multiThreadedTimer.view.View;
import com.codecool.multiThreadedTimer.model.threads.StartTimer;
import com.codecool.multiThreadedTimer.model.Timer;
import com.codecool.multiThreadedTimer.model.UserInputs;

import java.util.HashMap;
import java.util.Map;

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
            Timer timer = timersMap.get(timerName);
            if (timer == null) {
                timer = new Timer(timerName);
                timer.setStartTime(System.currentTimeMillis());
                timersMap.put(timerName, timer);
                new StartTimer(timerName, timer);
                view.alert("Created " + timer);
            } else {
                timer.setStartTime(System.currentTimeMillis());
                timer.setFlag(true);
            }
            flag = false;
            notify();
        } else if (action.equalsIgnoreCase("stop") && timerName != null) {
            Timer timer = timersMap.get(timerName);
            timer.setFlag(false);
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
}
