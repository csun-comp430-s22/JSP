package parser;

public class StructNameType implements Type {
	public final StructName name;
	
	public StructNameType(final StructName name) {
		this.name = name;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof StructNameType && name.equals(((StructNameType)other).name));
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return "StructNameType(" + name.toString() + ")";
	}
}
