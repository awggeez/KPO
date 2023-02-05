package complex_logic;

public class CheckCPU {
    private static final double voltageMax = 1.2;
    public boolean checkCPU(CPU cpu) {
        return cpu.getVoltage() < voltageMax;
    }
}
