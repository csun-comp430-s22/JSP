package parser;

public class IdentifierToken implements Token{
	public final String name;
	
	public IdentifierToken(final String name) {
		this.name = name;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof IdentifierToken && name.equals(((IdentifierToken)other).name));
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return "Identifier(" + name + ")";
	}

}
