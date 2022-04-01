package parser;

public class MinusToken {
	public boolean equals(final Object other) {
		return other instanceof MinusToken;
	}
	
	public int hashCode() {
		return 3;
	}
	
	public String toString() {
		return "MinusToken";
	}

}
