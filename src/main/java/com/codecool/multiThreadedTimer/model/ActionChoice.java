package com.codecool.multiThreadedTimer.model;

import com.codecool.multiThreadedTimer.controller.AppController;

public class ActionChoice implements Runnable {

    private AppController controller;

    public ActionChoice(AppController controller) {
        this.controller = controller;
        new Thread(this, "ActionChoice").start();
    }

    @Override
    public void run() {
        System.out.println("ActionChoice started");
        while (true) {
            controller.actionChoice();
        }
    }
}
