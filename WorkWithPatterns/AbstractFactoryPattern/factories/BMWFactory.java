package factories;

import crossovers.BMWCrossover;
import crossovers.Crossover;
import passengercars.BMWPassengerCar;
import passengercars.PassengerCar;

public class BMWFactory implements CarsFactory {
    @Override
    public PassengerCar createPassengerCar() {
        return new BMWPassengerCar();
    }

    @Override
    public Crossover createCrossover() {
        return new BMWCrossover();
    }
}
