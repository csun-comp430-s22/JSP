package typechecker.parser;

public class AndOp implements Op {
    public AndOp() {}

    public int hashCode() {
        return 29;
    }

    public boolean equals(final Object other) {
        return other instanceof AndOp;
    }

    public String toString() {
        return "AndOp";
    }
}