package factory;

import smartphones.Smartphone;

public abstract class Call {

    public void phoneCall() {
        Smartphone smartphone = createSmartphone();
        smartphone.displayCompanyName();
        smartphone.call();
    }

    public abstract Smartphone createSmartphone();
}
