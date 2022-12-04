import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Integer[]> steps = new ArrayList<>();
    private int score = 2;

    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Integer[]> getSteps() {
        return steps;
    }
}
