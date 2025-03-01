package com.example;

import java.util.Scanner;

public class Main {

    public static double average(int[] numbers) {
        int sum = 0;
        int count = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != 0) {
                sum += numbers[i];
                count++;
            }
        }

        return sum / (double)count;
    }

    public static int secondLargest(int[] numbers) {
        int largest = numbers[0];
        int secondLargest = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > largest) {
                secondLargest = largest;
                largest = numbers[i];
            } else if (numbers[i] > secondLargest) {
                secondLargest = numbers[i];
            }
        }

        return secondLargest;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zadávej čísla (0 pro ukončení):");

        int[] numbers = new int[100];
        int number = scanner.nextInt();

        for (int i = 0; number != 0; i++) {
            numbers[i] = number;
            number = scanner.nextInt();
        }

        System.out.println("Průměr čísel je: " + average(numbers));
        System.out.println("Druhé největší číslo je: " + secondLargest(numbers));
        scanner.close();
    }
}