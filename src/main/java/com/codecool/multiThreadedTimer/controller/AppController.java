package com.codecool.multiThreadedTimer.controller;

import com.codecool.multiThreadedTimer.model.StartTimer;
import com.codecool.multiThreadedTimer.model.UserInputs;

public class AppController {

    private boolean flag = false;
    private UserInputs userInputs;
    private String userChoice;
    private int timerNumber = 0;

    public AppController(UserInputs userInputs) {
        this.userInputs = userInputs;
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
                "'end timerName' - ends timer");
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

    }


    private void handleUserChoice() {
        String[] splitedUserChoice = userChoice.split(" ");
        String action = splitedUserChoice[0];
        String timerName = splitedUserChoice[1];
        if (action.equalsIgnoreCase("start")) {
            new Thread(new StartTimer(System.currentTimeMillis(), timerName, this), timerName).start();
            flag = false;
            notify();
        } else if (action.equalsIgnoreCase("end")) {
            alert("Ended");
            flag = false;
            notify();
        }
    }


    private void alert(String msg) {
        System.out.println(msg);
    }
}
