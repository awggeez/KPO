import java.util.*;

import static constants.FinalConsoleCommands.*;

/**
 * @author Abiev Marik Olegovich
 */
public class Game {
    static final Board board = new Board();
    private static Player player1;
    private static Player player2;
    private static boolean isFirstPlayerSkipped;
    private static boolean isSecondPlayerSkipped;
    private static int maxPoints;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void startGame() {
        playMode();
        if (SESSION_STATUS.equals(SESSION_OFF)) {
            return;
        }
        board.setBeginningState();
        while (!board.isFull()) {
            stepByFirstPlayer();
            board.updateCloneOfDesk();
            if (!isFirstPlayerSkipped) {
                board.getBoard().add(board.getPrevStepBoard());
            } else if (isSecondPlayerSkipped) {
                System.out.println(GAME_OVER);
                break;
            }
            stepBySecondPlayer();
            board.updateCloneOfDesk();
            if (!isSecondPlayerSkipped) {
                board.getBoard().add(board.getPrevStepBoard());
            }
        }

        System.out.println();
        board.displayBoard();
        getInfo();

        if (player1.getScore() > player2.getScore()) {
            if (player1.getName().equals(PERSON_NAME)) {
                maxPoints = player1.getScore();
            } else {
                maxPoints = player2.getScore();
            }
            System.out.printf(PLAYER_1_WINNER, player1.getScore());
        } else if (player1.getScore() < player2.getScore()) {
            if (player2.getName().equals(PERSON_NAME)) {
                maxPoints = player2.getScore();
            } else {
                maxPoints = player1.getScore();
            }
            System.out.printf(PLAYER_2_WINNER, player2.getScore());
        } else {
            maxPoints = player1.getScore();
            System.out.printf(BOTH_WINNERS, player1.getScore());
        }
    }


    /**
     * Responsible for the first player's turn
     */
    private static void stepByFirstPlayer() {
        if (isAnyChoice(PLAYER_1_SYMBOL)) {
            stepByPlayer(PLAYER_1_STEP, PLAYER_1_SYMBOL, player1);
        } else {
            System.out.println(NO_AVAILABLE_PLACE);
            isFirstPlayerSkipped = true;
        }
    }

    /**
     * Responsible for the second player's turn
     */
    private static void stepBySecondPlayer() {
        if (isAnyChoice(PLAYER_2_SYMBOL)) {
            stepByPlayer(PLAYER_2_STEP, PLAYER_2_SYMBOL, player2);
        } else {
            System.out.println(NO_AVAILABLE_PLACE);
            isSecondPlayerSkipped = true;
        }
    }

    /**
     * Performs a human move by calling the <b>step</b> method
     * and a computer move using the <b>professionalComputerStep</b> and <b>smartStep</b> methods
     * for a "weak" and "professional" computer, respectively
     *
     * @param playerStep    the message that is displayed before the player's turn
     * @param playerSymbol  the character the player moves with
     * @param player        player whose turn it is
     */
    private static void stepByPlayer(String playerStep, char playerSymbol, Player player) {
        boolean isComputer = true, isPerson = false;
        Integer[] pair = new Integer[2];
        Map<Integer[], Double> pairs = getPossibleSteps(playerSymbol);
        while (true) {
            if (player.getName().equals(PERSON_NAME)) {
                isPerson = true;
                System.out.println(playerStep);
                showHints(pairs);
                System.out.print(ENTER_COORDINATES);
                pair = step(player, playerSymbol);
            } else {
                if (isComputer) {
                    System.out.println(playerStep);
                    isComputer = false;
                }
                getInfo();
                board.displayBoard();

                if (player.getName().equals(SMART_COMPUTER_NAME)) {
                    pair = professionalComputerStep(playerSymbol);
                } else if (player.getName().equals(STUPID_COMPUTER_NAME)) {
                    pair = weakComputerStep(playerSymbol);
                }
            }
            int x = pair[0];
            int y = pair[1];
            if (Checker.checkValidation(x - 1, y - 1, playerSymbol, player)) {
                Cell cell = new Cell(playerSymbol, false);
                var reversiBoard = board.getReversiBoard();
                reversiBoard[y - 1][x - 1] = cell;
                fillOtherCells(x - 1, y - 1, playerSymbol);
                if (isPerson) {
                    deleteHints();
                }
                if (!isPerson) {
                    System.out.println(ENTER_COORDINATES + x + " " + y + "\n");
                }
                break;
            }
        }
    }

    /**
     * Removing from the board hints to a person of cells that he may go
     */
    private static void deleteHints() {
        Cell cell;
        for (int x = 1; x <= SIZE; x++) {
            for (int y = 1; y <= SIZE; y++) {
                if (board.getReversiBoard()[y - 1][x - 1].getValue() == HELP_SYMBOL) {
                    cell = new Cell(' ', true);
                    board.getReversiBoard()[y - 1][x - 1] = cell;
                }
            }
        }
    }

