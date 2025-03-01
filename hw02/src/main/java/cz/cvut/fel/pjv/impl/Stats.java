package cz.cvut.fel.pjv.impl;

import cz.cvut.fel.pjv.StatsInterface;

public class Stats implements StatsInterface {
    private long count = 0;
    public long lines = 0;

    double[] buffer = new double[10];

    @Override
    public void addNumber(double number) {
        this.buffer[(int) (this.count % 10)] = number;
        this.count++;
        if (this.count % 10 == 0) {
            System.out.println(getFormattedStatistics());
        }
    }

    @Override
    public double getAverage() {
        double sum = 0;
        if (this.count % 10 == 0) {
            for (int i = 0; i < 10; i++) {
                sum += this.buffer[i];
            }
            return sum / 10;
        } else {
            for (int i = 0; i < (this.count % 10); i++) {
                sum += this.buffer[i];
            }
            return sum / (this.count % 10);
        }
    }

    @Override
    public double getStandardDeviation() {
        double average = this.getAverage();
        double[] deviations = new double[10];
        if (this.count % 10 == 0) {
            for (int i = 0; i < 10; i++) {
                deviations[i] = Math.pow(this.buffer[i] - average, 2);
            }
            double sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += deviations[i];
            }
            return Math.sqrt(sum / 10);
        } else {
            for (int i = 0; i < (this.count % 10); i++) {
                deviations[i] = Math.pow(this.buffer[i] - average, 2);
            }
            double sum = 0;
            for (int i = 0; i < (this.count % 10); i++) {
                sum += deviations[i];
            }
            return Math.sqrt(sum / (this.count % 10));
        }
    }

    @Override
    public int getCount() {
        return (int) (this.count % 10 == 0 ? (this.count > 10 ? 10 : this.count) : this.count % 10);
    }

    @Override
    public String getFormattedStatistics() {
        // if (this.count > 1) {
        return String.format("%2d %.3f %.3f",
                (this.count % 10 == 0 ? (this.count > 10 ? 10 : this.count) : this.count % 10),
                this.getAverage(),
                this.getStandardDeviation());
        // } else {
        // return "";
        // }
    }
}
