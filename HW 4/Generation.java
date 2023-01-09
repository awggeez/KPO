import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {
    private static int countBooks = 10;
    private static final int FINAL_COUNT_BOOKS = 3;

    private static final List<Book> BOOKS = new ArrayList<>() {{
        add(new Book(new Author("Дэвид", "Томас"), "Программист-прагматик", 1999));
        add(new Book(new Author("Стив", "Макконнелл"), "Совершенный код", 1993));
        add(new Book(new Author("Роберт", "Мартин"), "Чистый код", 2008));
        add(new Book(new Author("Эрик", "Фримен"), "Head First. Паттерны проектирования", 2018));
        add(new Book(new Author("Дональд", "Кнут"), "Искусство программирования", 1968));
        add(new Book(new Author("Томас", "Кормен"), "Алгоритмы. Построение и анализ", 1990));
        add(new Book(new Author("Мартин", "Фаулер"), "Рефакторинг", 2003));
        add(new Book(new Author("Роберт", "Мартин"), "Идеальный программист", 2011));
        add(new Book(new Author("Антон", "Спрол"), "Думай как программист", 2022));
        add(new Book(new Author("Майкл", "Физерс"), "Эффективная работа с унаследованным кодом", 2004));
    }};

    private final List<Book> FINAL_LIST = new ArrayList<>();

    public void generateBooks() {
        for (int i = 0; i < FINAL_COUNT_BOOKS; i++) {
            var index = new Random().nextInt(countBooks);
            var book = BOOKS.get(index);
            BOOKS.remove(index);
            countBooks--;
            FINAL_LIST.add(book);
        }
    }

    public List<Book> getFINAL_LIST() {
        return FINAL_LIST;
    }
}
