package cz.cvut.fel.pjv.cars;

import java.util.ArrayList;

import cz.cvut.fel.pjv.cars.model.Car;

public class Race {
    private int length;
    private boolean finished;
    private ArrayList<Car> racers;

    public Race(int length) {
        this.length = length;
        this.racers = new ArrayList<Car>();
        this.finished = false;
    }

    public void addRacingCar(Car car) {
        this.racers.add(car);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.finished) {
            this.racers.forEach(car -> sb.append(car.toString()).append("\n"));
        } else {
            this.racers.forEach(car -> sb.append(car.toString() + ", time: ").append(getTime(car)).append("\n"));
        }
        return sb.toString();
    }

    private String getTime(Car car) {
        int totalTime = this.length / car.getSpeed();
        int seconds = totalTime % 60;
        int minutes = (totalTime / 60);
        int hours = totalTime / 3600;
        return hours + " hours " + minutes + " minutes " + seconds + " seconds";
    }

    public String getWinner() {
        this.finished = true;
        this.racers.sort((car1, car2) -> car2.getSpeed() - car1.getSpeed());
        return this.racers.get(0).toString();
    }

    public String getWinningTime() {
        return getTime(this.racers.get(0));
    }
}
