package parser;

public class AddressIdentExp implements Exp{
	final Identifier name;
	
	public AddressIdentExp(final Identifier name) {
		this.name = name;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(final Object other) {
		return(other instanceof AddressIdentExp && name.equals(((AddressIdentExp)other).name));
	}
	
	public String toString() {
		return "AddressIdentifier(" + name.toString() + ")";
	}
}