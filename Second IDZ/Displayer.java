import java.io.File;
import java.io.IOException;
import java.util.List;

import static constants.ConsoleCommands.UserFeedback.*;

public class Displayer {

    /**
     * Display the final hierarchy of the files
     * @param resultListOfFiles the final list of the files
     */
    public static void display(List<String> resultListOfFiles) {
        System.out.println(HIERARCHY_OF_FILES);
        for (String filename : resultListOfFiles) {
            System.out.println(filename);
        }
        System.out.println();
    }

    /**
     * Display the text of final hierarchy
     * @param myFileList list of "myFiles"
     */
    public static void displayText(List<MyFile> myFileList) {
        System.out.println(TEXT_OF_THE_HIERARCHY);
        for (MyFile myFile : myFileList) {
            var textOfTheFile = myFile.getTextOfTheFile();
            for (String line : textOfTheFile) {
                System.out.println(line);
            }
            System.out.println();
        }
    }

    /**
     * Display the text of final hierarchy
     * @param fileList list of the all exist files
     * @throws IOException if there is some problem with work with files
     */
    public static void displaySingleFile(List<File> fileList) throws IOException {
        var absolutePath = fileList.get(0).getAbsolutePath();
        MyFile singleFile = new MyFile(absolutePath);

        System.out.println(HIERARCHY_OF_FILES);
        System.out.println(absolutePath);
        System.out.println();

        System.out.println(TEXT_OF_THE_HIERARCHY);
        var textOfTheFile = singleFile.getTextOfTheFile();
        for (String line : textOfTheFile) {
            System.out.println(line);
        }
    }
}