    /**
     * Conclusion on the board of hints to a person of cells that he may go
     *
     * @param pairs map with coordinates of the cell and the value which you can get if you go there
     */
    private static void showHints(Map<Integer[], Double> pairs) {
        for (Map.Entry<Integer[], Double> entry : pairs.entrySet()) {
            int x = entry.getKey()[0];
            int y = entry.getKey()[1];
            Cell cell = new Cell(HELP_SYMBOL, true);
            board.getReversiBoard()[y - 1][x - 1] = cell;
        }
        printAllPossibleSteps(pairs);
        getInfo();
        board.displayBoard();
    }

    /**
     * Printing all possible moves on the board
     *
     * @param pairs map with coordinates of the cell and the value which you can get if you go there
     */
    private static void printAllPossibleSteps(Map<Integer[], Double> pairs) {
        System.out.print(OUTPUT_POSSIBLE_COORDINATES);
        boolean isFirstIteration = true;
        for (Map.Entry<Integer[], Double> entry : pairs.entrySet()) {
            int x = entry.getKey()[0];
            int y = entry.getKey()[1];
            if (isFirstIteration) {
                System.out.printf(COORDINATES_FORMAT, x, y);
                isFirstIteration = false;
            } else {
                System.out.printf(", " + COORDINATES_FORMAT, x, y);
            }
        }
        System.out.println();
    }

