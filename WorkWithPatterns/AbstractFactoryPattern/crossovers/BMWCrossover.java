package crossovers;

import passengercars.PassengerCar;

import static constants.ConsoleCommands.BMW_CROSSOVER_DRIVING;

public class BMWCrossover implements Crossover {
    @Override
    public void drive() {
        System.out.println(BMW_CROSSOVER_DRIVING);
    }
}
