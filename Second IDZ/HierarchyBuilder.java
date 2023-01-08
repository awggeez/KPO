import java.io.IOException;
import java.util.List;
import java.util.Map;

import static constants.ConsoleCommands.IncorrectBehavior.*;

public class HierarchyBuilder {

    private static Map<String, List<String>> mapOfRequires;
    private static List<String> resultListOfFiles;

    /**
     * Initialization fields of the class
     * @param mapOfRequires map: key - name of the file, value - list of its requires
     * @param resultListOfFiles the final list of the files
     */
    public static void initialization(Map<String, List<String>> mapOfRequires, List<String> resultListOfFiles) {
        HierarchyBuilder.mapOfRequires = mapOfRequires;
        HierarchyBuilder.resultListOfFiles = resultListOfFiles;
    }

    /**
     * Building list "myFiles" from the final list of files
     * @param resultListOfFiles the final list of the files
     * @param myFileList list of "myFiles"
     * @throws IOException if there is some problem with work with files
     */
    public static void buildMyFiles(List<String> resultListOfFiles, List<MyFile> myFileList) throws IOException {
        for (String fileName : resultListOfFiles) {
            myFileList.add(new MyFile(fileName));
        }
    }

    /**
     * Building the final hierarchy of the files
     * @param mapOfDependencies map: key - name of the file, value - list of its dependencies
     */
    public static void buildHierarchy(Map<String, List<String>> mapOfDependencies) {
        for (Map.Entry<String, List<String>> entry : mapOfDependencies.entrySet()) {
            var list = entry.getValue();
            if (list.size() != 0) {
                hierarchyTraversal(entry.getKey(), mapOfDependencies);
            }
        }
    }

    /**
     * Traversal map of dependencies for building the hierarchy
     * @param filename name of the file
     * @param mapOfDependencies map: key - name of the file, value - list of its dependencies
     */
    private static void hierarchyTraversal(String filename, Map<String, List<String>> mapOfDependencies) {
        var dependencies = mapOfDependencies.get(filename);
        if (dependencies != null && dependencies.size() != 0) {
            for (String dependency : dependencies) {
                var otherDependencies = mapOfDependencies.get(dependency);
                if (otherDependencies != null && otherDependencies.size() != 0) {
                    hierarchyTraversal(dependency, mapOfDependencies);
                } else {
                    addingFilesToResultList(dependency, mapOfRequires, resultListOfFiles);
                }
            }
        }
        addingFilesToResultList(filename, mapOfRequires, resultListOfFiles);
    }

    /**
     * Adding files to final list
     * @param filename name of the file
     * @param mapOfRequires map: key - name of the file, value - list of its requires
     * @param resultListOfFiles the final list of the files
     */
    private static void addingFilesToResultList(String filename, Map<String, List<String>> mapOfRequires, List<String> resultListOfFiles) {
        try {
            for (String require : mapOfRequires.get(filename)) {
                if (mapOfRequires.get(require) != null) {
                    if (mapOfRequires.get(require).size() == 0) {
                        if (!resultListOfFiles.contains(require)) {
                            resultListOfFiles.add(require);
                        }
                    } else {
                        addingFilesToResultList(require, mapOfRequires, resultListOfFiles);
                    }
                }
            }
            if (!resultListOfFiles.contains(filename)) {
                resultListOfFiles.add(filename);
            }
        } catch (Exception ex) {
            System.out.println(INCORRECT_NAME_OF_THE_FILE);
            System.exit(1);
        }
    }
}