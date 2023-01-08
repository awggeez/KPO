package constants;

public class ConsoleCommands {
    
    private ConsoleCommands() {}

    public static class UserFeedback {
        public static final String USERS_REQUEST = "Enter the absolute path: ";
        public static final String HIERARCHY_OF_FILES = "Final hierarchy of the files: \n";
        public static final String TEXT_OF_THE_HIERARCHY = "Text of the hierarchy: \n";
        public static final String NAME_OF_THE_CYCLE_FILE = "Name of the cycle file: ";
        public static final String WARNING_EMPTY_HIERARCHY = "There are zero files in folder. Impossible to build hierarchy!\n";
        
        private UserFeedback() {}
    }

    public static class IncorrectBehavior {
        public static final String INCORRECT_NAME_OF_THE_FILE = "Incorrect name of the file!\n";
        public static final String TWO_HEADS_IN_THE_HIERARCHY = "There are two files without requires. Impossible to build hierarchy!\n";
        public static final String CYCLE = "There is a cycle in the hierarchy!\n";
        
        private IncorrectBehavior() {}
    }

    public static class Symbols {
        public static final char EXAMPLE_OPEN_QUOTE = '‘';
        public static final char EXAMPLE_CLOSE_QUOTE = '’';
        public static final char KEYBOARD_QUOTE = '\'';
        
        private Symbols() {}
    }
}
