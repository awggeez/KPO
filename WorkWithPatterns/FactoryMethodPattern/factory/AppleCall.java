package factory;

import smartphones.AppleSmartphone;
import smartphones.Smartphone;

public class AppleCall extends Call {
    @Override
    public Smartphone createSmartphone() {
        return new AppleSmartphone();
    }
}
