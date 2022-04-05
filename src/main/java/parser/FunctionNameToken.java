package parser;

public class FunctionNameToken implements Token{
	public final String name;
	
	public FunctionNameToken(final String name) {
		this.name = name;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof FunctionNameToken && name.equals(((FunctionNameToken)other).name));
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return "FunctionName(" + name + ")";
	}
}