package parser;

public class LeftParenToken implements Token{
	public boolean equals(final Object other) {
		return other instanceof LeftParenToken;
	}
	
	public int hashCode() {
		return 8;
	}
	
	public String toString() {
		return "LeftParenToken";
	}

}
