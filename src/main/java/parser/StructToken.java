package parser;

public class StructToken implements Token {
	public boolean equals(final Object other) {
		return other instanceof StructToken;
	}
	
	public int hashCode() {
		return 31;
	}
	
	public String toString() {
		return "StructToken";
	}
}
