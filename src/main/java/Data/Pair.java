package Data;

public class Pair<X, Y> {
    private X x;
    private Y y;

    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getKey() {
        return x;
    }

    public void setKey(X x) {
        this.x = x;
    }

    public Y getValue() {
        return y;
    }

    public void setValue(Y y) {
        this.y = y;
    }
}
