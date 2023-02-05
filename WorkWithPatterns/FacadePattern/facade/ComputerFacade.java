package facade;

import complex_logic.*;

public class ComputerFacade {

    private static boolean isOk = true;
    private static long ADDRESS = 0x100;
    private static long SECTOR = 0xAA55;
    private static int SECTOR_SIZE = 512;
    private CPU cpu;
    private CheckCPU checkCPU;
    private Memory memory;

    private Monitor monitor;
    private HDD hdd;

    public ComputerFacade() {
        this.cpu = new CPU();
        cpu.setVoltage();
        checkCPU = new CheckCPU();
        if (!checkCPU.checkCPU(cpu)) {
            System.out.println("CPU voltage is too high");
            isOk = false;
        }
        this.memory = new Memory();
        this.hdd = new HDD();
        this.monitor = new Monitor();
    }

    public void start() {
        if (!isOk) {
            System.out.println("Computer will not started");
            return;
        }
        cpu.reset();
        memory.load(ADDRESS, hdd.read(SECTOR, SECTOR_SIZE));
        cpu.jump(ADDRESS);
        cpu.execute();
        monitor.turnOn();
    }
}
