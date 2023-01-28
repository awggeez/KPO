package smartphones;

import static constants.ConsoleCommands.*;

public class SamsungSmartphone implements Smartphone {
    @Override
    public void call() {
        System.out.println(SAMSUNG_CALL);
    }

    @Override
    public void displayCompanyName() {
        System.out.println(DISPLAY_SAMSUNG);
    }
}
