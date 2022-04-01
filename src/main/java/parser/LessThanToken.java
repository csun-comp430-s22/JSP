package parser;

public class LessThanToken {
	public boolean equals(final Object other) {
		return other instanceof LessThanToken;
	}
	
	public int hashCode() {
		return 4;
	}
	
	public String toString() {
		return "LessThanToken";
	}

}
