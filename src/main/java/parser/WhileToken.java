package parser;

public class WhileToken implements Token {
	public boolean equals(final Object other) {
		return other instanceof WhileToken;
	}
	
	public int hashCode() {
		return 15;
	}
	
	public String toString() {
		return "WhileToken";
	}

}
