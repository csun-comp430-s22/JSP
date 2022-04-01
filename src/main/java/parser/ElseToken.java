package parser;

public class ElseToken implements Token{
	public boolean equals(final Object other) {
		return other instanceof ElseToken;
	}
	
	public int hashCode() {
		return 13;
	}
	
	public String toString() {
		return "ElseToken";
	}

}
