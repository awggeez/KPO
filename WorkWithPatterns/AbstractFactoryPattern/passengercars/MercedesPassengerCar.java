package passengercars;

import static constants.ConsoleCommands.*;

public class MercedesPassengerCar implements PassengerCar {
    @Override
    public void drive() {
        System.out.println(MERCEDES_PASSENGER_CAR_DRIVING);
    }
}
