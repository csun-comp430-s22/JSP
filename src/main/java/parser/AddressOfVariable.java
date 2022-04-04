package parser;

public class AddressOfVariable implements Exp{
	final String variable;
	
	public AddressOfVariable(final String variable) {
		
		this.variable = variable;
	
	}
	
	public int hashCode() {
		return variable.hashCode();
	}
	
	public boolean equals(final Object other) {
		return(other instanceof AddressOfVariable && variable.equals(((AddressOfVariable)other).name));
	}
	
	public String toString() {
		return "AddressOfVariable(" + variable + ")";
	}
	
}