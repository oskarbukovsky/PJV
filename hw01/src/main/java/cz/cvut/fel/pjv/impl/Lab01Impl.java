package cz.cvut.fel.pjv.impl;

// import java.util.Locale;
import java.util.Scanner;

import cz.cvut.fel.pjv.Lab01;

public class Lab01Impl implements Lab01 {
    public void addition(Scanner scanner) {
        System.out.println("Zadej scitanec: ");
        double first = scanner.nextDouble();
        System.out.println("Zadej scitanec: ");
        double second = scanner.nextDouble();

        System.out.println("Zadej pocet desetinnych mist: ");
        int precision = scanner.nextInt();
        if (precision < 0) {
            System.out.println("Chyba - musi byt zadane kladne cislo!");
            return;
        }

        System.out.printf("%." + precision + "f + %." + precision + "f = %." + precision + "f", first, second,
                first + second);
    }

    public void subtraction(Scanner scanner) {
        System.out.println("Zadej mensenec: ");
        double first = scanner.nextDouble();
        System.out.println("Zadej mensitel: ");
        double second = scanner.nextDouble();

        System.out.println("Zadej pocet desetinnych mist: ");
        int precision = scanner.nextInt();
        if (precision < 0) {
            System.out.println("Chyba - musi byt zadane kladne cislo!");
            return;
        }

        System.out.printf("%." + precision + "f - %." + precision + "f = %." + precision + "f", first, second,
                first - second);
    }

    public void multiplication(Scanner scanner) {
        System.out.println("Zadej cinitel: ");
        double first = scanner.nextDouble();
        System.out.println("Zadej cinitel: ");
        double second = scanner.nextDouble();

        System.out.println("Zadej pocet desetinnych mist: ");
        int precision = scanner.nextInt();
        if (precision < 0) {
            System.out.println("Chyba - musi byt zadane kladne cislo!");
            return;
        }

        System.out.printf("%." + precision + "f * %." + precision + "f = %." + precision + "f", first, second,
                first * second);
    }

    public void division(Scanner scanner) {
        System.out.println("Zadej delenec: ");
        double first = scanner.nextDouble();
        System.out.println("Zadej delitel: ");
        double second = scanner.nextDouble();
        if (second == 0) {
            System.out.println("Pokus o deleni nulou!");
            return;
        }

        System.out.println("Zadej pocet desetinnych mist: ");
        int precision = scanner.nextInt();
        if (precision < 0) {
            System.out.println("Chyba - musi byt zadane kladne cislo!");
            return;
        }

        System.out.printf("%." + precision + "f / %." + precision + "f = %." + precision + "f", first, second,
                first / second);
    }

    @Override
    public void homework() {
        Scanner scanner = new Scanner(System.in);
        // scanner.useLocale(Locale.US);

        System.out.println("Vyber operaci (1-soucet, 2-rozdil, 3-soucin, 4-podil):");
        switch (scanner.nextInt()) {
            case 1:
                addition(scanner);
                break;
            case 2:
                subtraction(scanner);
                break;
            case 3:
                multiplication(scanner);
                break;
            case 4:
                division(scanner);
                break;
            default:
                System.out.println("Chybna volba!");
        }
        scanner.close();
    }
}
