package com.codecool.multiThreadedTimer.model;

import com.codecool.multiThreadedTimer.controller.AppController;

public class ActionHandler implements Runnable{

    private AppController controller;

    public ActionHandler(AppController controller) {
        this.controller = controller;
        new Thread(this, "ActionHandler").start();
    }

    @Override
    public void run() {
        controller.handleAction();
    }
}
