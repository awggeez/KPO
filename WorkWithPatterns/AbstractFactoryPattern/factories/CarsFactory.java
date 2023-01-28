package factories;

import crossovers.Crossover;
import passengercars.PassengerCar;

public interface CarsFactory {
    PassengerCar createPassengerCar();
    Crossover createCrossover();
}
