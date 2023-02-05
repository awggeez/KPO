package strategy;

public class JetPrinter implements PrintStrategy {
    private static final String name = "JetPrinter";
    public void print(String text) {
        passageThroughTheNozzle();
        inkDropletsOnThePaper();
        System.out.println("JetPrinter: " + text);
    }

    public void passageThroughTheNozzle() {
        System.out.println("JetPrinter: Passing through the nozzle");
    }

    public void inkDropletsOnThePaper() {
        System.out.println("JetPrinter: Ink droplets on the paper");
    }

    @Override
    public String getName() {
        return name;
    }
}
