import java.util.Scanner;

import static constants.ConsoleCommands.UserFeedback.*;
import static constants.ConsoleCommands.Commands.*;
import static constants.ConsoleCommands.Symbols.*;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Library LIBRARY = new Library();

    public static void main(String[] args) {
        Generation generation = new Generation();
        generation.generateBooks();
        LIBRARY.getBooks().addAll(generation.getFINAL_LIST());

        String command = null;
        while (!EXIT.equals(command)) {
            System.out.print(INPUT_COMMAND);
            command = SCANNER.next();

            String name;

            switch (command) {
                case GET_THE_BOOK -> {
                    name = refactoringName();
                    getBook(name);
                }
                case PUT_THE_BOOK -> {
                    name = refactoringName();
                    LIBRARY.put(name);
                }
                case ALL_BOOKS -> LIBRARY.displayAllBooks();
                case LIST_OF_BOOKS -> {
                    var takenBooks = LIBRARY.getTakenBooks();
                    if (takenBooks.size() == 0) {
                        System.out.println(EMPTY_LIST);
                        break;
                    }
                    for (Book book : takenBooks) {
                        System.out.println(book);
                    }
                }
                case EXIT -> System.out.println(GOODBYE);
                default -> System.out.println(WRONG_COMMAND);
            }
        }
    }

    private static String refactoringName() {
        String name;
        name = SCANNER.nextLine();
        char first = name.charAt(1);
        name = name.replace(SPACE + first, EMPTY + first);
        return name;
    }

    private static void getBook(String name) {
        if (LIBRARY.checkTaken(name)) {
            System.out.println("NO. Book is taken!");
        }
        var count = LIBRARY.checkBook(name);
        if (count == 0) {
            System.out.println("There is no such book :( ");
        } else if (count > 1) {
            LIBRARY.getDisplayAllSuchBooks(name);
            System.out.print("Enter the number: ");
            var book = LIBRARY.getSuchBooks().get(SCANNER.nextInt() - 1);
            LIBRARY.getBooks().remove(book);
            LIBRARY.getTakenBooks().add(book);
            System.out.println(book + " is taken");
        } else {
            LIBRARY.get(name);
            System.out.println("OK. Book is taken!");
        }
    }
}
