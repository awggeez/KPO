import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static constants.ConsoleCommands.UserFeedback.*;
import static constants.ConsoleCommands.IncorrectBehavior.*;

/**
 * @author Abiev Marik Olegovich
 */

public class Main {
    private static String absolutePath;
    private static final Map<String, List<String>> MAP_OF_REQUIRES = new HashMap<>();
    private static final Map<String, List<String>> MAP_OF_DEPENDENCIES = new HashMap<>();
    private static final List<String> LIST_OF_REQUIRES = new ArrayList<>();
    private static final List<String> RESULT_LIST_OF_FILES = new ArrayList<>();
    private static final List<MyFile> MY_FILE_LIST = new ArrayList<>();
    private static final Checker checker = new Checker();

    public static void main(String[] args) throws IOException {
        usersInput();

        List<File> fileList;
        try {
            fileList = Files.walk(Paths.get(absolutePath))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile).toList();
        } catch (Exception exception) {
            System.out.println(INCORRECT_NAME_OF_THE_FILE);
            return;
        }

        if (checker.checkForEmptyHierarchy(fileList)) {
            return;
        }

        ConnectionsMaker.initialization(absolutePath, MAP_OF_DEPENDENCIES);
        ConnectionsMaker.creatingListOfRequires(fileList, LIST_OF_REQUIRES, MAP_OF_REQUIRES);

        if (!checker.checkForTwoHeads(MAP_OF_REQUIRES)) {
            return;
        }

        Graph graph = new Graph(MAP_OF_REQUIRES);
        if (Graph.checkGraphForCorrectness()) {
            System.out.println(CYCLE);
            System.out.println(NAME_OF_THE_CYCLE_FILE + graph.getCycle());
            return;
        }

        HierarchyBuilder.initialization(MAP_OF_REQUIRES, RESULT_LIST_OF_FILES);
        HierarchyBuilder.buildHierarchy(MAP_OF_DEPENDENCIES);
        HierarchyBuilder.buildMyFiles(RESULT_LIST_OF_FILES, MY_FILE_LIST);

        if (checker.checkForSingleFile(fileList)) {
            Displayer.displaySingleFile(fileList);
            return;
        }

        Displayer.display(RESULT_LIST_OF_FILES);
        Displayer.displayText(MY_FILE_LIST);
    }

    /**
     * Responsible for user input
     */
    private static void usersInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(USERS_REQUEST);
        absolutePath = scanner.nextLine();
        System.out.println();
    }
}