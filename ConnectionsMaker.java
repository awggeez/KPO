import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static constants.ConsoleCommands.Symbols.*;

public class ConnectionsMaker {

    public static final String REQUIRE = "require";
    private static String absolutePath;
    private static Map<String, List<String>> mapOfDependencies;

    /**
     * Initialization fields of the class
     * @param absolutePath absolutePath of the main folder
     * @param mapOfDependencies map: key - name of the file, value - list of its dependencies
     */
    public static void initialization(String absolutePath, Map<String, List<String>> mapOfDependencies) {
        ConnectionsMaker.absolutePath = absolutePath;
        ConnectionsMaker.mapOfDependencies = mapOfDependencies;
    }

    /**
     * Creating list of all exist requires
     * @param fileList list of the all exist files
     * @param listOfRequires list of the all exist requires
     * @param mapOfRequires map: key - name of the file, value - list of its requires
     * @throws IOException if there is some problem with work with files
     */
    public static void creatingListOfRequires(List<File> fileList, List<String> listOfRequires, Map<String,
            List<String>> mapOfRequires) throws IOException {
        for (File file : fileList) {
            var of = Path.of(file.getPath());
            var list = Files.readAllLines(of);
            for (String line : list) {
                findRequires(line, listOfRequires, absolutePath);
            }

            creatingOfTheDependencies(file, listOfRequires, mapOfDependencies);
            mapOfRequires.put(file.getPath(), listOfRequires);
            listOfRequires = new ArrayList<>();
        }
    }

    /**
     * Creating list of all exist dependencies
     * @param file current file in the cycle
     * @param listOfRequires list of the all exist requires
     * @param mapOfDependencies map: key - name of the file, value - list of its dependencies
     */
    public static void creatingOfTheDependencies(File file, List<String> listOfRequires, Map<String, List<String>> mapOfDependencies) {
        for (String require : listOfRequires) {
            var possibleDependencies = mapOfDependencies.get(require);
            if (possibleDependencies == null) {
                List<String> dependencies = new ArrayList<>();
                dependencies.add(file.getPath());
                mapOfDependencies.put(require, dependencies);
            } else {
                possibleDependencies.add(file.getPath());
            }
        }
    }

    /**
     * Find the keyword "require" in each file
     * @param line current line in the file
     * @param listOfRequires list of the all exist requires
     * @param absolutePath absolutePath of the main folder
     */
    public static void findRequires(String line, List<String> listOfRequires, String absolutePath) {
        if (line.startsWith(REQUIRE)) {
            int firstIndexForQuote = REQUIRE.length() + 1, secondIndexForQuote = 0;
            int currentIndex = firstIndexForQuote + 1;
            if (line.charAt(firstIndexForQuote) == EXAMPLE_OPEN_QUOTE || line.charAt(firstIndexForQuote) == KEYBOARD_QUOTE) {
                for (; currentIndex < line.length(); currentIndex++) {
                    if (line.charAt(currentIndex) == EXAMPLE_CLOSE_QUOTE || line.charAt(currentIndex) == KEYBOARD_QUOTE) {
                        secondIndexForQuote = currentIndex;
                        break;
                    }
                }
                var require = (absolutePath + "\\" + line.substring(firstIndexForQuote + 1, secondIndexForQuote))
                        .replaceAll("/", "\\\\");
                listOfRequires.add(require);
            }
        }
    }
}
