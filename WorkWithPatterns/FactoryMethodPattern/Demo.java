import factory.AppleCall;
import factory.Call;
import factory.SamsungCall;

import java.util.Scanner;

import static constants.ConsoleCommands.*;

public class Demo {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Call call;

    public static void main(String[] args) {
        configuration();
        runBusinessLogic();
    }

    private static void configuration() {
        System.out.print(INPUT_COMPANY);
        String companyName;
        boolean isCorrectCompanyName = false;

        while (!isCorrectCompanyName) {
            companyName = SCANNER.nextLine();
            switch (companyName) {
                case SAMSUNG_COMPANY_NAME -> {
                    call = new SamsungCall();
                    isCorrectCompanyName = true;
                }
                case APPLE_COMPANY_NAME -> {
                    call = new AppleCall();
                    isCorrectCompanyName = true;
                }
                default -> {
                    System.out.println(WRONG_INPUT);
                    System.out.print(INPUT_COMPANY);
                }
            }
        }
    }

    private static void runBusinessLogic() {
        call.phoneCall();
    }
}
