package com.codecool.multiThreadedTimer.model.threads;

import com.codecool.multiThreadedTimer.controller.AppController;

public class ActionHandler implements Runnable{

    private AppController controller;

    public ActionHandler(AppController controller) {
        this.controller = controller;
        new Thread(this, "ActionHandler").start();
    }

    @Override
    public void run() {
        while (true) {
            controller.handleAction();
        }
    }
}
