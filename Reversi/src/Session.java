import static constants.FinalConsoleCommands.*;

public class Session {
    private static int maxValue = 0;

    public static void main(String[] args) {
        while (SESSION_STATUS.equals(SESSION_ON)) {
            Game.startGame();
            int currentPoints = Game.getMaxPoints();
            if (currentPoints > maxValue) {
                maxValue = currentPoints;
            }
        }
        System.out.println(MAX_RESULT_IN_SESSION + " - " + maxValue);
    }
}
