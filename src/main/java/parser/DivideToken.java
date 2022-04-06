package parser;

public class DivideToken implements Token{
	public boolean equals(final Object other) {
		return other instanceof DivideToken;
	}
	
	public int hashCode() {
		return 1;
	}
	
	public String toString() {
		return "DivideToken";
	}

}
