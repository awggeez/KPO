package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class FileIterator implements Iterator<String> {
    private FileReader file;
    private BufferedReader bufferedReader;

    public FileIterator(String fileName) throws FileNotFoundException {
        file = new FileReader(fileName);
        bufferedReader = new BufferedReader(file);
    }

    @Override
    public boolean hasNext() {
        try {
            if (bufferedReader.ready()) {
                return true;
            }
        } catch (IOException e) {
            System.out.println("Error!");
        }
        return false;
    }

    @Override
    public String next() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Error!");
        }
        return null;
    }
}
