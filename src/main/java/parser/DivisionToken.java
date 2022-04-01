package parser;

public class DivisionToken {
	public boolean equals(final Object other) {
		return other instanceof DivisionToken;
	}
	
	public int hashCode() {
		return 1;
	}
	
	public String toString() {
		return "DivisionToken";
	}

}
