package parser;

public class BreakToken implements Token {
	public boolean equals(final Object other) {
		return other instanceof BreakToken;
	}
	
	public int hashCode() {
		return 14;
	}
	
	public String toString() {
		return "BreakToken";
	}

}
