import java.util.ArrayList;
import java.util.List;

import static constants.FinalConsoleCommands.*;

public class Board {

    private Cell[][] reversiBoard = new Cell[SIZE][SIZE];

    private static final List<Cell[][]> BOARD = new ArrayList<>();

    private Cell[][] prevStepBoard;

    private Cell[][] savedBoard;
    private static final String UP_PATH = "  1    2    3    4    5    6    7    8  ";
    private static final String OFFSET = "| %c |";

    {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == 3 && j == 3) {
                    reversiBoard[i][j] = new Cell('0', false);
                } else if (i == 4 && j == 3) {
                    reversiBoard[i][j] = new Cell('X', false);
                } else if (i == 3 && j == 4) {
                    reversiBoard[i][j] = new Cell('X', false);
                } else if (i == 4 && j == 4) {
                    reversiBoard[i][j] = new Cell('0', false);
                } else {
                    reversiBoard[i][j] = new Cell(' ', true);
                }
            }
        }
    }

    public void displayBoard() {
        System.out.print("  ");
        System.out.println(UP_PATH);
        for (int x = 0; x < SIZE; x++) {
            System.out.print((x + 1) + " ");
            for (int y = 0; y < SIZE; y++) {
                System.out.printf(OFFSET, reversiBoard[x][y].getValue());
            }
            System.out.println();
        }
    }

    public Cell[][] getReversiBoard() {
        return reversiBoard;
    }

    public void setReversiBoard(Cell[][] reversiBoard) {
        this.reversiBoard = reversiBoard;
    }

    public List<Cell[][]> getBoard() {
        return BOARD;
    }

    public boolean isFull() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (reversiBoard[y][x].getValue() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public int getCountOfCurrentSymbol(char symbol) {
        int result = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (reversiBoard[i][j].getValue() == symbol) {
                    result++;
                }
            }
        }
        return result;
    }

    public Cell[][] getPrevStepBoard() {
        return prevStepBoard;
    }

    public void updateCloneOfDesk() {
        reversiToPrev();
    }

    public void setSavedBoard() {
        reversiToSaved();
    }

    private void reversiToSaved() {
        savedBoard = new Cell[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                savedBoard[y][x] = new Cell(reversiBoard[y][x].getValue(), reversiBoard[y][x].isEmpty());
            }
        }
    }

    private void reversiToPrev() {
        prevStepBoard = new Cell[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                prevStepBoard[y][x] = new Cell(reversiBoard[y][x].getValue(), reversiBoard[y][x].isEmpty());
            }
        }
    }

    public void copyToReversi() {
        reversiBoard = new Cell[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                reversiBoard[y][x] = new Cell(savedBoard[y][x].getValue(), savedBoard[y][x].isEmpty());
            }
        }
    }

    public void setBeginningState() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == 3 && j == 3) {
                    reversiBoard[i][j] = new Cell('0', false);
                } else if (i == 4 && j == 3) {
                    reversiBoard[i][j] = new Cell('X', false);
                } else if (i == 3 && j == 4) {
                    reversiBoard[i][j] = new Cell('X', false);
                } else if (i == 4 && j == 4) {
                    reversiBoard[i][j] = new Cell('0', false);
                } else {
                    reversiBoard[i][j] = new Cell(' ', true);
                }
            }
        }
    }
}