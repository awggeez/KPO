import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {
    private static Map<String, List<String>> mapOfConnections;
    private static final List<String> ZAP = new ArrayList<>();
    private static final List<String> CYCLE = new ArrayList<>();
    private static boolean checker = true;

    public Graph(Map<String, List<String>> edges) {
        mapOfConnections = edges;
    }

    /**
     * Checking for correctness of the graph
     * @return true - if graph is not correct, false - otherwise
     */
    public static boolean checkGraphForCorrectness() {
        var connections = mapOfConnections;
        for (Map.Entry<String, List<String>> entry : connections.entrySet()) {
            ZAP.clear();
            checkLooping(entry.getKey(), entry.getValue());
            if (!checker) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checking for looping files in the hierarchy
     * @param filename name of the file
     * @param dependencies list of file's dependencies
     */
    public static void checkLooping(String filename, List<String> dependencies) {
        if (!checker) {
            return;
        }
        for (String prov : ZAP) {
            if (prov.equals(filename)) {
                CYCLE.add(filename);
                checker = false;
                return;
            }
        }
        ZAP.add(filename);

        if (dependencies != null) {
            if (dependencies.size() != 0) {
                for (String dest : dependencies) {
                    checkLooping(dest, mapOfConnections.get(dest));
                    ZAP.remove(ZAP.size() - 1);
                }
            }
        }
    }

    public List<String> getCycle() {
        return CYCLE;
    }
}