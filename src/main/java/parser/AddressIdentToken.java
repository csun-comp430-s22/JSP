package parser;

public class AddressIdentToken implements Token {
	public final String name;
	
	public AddressIdentToken(final String name) {
		this.name = name;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof AddressIdentToken && name.equals(((AddressIdentToken)other).name));
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return "Identifier(&" + name + ")";
	}

}
