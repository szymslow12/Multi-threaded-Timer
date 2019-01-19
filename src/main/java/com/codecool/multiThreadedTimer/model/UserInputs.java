package com.codecool.multiThreadedTimer.model;

import java.util.Scanner;

public class UserInputs {

    private String value;
    private Scanner scanner;

    public UserInputs() {
        scanner = new Scanner(System.in);
    }


    public String getValue() {
        return scanner.nextLine();
    }
}
