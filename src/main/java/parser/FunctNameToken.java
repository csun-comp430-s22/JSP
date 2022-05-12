package parser;

public class FunctNameToken implements Token {
	public final String name;
	
	public FunctNameToken(final String name) {
		this.name = name;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof FunctNameToken && name.equals(((FunctNameToken)other).name));
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return "FunctNameToken(" + name + ")";
	}
}
