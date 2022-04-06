package parser;

public class EqualsToToken implements Token{
	public boolean equals(final Object other) {
		return other instanceof EqualsToToken;
	}
	
	public int hashCode() {
		return 6;
	}
	
	public String toString() {
		return "EqualsToToken";
	}

}
