package cz.cvut.fel.pjv.impl;

import cz.cvut.fel.pjv.TextIO;

public class Lab02 {
    public void main(String[] args) {
        Stats stats = new Stats();

        TextIO textIO = new TextIO();
        while (true) {
            String number = textIO.getLine();
            if (number.equals("")) {
                System.err.println("End of input detected!");
                if (stats.getCount() % 10 != 0) {
                    System.out.println(stats.getFormattedStatistics());
                }
                break;
            }
            if (TextIO.isDouble(number)) {
                stats.addNumber(Double.parseDouble(number));
            } else {
                System.err.println("A number has not been parsed from line " + (stats.lines + 1));
            }
            stats.lines++;
        }
    }
}