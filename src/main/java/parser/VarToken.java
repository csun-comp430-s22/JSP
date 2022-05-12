package parser;

public class VarToken implements Token{
	public final String name;
	
	public VarToken(final String name) {
		this.name = name;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof VarToken && name.equals(((VarToken)other).name));
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return "VarToken(" + name + ")";
	}

}
