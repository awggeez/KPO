import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MyFile {
    private final List<String> textOfTheFile;
    public MyFile(String path) throws IOException {
        textOfTheFile = Files.readAllLines(Paths.get(path));
    }
    public List<String> getTextOfTheFile() {
        return textOfTheFile;
    }
}