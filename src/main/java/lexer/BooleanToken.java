package lexer;

public class BooleanToken implements Token{
	public boolean equals(final Object token) {
		return token instanceof BooleanToken;
	}
	
	public int hashCode() {
		return 20;
	}
	
	public String toString() {
		return "Bool";
		
	}
}