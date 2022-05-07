package lexer;

public class FunctionPointerToken implements Token {
	public boolean equals(final Object other) {
        return other instanceof FunctionPointerToken;
    }

    public int hashCode() {
        return 30;
    }

    public String toString() {
        return "=>";
    }

}
