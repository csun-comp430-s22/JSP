package parser;

public class EqualToToken implements Token{
	public boolean equals(final Object other) {
		return other instanceof EqualToToken;
	}
	
	public int hashCode() {
		return 6;
	}
	
	public String toString() {
		return "EqualToToken";
	}

}
