package parser;

public class PointerIdentExp implements Exp {
	final Identifier name;
	
	public PointerIdentExp(final Identifier name) {
		this.name = name;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(final Object other) {
		return(other instanceof PointerIdentExp && name.equals(((PointerIdentExp)other).name));
	}
	
	public String toString() {
		return "PointerIdentifier(" + name.toString() + ")";
	}

}
