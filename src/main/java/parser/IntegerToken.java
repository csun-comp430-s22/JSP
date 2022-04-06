package parser;

public class IntegerToken implements Token {
    public final int value;

    public IntegerToken(final int value) {
        this.value = value;
    }

    public boolean equals(final IntegerToken other) {
        return (other instanceof IntegerToken &&
                value == ((IntegerToken)other).value);
    }

    public int hashCode() {
        return value;
    }

    public String toString() {
        return "IntegerToken(" + value + ")";
    }
}