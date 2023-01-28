package crossovers;

import static constants.ConsoleCommands.MERCEDES_CROSSOVER_DRIVING;

public class MercedesCrossover implements Crossover {
    @Override
    public void drive() {
        System.out.println(MERCEDES_CROSSOVER_DRIVING);
    }
}
