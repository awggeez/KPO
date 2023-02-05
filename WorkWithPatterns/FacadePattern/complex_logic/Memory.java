package complex_logic;

import java.util.Arrays;

public class Memory {
public void load(long position, byte[] data) {
        System.out.println("Memory load data " + Arrays.toString(data) + " to position: " + position);
    }
}
