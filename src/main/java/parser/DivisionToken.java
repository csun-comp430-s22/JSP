package parser;

public class DivisionToken implements Token{
	public boolean equals(final Object tokens) {
		return tokens instanceof DivisionToken;
	}
	
	public int hashCode() {
		return 3;
	}
	
	public String toString() {
		return "DivisionToken";
	}
}