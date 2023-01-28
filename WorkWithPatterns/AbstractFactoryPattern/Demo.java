import app.Application;
import factories.BMWFactory;
import factories.CarsFactory;
import factories.MercedesFactory;

import java.util.Scanner;

import static constants.ConsoleCommands.*;

public class Demo {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        Application app = configuration();
        app.drive();
    }

    private static Application configuration() {
        Application application;
        CarsFactory factory = null;

        String companyName;
        boolean isCorrectCompanyName = false;
        System.out.print(INPUT_COMPANY);

        while (!isCorrectCompanyName) {
            companyName = SCANNER.nextLine();
            switch (companyName) {
                case MERCEDES_COMPANY_NAME -> {
                    factory = new MercedesFactory();
                    isCorrectCompanyName = true;
                }
                case BMW_COMPANY_NAME -> {
                    factory = new BMWFactory();
                    isCorrectCompanyName = true;
                }
                default -> {
                    System.out.println(WRONG_INPUT);
                    System.out.print(INPUT_COMPANY);
                }
            }
        }

        application = new Application(factory);
        return application;
    }
}
