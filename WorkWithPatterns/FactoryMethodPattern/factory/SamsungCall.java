package factory;

import smartphones.SamsungSmartphone;
import smartphones.Smartphone;

public class SamsungCall extends Call {
    @Override
    public Smartphone createSmartphone() {
        return new SamsungSmartphone();
    }
}
