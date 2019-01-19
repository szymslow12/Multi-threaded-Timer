package com.codecool.multiThreadedTimer.controller;

import com.codecool.multiThreadedTimer.model.UserInputs;

public class AppController {

    private boolean flag = false;
    private UserInputs userInputs;

    public AppController(UserInputs userInputs) {
        this.userInputs = userInputs;
    }


    public void run() {
        if (!flag) {

        }
    }


    private void alert(String msg) {
        System.out.println(msg);
    }
}
