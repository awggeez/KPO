package factories;

import crossovers.Crossover;
import crossovers.MercedesCrossover;
import passengercars.MercedesPassengerCar;
import passengercars.PassengerCar;

public class MercedesFactory implements CarsFactory {
    @Override
    public PassengerCar createPassengerCar() {
        return new MercedesPassengerCar();
    }

    @Override
    public Crossover createCrossover() {
        return new MercedesCrossover();
    }
}
