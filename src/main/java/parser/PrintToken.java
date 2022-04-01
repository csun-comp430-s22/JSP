package parser;

public class PrintToken implements Token {
	public boolean equals(final Object other) {
		return other instanceof PrintToken;
	}
	
	public int hashCode() {
		return 17;
	}
	
	public String toString() {
		return "PrintToken";
	}

}
