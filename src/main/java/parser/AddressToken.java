package parser;

public class AddressToken implements Token {
	public boolean equals(final Object other) {
		return other instanceof AddressToken;
	}
	
	public int hashCode() {
		return 25;
	}
	
	public String toString() {
		return "AddressToken";
	}

}
