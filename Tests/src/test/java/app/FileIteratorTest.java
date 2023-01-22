package app;

import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileIteratorTest extends TestCase {

    public void testWhenEmpty__ThenTrueReturned() throws IOException {
        var path = "D:\\KPO\\Git_HW\\Tests\\src\\main\\java\\app\\input.txt";
        FileIterator iterator = new FileIterator(path);
        try (FileWriter ignored = new FileWriter(path)) {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertFalse(iterator.hasNext());
    }

    public void testWhenNotEmpty__ThenTrueReturned() throws IOException {
        var path = "D:\\KPO\\Git_HW\\Tests\\src\\main\\java\\app\\input.txt";
        FileIterator iterator = new FileIterator(path);
        try (FileWriter file = new FileWriter(path)) {
            file.write("AAA");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertTrue(iterator.hasNext());
    }

    public void testNext() throws FileNotFoundException {
        final String toFile = "Aaa";
        final String toCheck = "Aaa";
        var path = "D:\\KPO\\Git_HW\\Tests\\src\\main\\java\\app\\input.txt";
        FileIterator iterator = new FileIterator(path);
        try (FileWriter file = new FileWriter(path)) {
            file.write(toFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertEquals(iterator.next(), toCheck);
    }
}