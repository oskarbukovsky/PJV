package cz.cvut.fel.pjv.cars.model;

import java.util.UUID;

public class Car {
    private String manufacturer;
    private String modelName;
    private int year;
    private UUID vinCode;
    private String engine;
    private int speed;
    protected ServiceBook serviceBook;
    private static int numberOfExistingCars = 0;

    public Car(String manufacturer, String modelName, int year) {
        this(manufacturer, modelName, year, "undefined");
    }

    public Car(String manufacturer, String modelName, int year, String engine) {
        this(manufacturer, modelName, year, engine, 0);
    }

    public Car(String manufacturer, String modelName, int year, String engine, int speed) {
        Car.numberOfExistingCars++;
        this.manufacturer = manufacturer;
        this.modelName = modelName;
        this.year = year;
        this.vinCode = UUID.randomUUID();
        this.engine = engine;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return this.manufacturer + " " + this.modelName + " year " + this.year + " VIN: " + this.vinCode;
    }

    public static int getNumberOfExistingCars() {
        return Car.numberOfExistingCars;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getModelName() {
        return this.modelName;
    }

    public int getYear() {
        return this.year;
    }

    public ServiceBook getServiceBook() {
        return this.serviceBook;
    }

    public String getEngine() {
        return this.engine;
    }

    public int getSpeed() {
        return this.speed;
    }
}
