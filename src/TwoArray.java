
// Class stores 2 generic arrays for passing data between classes
public class TwoArray<X, Y> {
    private X[] x;
    private Y[] y;
    public TwoArray(X[] x, Y[] y) {
        this.x = x;
        this.y = y;
    }
    public X[] getX() {
        return this.x;
    }
    public Y[] getY() {
        return y;
    }
}

