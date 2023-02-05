package strategy;

public class MatrixPrinter implements PrintStrategy {
    private static final String name = "MatrixPrinter";

    @Override
    public void print(String text) {
        headMovement();
        takePaint();
        hitThePaper();
        System.out.println("MatrixPrinter: " + text);
    }

    public void headMovement() {
        System.out.println("MatrixPrinter: Head's moving");
    }

    public void takePaint() {
        System.out.println("MatrixPrinter: Paint taken");
    }

    public void hitThePaper() {
        System.out.println("MatrixPrinter: Head's hitting the paper");
    }

    @Override
    public String getName() {
        return name;
    }
}