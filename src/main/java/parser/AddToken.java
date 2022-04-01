package parser;

public class AddToken implements Token{
	public boolean equals(final Object other) {
		return other instanceof AddToken;
	}
	
	public int hashCode() {
		return 2;
	}
	
	public String toString() {
		return "AddToken";
	}

}
