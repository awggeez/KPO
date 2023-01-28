package passengercars;

import static constants.ConsoleCommands.*;

public class BMWPassengerCar implements PassengerCar {
    @Override
    public void drive() {
        System.out.println(BMW_PASSENGER_CAR_DRIVING);
    }
}
