import java.io.File;
import java.util.List;
import java.util.Map;

import static constants.ConsoleCommands.IncorrectBehavior.*;
import static constants.ConsoleCommands.UserFeedback.*;

public class Checker {
    private int counter = 0;

    /**
     * Check the case when there are zero files in the folder
     * @param fileList list of the all exist files
     * @return true - if there are zero files, false - otherwise
     */
    public boolean checkForEmptyHierarchy(List<File> fileList) {
        if (fileList.size() == 0) {
            System.out.print(WARNING_EMPTY_HIERARCHY);
            return true;
        }
        return false;
    }

    /**
     * Check the case when there are two or more files without requires
     * @param mapOfRequires map: key - name of the file, value - list of its requires
     * @return false - if there are two or more files without requires, true - otherwise
     */
    public boolean checkForTwoHeads(Map<String, List<String>> mapOfRequires) {
        for (Map.Entry<String, List<String>> entry : mapOfRequires.entrySet()) {
            if (entry.getValue().size() == 0) {
                counter++;
            }
            if (counter > 1) {
                System.out.println(TWO_HEADS_IN_THE_HIERARCHY);
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param fileList list of the all exist files
     * @return true - if there is only one file, false - otherwise
     */
    public boolean checkForSingleFile(List<File> fileList) {
        return fileList.size() == 1;
    }
}