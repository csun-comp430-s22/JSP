package parser;

public class FunctName {
	public final String name;
	
	public FunctName(final String name) {
		this.name = name;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(final Object other) {
		return(other instanceof FunctName && name.equals(((FunctName)other).name));
	}
	
	public String toString() {
		return "FunctName(" + name + ")";
	}
}
