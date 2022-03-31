package parser;

public class AddToken implements Token{
	public boolean equals(final Object tokens) {
		return tokens instanceof AddToken;
	}
	
	public int hashCode() {
		return 0;
	}
	
	public String toString() {
		return "AddToken";
	}
}