package parser;

public class AddressOfFunction implements Exp{
	final FunctionName name;
	
	public AddressOfFunction(final FunctionName name) {
		this.name = name;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(final Object other) {
		return(other instanceof AddressOfFunction && name.equals(((AddressOfFunction)other).name));
	}
	
	public String toString() {
		return "AddressOfFunction(" + name + ")";
	}
}