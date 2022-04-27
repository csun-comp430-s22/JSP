package parser;

public class FunctionPointerToken implements Token {
	public boolean equals(final Object other) {
		return other instanceof FunctionPointerToken;
	}
	
	public int hashCode() {
		return 37;
	}
	
	public String toString() {
		return "FunctionPointerToken";
	}
}
