package parser;

public class GreaterThanToken {
	public boolean equals(final Object other) {
		return other instanceof GreaterThanToken;
	}
	
	public int hashCode() {
		return 5;
	}
	
	public String toString() {
		return "GreaterThanToken";
	}

}
