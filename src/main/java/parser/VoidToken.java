package parser;

public class VoidToken implements Token {
	public boolean equals(final Object other) {
		return other instanceof VoidToken;
	}
	
	public int hashCode() {
		return 27;
	}
	
	public String toString() {
		return "voidToken";
	}
}
