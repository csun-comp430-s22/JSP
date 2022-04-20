package lexer;

public class StructToken implements Token {
	public boolean equals(final Object other) {
        return other instanceof StructToken;
    }

    public int hashCode() {
        return 28;
    }

    public String toString() {
        return "struct";
    }

}
