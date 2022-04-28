package typechecker.parser;

public class Identifier{
	public final String name;
	
	public Identifier(final String name) {
		this.name = name;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(final Object other) {
		return(other instanceof Identifier && name.equals(((Identifier)other).name));
	}
	
	public String toString() {
		return "Ident(" + name + ")";
	}
} 