package strategy;

public class LaserPrint implements PrintStrategy {
    private static final String name = "LaserPrint";
    @Override
    public void print(String text) {
        loadCharge();
        transferImage();
        consolidateImage();
        System.out.println("LaserPrint: " + text);
    }

    public void loadCharge() {
        System.out.println("LaserPrint: Charge loaded");
    }

    public void transferImage() {
        System.out.println("LaserPrint: Image transferred");
    }

    public void consolidateImage() {
        System.out.println("LaserPrint: Image consolidated");
    }

    @Override
    public String getName() {
        return name;
    }
}
