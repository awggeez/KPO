package constants;

public class ConsoleCommands {

    private ConsoleCommands() {}

    public static class Commands {
        public static final String GET_THE_BOOK = "/get";
        public static final String LIST_OF_BOOKS = "/list";
        public static final String PUT_THE_BOOK = "/put";
        public static final String ALL_BOOKS = "/all";
        public static final String EXIT = "/exit";

        private Commands() {}
    }

    public static class UserFeedback {
        public static final String INPUT_COMMAND = "\nEnter the command: ";
        public static final String EMPTY_LIST = "You didn't take any book";
        public static final String EMPTY_LIBRARY = "There are no books";
        public static final String ALL_CONTENT = "All books";
        public static final String GOODBYE = "Goodbye!";
        public static final String WRONG_COMMAND = "Wrong command";

        private UserFeedback() {}
    }

    public static class Symbols {
        public static final String SPACE = " ";
        public static final String EMPTY = "";

        private Symbols() {}
    }
}
