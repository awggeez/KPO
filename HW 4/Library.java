import java.util.ArrayList;
import java.util.List;

import static constants.ConsoleCommands.UserFeedback.ALL_CONTENT;
import static constants.ConsoleCommands.UserFeedback.EMPTY_LIBRARY;

public class Library {

    private final List<Book> books = new ArrayList<>();
    private final List<Book> takenBooks = new ArrayList<>();
    private final List<Book> suchBooks = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public List<Book> getTakenBooks() {
        return takenBooks;
    }

    public List<Book> getSuchBooks() {
        return suchBooks;
    }

    public void get(String name) {
        for (Book book : books) {
            if (book.getDescription().equals(name)) {
                takenBooks.add(book);
                books.remove(book);
                return;
            }
        }
    }

    public void put(String name) {
        for (Book book : takenBooks) {
            if (book.getDescription().equals(name)) {
                books.add(book);
                takenBooks.remove(book);
                System.out.println("You're putting the book");
                return;
            }
        }
        System.out.println("You didn't take this book");
    }

    public int checkBook(String name) {
        int counter = 0;
        for (Book book : books) {
            if (book.getDescription().equals(name)) {
                counter++;
            }
        }
        return counter;
    }

    public void getDisplayAllSuchBooks(String name) {
        int counter = 1;
        for (Book book : books) {
            if (book.getDescription().equals(name)) {
                suchBooks.add(book);
                System.out.println(counter++ + ". " + book);
            }
        }
    }

    public void displayAllBooks() {
        if (books.size() == 0) {
            System.out.println(EMPTY_LIBRARY);
            return;
        }
        System.out.println(ALL_CONTENT);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public boolean checkTaken(String name) {
        for (Book book : takenBooks) {
            if (book.getDescription().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
