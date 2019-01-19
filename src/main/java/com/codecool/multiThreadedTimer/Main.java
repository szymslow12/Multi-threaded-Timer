package com.codecool.multiThreadedTimer;

import com.codecool.multiThreadedTimer.controller.AppController;
import com.codecool.multiThreadedTimer.model.ActionChoice;
import com.codecool.multiThreadedTimer.model.ActionHandler;
import com.codecool.multiThreadedTimer.model.UserInputs;

public class Main {

    public static void main(String[] args) {
        AppController controller = new AppController(new UserInputs());
        new ActionChoice(controller);
        new ActionHandler(controller);
    }
}
