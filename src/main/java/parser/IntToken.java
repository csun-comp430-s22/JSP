package parser;

public class IntToken implements Token {
	public boolean equals(final Object other) {
		return other instanceof IntToken;
	}
	
	public int hashCode() {
		return 26;
	}
	
	public String toString() {
		return "IntToken";
	}
}
