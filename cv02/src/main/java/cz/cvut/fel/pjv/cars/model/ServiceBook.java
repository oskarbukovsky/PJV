package cz.cvut.fel.pjv.cars.model;

import java.util.ArrayList;

public class ServiceBook {

    private Car car;
    private ArrayList<String> records;

    public ServiceBook(Car car) {
        this.car = car;
        car.serviceBook = this;
        this.records = new ArrayList<String>();
    }

    public void addRecord(String record) {
        this.records.add(record);
    }

    @Override
    public String toString() {
        return String.join("\n", this.records);
    }

    public Car getCar() {
        return this.car;
    }
}
