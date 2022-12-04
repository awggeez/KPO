public class Cell {

    private char value;
    private boolean isEmpty;

    public Cell(char value, boolean isEmpty) {
        this.value = value;
        this.isEmpty = isEmpty;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