    /**
     * Calculation of the most profitable move, based on the points you can get
     *
     * @param map map with coordinates of the cell and the value which you can get if you go there
     * @return    coordinates of the most advantageous cell
     */
    private static Integer[] calculateFormula(Map<Integer[], Double> map) {
        double mostSignificant = 0;
        Integer[] result = new Integer[2];
        for (Map.Entry<Integer[], Double> entry : map.entrySet()) {
            if (entry.getValue() > mostSignificant) {
                mostSignificant = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }

    /**
     * Step by "weak" computer by calling the method <b>calculateFormula</b>
     *
     * @param symbol the character the player moves with
     * @return       coordinates of the most advantageous cell
     */
    private static Integer[] weakComputerStep(char symbol) {
        return calculateFormula(mapOfSteps(symbol));
    }

    /**
     * Returns all possible moves
     *
     * @param symbol the character the player moves with
     * @return       map of the all possible cells to move with the values that can be obtained
     */
    private static Map<Integer[], Double> mapOfSteps(char symbol) {
        double countZam;
        Map<Integer[], Double> pairs = new HashMap<>();
        for (int y = 1; y <= SIZE; y++) {
            for (int x = 1; x <= SIZE; x++) {
                if (Checker.checkValidation(x - 1, y - 1, symbol, new Player(SMART_COMPUTER_NAME))) {
                    countZam = Checker.checkPositionComputer(x - 1, y - 1, symbol);
                    pairs.put(new Integer[]{x, y}, countZam);
                }
            }
        }
        return pairs;
    }

    /**
     * Responsible for the course of a "smart" computer
     *
     * @param symbol the character the player moves with
     * @return       coordinates of the most advantageous cell
     */
    private static Integer[] professionalComputerStep(char symbol) {
        var pairs = getPossibleSteps(symbol);
        double maxResult = Integer.MIN_VALUE;
        Integer[] coordinatesForMaxResult = new Integer[2];
        char newSymbol = (symbol == PLAYER_1_SYMBOL) ? PLAYER_2_SYMBOL : PLAYER_1_SYMBOL;

        for (Map.Entry<Integer[], Double> entry : pairs.entrySet()) {
            board.setSavedBoard();
            int x = entry.getKey()[0];
            int y = entry.getKey()[1];
            Cell cell = new Cell(symbol, false);
            var reversiBoard = board.getReversiBoard();
            reversiBoard[y - 1][x - 1] = cell;

            fillOtherCells(x - 1, y - 1, symbol);
            var superSmartStep = superSmartStep(newSymbol);

            for (Map.Entry<Integer[], Double> sup : superSmartStep.entrySet()) {
                if (entry.getValue() - sup.getValue() > maxResult) {
                    maxResult = entry.getValue() - sup.getValue();
                    coordinatesForMaxResult = entry.getKey();
                }
            }
            superSmartStep.clear();
            board.copyToReversi();
        }
        return coordinatesForMaxResult;
    }

    /**
     * Returns all possible moves
     *
     * @param symbol the character the player moves with
     * @return       map of the all possible cells to move with the values that can be obtained
     */
    private static Map<Integer[], Double> getPossibleSteps(char symbol) {
        return mapOfSteps(symbol);
    }

    /**
     * Determines the most advantageous cell for a move
     *
     * @param newSymbol the character the player doesn't move with
     * @return          the coordinates of the cell with the highest value
     */
    private static Map<Integer[], Double> superSmartStep(char newSymbol) {
        Map<Integer[], Double> possibleSteps = getPossibleSteps(newSymbol);
        return calculateSuperFormula(possibleSteps);
    }

    /**
     * Calculates the most advantageous cell for a move using a special formula
     *
     * @param map map of the all possible cells to move with the values that can be obtained
     * @return    the coordinates of the cell with the highest value
     */
    private static Map<Integer[], Double> calculateSuperFormula(Map<Integer[], Double> map) {
        double mostSignificant = Double.MIN_VALUE;
        Integer[] coordinates = new Integer[2];
        Map<Integer[], Double> result = new HashMap<>();
        for (Map.Entry<Integer[], Double> entry : map.entrySet()) {
            if (entry.getValue() > mostSignificant) {
                mostSignificant = entry.getValue();
                coordinates = entry.getKey();
            }
        }
        result.put(coordinates, mostSignificant);
        return result;
    }

    /**
     * Responsible for the course of a person
     *
     * @param player player whose turn it is
     * @param symbol the character the player moves with
     * @return       coordinates of the most advantageous cell
     */
    private static Integer[] step(Player player, char symbol) {
        int x, y;
        String line = "";
        Integer[] pair = new Integer[2];
        while (true) {
            try {
                line = SCANNER.nextLine();
                var split = line.split(" ");
                x = Integer.parseInt(split[0]);
                y = Integer.parseInt(split[1]);
            } catch (Exception e) {
                if (line.equals("back")) {
                    stepBack();
                    Map<Integer[], Double> pairs = getPossibleSteps(symbol);
                    showHints(pairs);
                    if (player.getName().equals(PERSON_NAME) && symbol == PLAYER_1_SYMBOL) {
                        System.out.println(PLAYER_1_STEP);
                    } else {
                        System.out.println(PLAYER_2_STEP);
                    }
                } else {
                    System.out.println(INCORRECT_INPUT);
                }
                continue;
            }
            break;
        }
        pair[0] = x;
        pair[1] = y;
        return pair;
    }

    /**
     * Fills in the required cells with a <b>symbol</b>
     *
     * @param x      coordinate x
     * @param y      coordinate y
     * @param symbol the character the player moves with
     */
    private static void fillOtherCells(int x, int y, char symbol) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                Checker.checkTheCurrentRow(x, dx, y, dy, symbol);
            }
        }
    }

    /**
     * Checks the possibility of a move
     *
     * @param symbol the character the player moves with
     * @return       is possible step or not
     */
    private static boolean isAnyChoice(char symbol) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (Checker.checkPossibility(i, j, symbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Game mode selection
     */
    private static void playMode() {
        int modeChoice;
        boolean correctAnswer = false;
        while (!correctAnswer) {
            System.out.print(PLAY_MODE + " ");
            try {
                modeChoice = SCANNER.nextInt();
                if (modeChoice == 1) {
                    player1 = new Player(PERSON_NAME);
                    player2 = new Player(PERSON_NAME);
                    correctAnswer = true;
                    SESSION_STATUS = SESSION_ON;
                } else if (modeChoice == 2) {
                    player1 = new Player(PERSON_NAME);
                    player2 = new Player(STUPID_COMPUTER_NAME);
                    correctAnswer = true;
                    SESSION_STATUS = SESSION_ON;
                } else if (modeChoice == 3) {
                    player1 = new Player(PERSON_NAME);
                    player2 = new Player(SMART_COMPUTER_NAME);
                    correctAnswer = true;
                    SESSION_STATUS = SESSION_ON;
                } else if (modeChoice == 4) {
                    player1 = new Player(STUPID_COMPUTER_NAME);
                    player2 = new Player(PERSON_NAME);
                    correctAnswer = true;
                    SESSION_STATUS = SESSION_ON;
                } else if (modeChoice == 5) {
                    player1 = new Player(SMART_COMPUTER_NAME);
                    player2 = new Player(PERSON_NAME);
                    correctAnswer = true;
                    SESSION_STATUS = SESSION_ON;
                } else if (modeChoice == 0) {
                    SESSION_STATUS = SESSION_OFF;
                    return;
                }
            } catch (RuntimeException exception) {
                System.out.println("Not a number!\n");
                SCANNER.nextLine();
            }
        }
        SCANNER.nextLine();
    }

    /**
     * Withdrawal of player points
     */
    public static void getInfo() {
        player1.setScore(board.getCountOfCurrentSymbol('X'));
        player2.setScore(board.getCountOfCurrentSymbol('0'));
        System.out.println("Player 1: score - " + player1.getScore());
        System.out.println("Player 2: score - " + player2.getScore());
    }

    /**
     * Return max points of a person
     *
     * @return max points of a person
     */
    public static int getMaxPoints() {
        return maxPoints;
    }

    /**
     * Allows the player to repeat the move
     */
    public static void stepBack() {
        var cells = board.getBoard();
        if (cells.size() < 2) {
            System.out.println(NO_STEP_BACK);
            return;
        } else if (cells.size() == 2) {
            board.setBeginningState();
            board.setReversiBoard(board.getReversiBoard());
        } else {
            backBoard(cells);
        }
        cells.remove(cells.size() - 1);
        cells.remove(cells.size() - 1);
    }

    /**
     *  Returns the previous state of the board for the player
     *
     * @param cells list of all board states
     */
    private static void backBoard(List<Cell[][]> cells) {
        Cell[][] tmp_desk = cells.get(cells.size() - 3);
        Cell[][] now_desk = board.getReversiBoard();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                now_desk[y][x].setValue(tmp_desk[y][x].getValue());
                now_desk[y][x].setEmpty(tmp_desk[y][x].isEmpty());
            }
        }
    }
}