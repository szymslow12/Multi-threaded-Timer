package com.codecool.multiThreadedTimer.controller;

import com.codecool.multiThreadedTimer.view.View;
import com.codecool.multiThreadedTimer.model.threads.TimerThread;
import com.codecool.multiThreadedTimer.model.Timer;
import com.codecool.multiThreadedTimer.model.UserInputs;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

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
        view.alert("\nEnter what you want to do?\n\t" +
                "'start timerName' - start new timer\n\t" +
                "'stop timerName' - stop timer\n\t" +
                "'check timerName' - check given timer\n\t" +
                "'check' - check all timers\n");
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
            handleStop(timerName);
        } else if (action.equalsIgnoreCase("check")) {
            handleCheck(timerName);
        } else {
            view.alert("Bad action!");
            setFlagFalseAndNotify();
        }
    }


    private void handleStart(String timerName) {
        Timer timer = timersMap.get(timerName);
        if (timer == null) {
            createNewTimer(timerName);
        } else {
            resumeOldTimer(timer);
        }
        setFlagFalseAndNotify();
    }

    private void createNewTimer(String timerName) {
        Timer timer = new Timer(timerName);
        timer.setStartTime(System.currentTimeMillis());
        timersMap.put(timerName, timer);
        TimerThread thread = new TimerThread(timerName, timer);
        timer.setThreadId(thread.getId());
        view.alert("Created " + timer);
    }


    private void resumeOldTimer(Timer timer) {
        timer.setStartTime(System.currentTimeMillis());
        timer.setFlag(true);
        view.alert("Resumed " + timer);
    }


    private void handleStop(String timerName) {
        Timer timer = timersMap.get(timerName);
        timer.setFlag(false);
        interruptTimerThread(timerName);
        view.alert("Stopped " + timer);
        setFlagFalseAndNotify();
    }


    private void interruptTimerThread(String timerName) {
        Set<Thread> threads = Thread.getAllStackTraces().keySet();
        Predicate<Thread> byName = thread -> thread.getName().equals(timerName);
        Optional<Thread> optional = threads.stream().filter(byName).findFirst();
        if (optional.isPresent()) {
            Thread thread = optional.get();
            thread.interrupt();
        }
    }


    private void handleCheck(String timerName) {
        if (timerName != null) {
            view.alert("Check " + timersMap.get(timerName));
        } else {
            view.printTimers(timersMap);
        }
        setFlagFalseAndNotify();
    }


    private void setFlagFalseAndNotify() {
        flag = false;
        notify();
    }
}
