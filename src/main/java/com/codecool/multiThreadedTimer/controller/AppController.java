package com.codecool.multiThreadedTimer.controller;

import com.codecool.multiThreadedTimer.model.UserInputs;

public class AppController {

    private boolean flag = false;
    private UserInputs userInputs;
    private String userChoice;

    public AppController(UserInputs userInputs) {
        this.userInputs = userInputs;
    }


    public String getUserChoice() {
        return userChoice;
    }


    public synchronized void actionChoice() {
        try {
            if (!flag) {
                alert("Enter what you want to do?\n\t" +
                        "'start timerName' - start new timer\n\t" +
                        "'end timerName' - ends timer");
                userChoice = userInputs.getValue();
                flag = true;
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized void startTimer() {
        if (flag) {

        }
    }


    private void alert(String msg) {
        System.out.println(msg);
    }
}
