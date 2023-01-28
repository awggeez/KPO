package app;

import crossovers.Crossover;
import factories.CarsFactory;
import passengercars.PassengerCar;

public class Application {
    private Crossover crossover;
    private PassengerCar passengerCar;

    public Application(CarsFactory factory) {
        crossover = factory.createCrossover();
        passengerCar = factory.createPassengerCar();
    }

    public void drive() {
        crossover.drive();
        passengerCar.drive();
    }
}
