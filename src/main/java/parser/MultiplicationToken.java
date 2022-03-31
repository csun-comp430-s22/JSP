package parser;

public class MultiplicationToken implements Token{
	public boolean equals(final Object tokens) {
		return tokens instanceof MultiplicationToken;
	}
	
	public int hashCode() {
		return 2;
	}
	
	public String toString() {
		return "MultiplicationToken";
	}
}