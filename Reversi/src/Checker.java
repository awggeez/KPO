import static constants.FinalConsoleCommands.*;

public class Checker {
    static boolean checkValidation(int x, int y, char symbol, Player player) {
        if (cellInBoard(x, y) && Game.board.getReversiBoard()[y][x].isEmpty()) {
            if (checkPosition(x, y, symbol)) {
                return true;
            } else if (player.getName().equals(PERSON_NAME)) {
                System.out.println(INCORRECT_PLACE);
            }
            return false;
        } else {
            if (!cellInBoard(x, y)) {
                System.out.println(INCORRECT_PLACE);
            } else if (player.getName().equals(PERSON_NAME)) {
                System.out.println(TAKEN_PLACE);
            }
        }
        return false;
    }
    static boolean checkPosition(int x, int y, char symbol) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (cellInBoard(x + dx, y + dy)) {
                    if (!Game.board.getReversiBoard()[y + dy][x + dx].isEmpty()) {
                        if (checkTheCurrentRowForInsert(x, dx, y, dy, symbol)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    static double checkPositionComputer(int x, int y, char symbol) {
        int maxCount = 0;
        double valueCurrentCell = 0;
        if ((x == 0 && y == 0) || (x == 0 && y == SIZE - 1) ||
            (x == SIZE - 1 && y == 0) || (x == SIZE - 1 && y == SIZE - 1)) {
            valueCurrentCell = 0.8;
        } else if (x == SIZE - 1 || x == 0 || y == SIZE - 1 || y == 0) {
            valueCurrentCell = 0.4;
        }

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (cellInBoard(x + dx, y + dy)) {
                    if (!Game.board.getReversiBoard()[y + dy][x + dx].isEmpty()) {
                        maxCount += checkTheCurrentRowForInsertComputer(x, dx, y, dy, symbol);
                    }
                }
            }
        }
        return maxCount + valueCurrentCell;
    }

    static void checkTheCurrentRow(int x, int dx, int y, int dy, char symbol) {
        boolean isAnotherInTheRow = false;
        Cell currentCell = Game.board.getReversiBoard()[y][x];
        while (cellInBoard(x + dx, y + dy) && !currentCell.isEmpty()) {
            x += dx;
            y += dy;
            currentCell = Game.board.getReversiBoard()[y][x];
            if (cellInBoard(x, y)) {
                if (currentCell.getValue() != symbol && !currentCell.isEmpty()) {
                    isAnotherInTheRow = true;
                }
                if (currentCell.getValue() == symbol && !currentCell.isEmpty()) {
                    if (isAnotherInTheRow) {
                        x -= dx;
                        y -= dy;
                        currentCell = Game.board.getReversiBoard()[y][x];
                        while (currentCell.getValue() != symbol && cellInBoard(x, y)) {
                            currentCell.setValue(symbol);
                            x -= dx;
                            y -= dy;
                            currentCell = Game.board.getReversiBoard()[y][x];
                        }
                    }
                    break;
                }
            }
        }
    }

    static boolean checkTheCurrentRowForInsert(int x, int dx, int y, int dy, char symbol) {
        boolean isAnotherInTheRow = false;
        Cell currentCell = Game.board.getReversiBoard()[y + dy][x + dx];
        while (cellInBoard(x + dx, y + dy) && !currentCell.isEmpty()) {
            x += dx;
            y += dy;
            currentCell = Game.board.getReversiBoard()[y][x];
            if (cellInBoard(x, y)) {
                if (currentCell.getValue() != symbol && !currentCell.isEmpty()) {
                    isAnotherInTheRow = true;
                }
                if (currentCell.getValue() == symbol && !currentCell.isEmpty()) {
                    return isAnotherInTheRow;
                }
            }
        }
        return false;
    }

    static int checkTheCurrentRowForInsertComputer(int x, int dx, int y, int dy, char symbol) {
        int countCells = 0;
        boolean isAnotherSymbol = false;
        Cell currentCell = Game.board.getReversiBoard()[y + dy][x + dx];
        while (cellInBoard(x + dx, y + dy) && !currentCell.isEmpty()) {
            x += dx;
            y += dy;
            currentCell = Game.board.getReversiBoard()[y][x];
            if (cellInBoard(x, y)) {
                if (currentCell.getValue() != symbol && !currentCell.isEmpty()) {
                    isAnotherSymbol = true;
                    if (x == 0 || x == SIZE - 1 || y == 0 || y == SIZE - 1) {
                        countCells += 2;
                    } else {
                        countCells += 1;
                    }
                } else if (currentCell.getValue() == symbol && isAnotherSymbol) {
                    return countCells;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    static boolean checkPossibility(int x, int y, char symbol) {
        if (cellInBoard(x, y) && Game.board.getReversiBoard()[y][x].isEmpty()) {
            return checkPosition(x, y, symbol);
        }
        return false;
    }

    static boolean cellInBoard(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
