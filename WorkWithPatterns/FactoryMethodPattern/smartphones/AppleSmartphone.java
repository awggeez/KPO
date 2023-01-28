package smartphones;

import static constants.ConsoleCommands.*;

public class AppleSmartphone implements Smartphone {
    @Override
    public void call() {
        System.out.println(APPLE_CALL);
    }

    @Override
    public void displayCompanyName() {
        System.out.println(DISPLAY_APPLE);
    }
}
