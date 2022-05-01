package parser;

public class IntegerLiteralExp implements Exp {
    public final int value;

    public IntegerLiteralExp(final int value) {
        this.value = value;
    }

    public int hashCode() {
        return value;
    }

    public boolean equals(final Object other) {
        return (other instanceof IntegerLiteralExp &&
                value == ((IntegerLiteralExp)other).value);
    }

    public String toString() {
        return "IntegerLiteralExp(" + value + ")";
    }
}