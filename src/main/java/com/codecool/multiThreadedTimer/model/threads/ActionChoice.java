package com.codecool.multiThreadedTimer.model.threads;

import com.codecool.multiThreadedTimer.controller.AppController;

public class ActionChoice implements Runnable {

    private AppController controller;

    public ActionChoice(AppController controller) {
        this.controller = controller;
        new Thread(this, "ActionChoice").start();
    }

    @Override
    public void run() {
        while (true) {
            controller.actionChoice();
        }
    }
}
