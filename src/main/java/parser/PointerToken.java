package parser;

public class PointerToken implements Token {
	public final String name;
	
	public PointerToken(final String name) {
		this.name = name;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof PointerToken && name.equals(((PointerToken)other).name));
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return "Pointer(*" + name + ")";
	}

}
