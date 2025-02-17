package com.example;

import java.util.Scanner;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        String test = "Hello world11!";
        out.println(test);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int number = scanner.nextInt();
        System.out.println("You entered: " + number);
        scanner.close();
    }
}