package context;

import strategy.PrintStrategy;

public class Context {
    private PrintStrategy strategy;

    public Context(PrintStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(String text) {
        strategy.print(text);
    }

    public String getStrategy() {
        return strategy.getName();
    }
}
