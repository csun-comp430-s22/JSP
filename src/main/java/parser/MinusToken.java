package parser;

public class MinusToken implements Token{
	public boolean equals(final Object tokens) {
		return tokens instanceof MinusToken;
	}
	
	public int hashCode() {
		return 1;
	}
	
	public String toString() {
		return "MinusToken";
	}
}