public class Book {

    private final Author author;
    private final String description;
    private final int yearOfCreation;

    public Book(Author author, String description, int yearOfCreation) {
        this.description = description;
        this.author = author;
        this.yearOfCreation = yearOfCreation;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Book: " +
               "Author = " + author +
               ", description = '" + description + '\'' +
               ", yearOfCreation = " + yearOfCreation;
    }
}
