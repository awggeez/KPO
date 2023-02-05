package complex_logic;

import java.util.Random;

public class CPU {
    private double voltage;
    public void reset() {
        System.out.println("CPU reset memory content");
    }

    public void jump(long position) {
        System.out.println("CPU jump to position: " + position);
    }

    public void execute() {
        System.out.println("CPU execute");
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage() {
        Random random = new Random();
        this.voltage = random.nextDouble() + 0.3; // 0.4 - 1.4
    }
}
