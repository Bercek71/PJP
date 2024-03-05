package grammar;

public class Terminal extends Symbol {

    static enum Type{
        REGULAR, EPSILON
    }
    public Terminal(String name) {
        super(name);
    }
}
