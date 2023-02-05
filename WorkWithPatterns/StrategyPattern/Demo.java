import context.Context;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String textToPrint = scanner.nextLine();
        scanner.close();

        Context context = new Context(new strategy.LaserPrint());
        System.out.println("Printing the text: \"" + textToPrint + "\" using the strategy: " + context.getStrategy());
        context.executeStrategy(textToPrint);
        System.out.println();

        context = new Context(new strategy.MatrixPrinter());
        System.out.println("Printing the text: \"" + textToPrint + "\" using the strategy: " + context.getStrategy());
        context.executeStrategy(textToPrint);
        System.out.println();

        context = new Context(new strategy.JetPrinter());
        System.out.println("Printing the text: \"" + textToPrint + "\" using the strategy: " + context.getStrategy());
        context.executeStrategy(textToPrint);
        System.out.println();
    }
}
