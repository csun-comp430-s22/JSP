package parser;

public class IntegerVariable implements Token {
    public final int value;

    public IntegerVariable(final int value) {
        this.value = value;
    }

    public boolean equals(final Object other) {
        return (other instanceof IntegerVariable && value == ((IntegerVariable)other).value);
    }

    public int hashCode() {
        return value;
    }

    public String toString() {
        return "IntegerVariable(" + value + ")";
    }
}