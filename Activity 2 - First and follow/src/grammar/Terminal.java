package grammar;

import java.util.Objects;

public class Terminal extends Symbol {

    public static enum Type {
        REGULAR, EPSILON
    }

    private Type type;

    public Terminal(String name) {
        super(Objects.requireNonNullElse(name, "{e}"));
        this.type = name == null || name.equals("{e}") ? Type.EPSILON : Type.REGULAR;
    }

    public Type getType() {
        return type;
    }
}
