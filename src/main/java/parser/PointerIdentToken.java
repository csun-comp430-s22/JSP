package parser;

public class PointerIdentToken implements Token {
	public final String name;
	
	public PointerIdentToken(final String name) {
		this.name = name;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof PointerIdentToken && name.equals(((PointerIdentToken)other).name));
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return "Pointer(*" + name + ")";
	}

}
