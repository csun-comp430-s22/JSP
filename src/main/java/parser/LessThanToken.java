package parser;

public class LessThanToken implements Token{
	public boolean equals(final Object tokens) {
		return tokens instanceof LessThanToken;
	}
	
	public int hashCode() {
		return 4;
	}
	
	public String toString() {
		return "LessThanToken";
	}
}