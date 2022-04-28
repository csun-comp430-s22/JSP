package parser;

public class StructNameToken implements Token {
	public final String name;
	
	public StructNameToken(final String name) {
		this.name = name;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof StructNameToken && name.equals(((StructNameToken)other).name));
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return "StructNameToken(" + name + ")";
	}

}
