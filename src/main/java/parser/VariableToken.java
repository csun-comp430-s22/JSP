package parser;

public class VariableToken implements Token {
    public final String name;

    public VariableToken(final String name) {
        this.name = name;
    }

    public boolean equals(final Object other) {
        return (other instanceof VariableToken &&
                name.equals(((VariableToken)other).name));
    }

    public int hashCode() {
        return name.hashCode();
    }

    public String toString() {
        return "VariableToken(" + name + ")";
    }
}
