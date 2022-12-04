package constants;

public final class FinalConsoleCommands {

    public static final String PLAY_MODE = """
            Select game mode:
            1 - human against human
            2 - human against weak computer (your first move)
            3 - human against smart computer (your first move)
            4 - weak computer against human (first move at the computer)
            5 - smart computer against human (first move at the computer)
            0 - end session
            >\040""";

    public static final String INCORRECT_PLACE = "Incorrect place. Please, repeat\n";
    public static final String INCORRECT_INPUT = "Incorrect input. Please, repeat\n";
    public static final String TAKEN_PLACE = "This cell is taken. Please, repeat\n";
    public static final String NO_AVAILABLE_PLACE = "Skip a turn. No further possible move\n";
    public static final String GAME_OVER = "Game over!\n";
    public static final String SESSION_ON = "ON";
    public static final String SESSION_OFF = "OFF";
    public static String SESSION_STATUS = SESSION_ON;
    public static final String MAX_RESULT_IN_SESSION = "For this session, the maximum number of points scored by a person";
    public static final String NO_STEP_BACK = "There are no moves back!\n";

    public static final String PLAYER_1_STEP = "\nPlayer 1, your turn(enter 2 space-separated x and y coordinates on the same line, respectively, or move back by entering \"back\")";
    public static final String PLAYER_2_STEP = "\nPlayer 2, your turn(enter 2 space-separated x and y coordinates on the same line, respectively, or move back by entering \"back\")";

    public static final String ENTER_COORDINATES = "Your coordinates(or option \"back\"): ";
    public static final String OUTPUT_POSSIBLE_COORDINATES = "You can go to: ";
    public static final String COORDINATES_FORMAT = "(%d, %d)";

    public static final char PLAYER_1_SYMBOL = 'X';
    public static final char PLAYER_2_SYMBOL = '0';
    public static final String PLAYER_1_WINNER = "Player 1 won: %d scores\n";
    public static final String PLAYER_2_WINNER = "Player 2 won: %d scores\n";
    public static final String BOTH_WINNERS = "Draw. Both players have %d scores\n";

    public static final char HELP_SYMBOL = '⁕';

    public static final String PERSON_NAME = "person";
    public static final String STUPID_COMPUTER_NAME = "stupidComputer";
    public static final String SMART_COMPUTER_NAME = "smartComputer";

    public static final int SIZE = 8;

    private FinalConsoleCommands() {}
}
