package parser;

public class IfToken implements Token{
	public boolean equals(final Object other) {
		return other instanceof IfToken;
	}
	
	public int hashCode() {
		return 12;
	}
	
	public String toString() {
		return "IfToken";
	}

}
