package parser;

public class StructNameLiteralExp implements Exp {
    public final int value;

    public StructNameLiteralExp(final int value) {
        this.value = value;
    }

    public int hashCode() {
        return value;
    }

    public boolean equals(final Object other) {
        return (other instanceof StructNameLiteralExp &&
                value == ((StructNameLiteralExp)other).value);
    }

    public String toString() {
        return "StructNameLiteralExp(" + value + ")";
    }
}