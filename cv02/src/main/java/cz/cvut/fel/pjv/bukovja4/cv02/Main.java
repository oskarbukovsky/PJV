package cz.cvut.fel.pjv.bukovja4.cv02;

import cz.cvut.fel.pjv.cars.Race;
import cz.cvut.fel.pjv.cars.model.Car;
import cz.cvut.fel.pjv.cars.model.ServiceBook;

public class Main {
    public static void main(String[] args) {
        Car car11 = new Car("Volkswagen", "Polo", 2010);
        System.out.println(car11);
        System.out.println("Počet aut: " + Car.getNumberOfExistingCars());
        Car car12 = new Car("Chevrolet", "Corvette", 1980);
        System.out.println(car12);
        System.out.println("Počet aut: " + Car.getNumberOfExistingCars());

        Car car21 = new Car("Volkswagen", "Polo", 2010, "AKK");
        ServiceBook serviceBook1 = new ServiceBook(car21);
        serviceBook1.addRecord("První servisní prohlídka.");
        System.out.println(car21);
        System.out.println("Počet aut: " + Car.getNumberOfExistingCars());
        System.out.printf("Servisní záznamy %s %s:\n%s\n", car21.getManufacturer(),
                car21.getModelName(),
                car21.getServiceBook());
        System.out.println("\n");
        Car car22 = new Car("Chevrolet", "Corvette", 1980, "LS7");
        ServiceBook serviceBook2 = new ServiceBook(car22);
        serviceBook2.addRecord("První servisní prohlídka.");
        serviceBook2.addRecord("Porucha motoru.");
        System.out.println(car22);
        System.out.println("Počet aut: " + Car.getNumberOfExistingCars());
        System.out.printf("Servisní záznamy %s %s:\n%s\n", car22.getManufacturer(),
                car22.getModelName(),
                car22.getServiceBook());

        Car car1 = new Car("Volkswagen", "Polo", 2010, "AKK", 40);
        Car car2 = new Car("Chevrolet", "Corvette", 1980, "LS7", 45);

        Car car3 = new Car("Trabant", "P601", 1990, "Air cooled, 0.6-liter 2-stroke", 20);
        Car car4 = new Car("BMW", "3", 2006, "318d", 42);
        Car car5 = new Car("McLaren", "F1", 2014, "V12", 107);
        Race race = new Race(1000);
        race.addRacingCar(car1);
        race.addRacingCar(car2);
        race.addRacingCar(car3);
        race.addRacingCar(car4);
        race.addRacingCar(car5);
        System.out.println("Závodníci:");
        System.out.println(race);
        System.out.printf("Výhercem se stává %s s časem %s.\n", race.getWinner(), race.getWinningTime());
        System.out.printf("Pořadí v cíli:\n%s\n", race);
    }
}