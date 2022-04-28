package parser;

public class StructName {
	public final String name;
	
	public StructName(final String name) {
		this.name = name;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(final Object other) {
		return(other instanceof StructName && name.equals(((StructName)other).name));
	}
	
	public String toString() {
		return "StructName(" + name + ")";
	}

}
