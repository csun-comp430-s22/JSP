package parser;

public class GreaterThanToken implements Token{
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
