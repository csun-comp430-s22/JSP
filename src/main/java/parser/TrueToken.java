package parser;

public class TrueToken implements Token {
	public boolean equals(final Object other) {
		return other instanceof TrueToken;
	}
	
	public int hashCode() {
		return 21;
	}
	
	public String toString() {
		return "TrueToken";
	}
}
