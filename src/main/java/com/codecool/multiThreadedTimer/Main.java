package com.codecool.multiThreadedTimer;

import com.codecool.multiThreadedTimer.controller.AppController;
import com.codecool.multiThreadedTimer.model.threads.ActionChoice;
import com.codecool.multiThreadedTimer.model.threads.ActionHandler;
import com.codecool.multiThreadedTimer.model.UserInputs;
import com.codecool.multiThreadedTimer.view.View;

public class Main {

    public static void main(String[] args) {
        AppController controller = new AppController(new UserInputs(), new View());
        new ActionChoice(controller);
        new ActionHandler(controller);
    }
}
