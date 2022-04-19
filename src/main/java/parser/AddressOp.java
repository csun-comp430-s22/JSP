package parser;

public class AddressOp implements Op{
	public boolean equals(final Object ops) {
		return ops instanceof AddressOp;
	}
	
	public int hashCode() {
		return 24;
	}
	
	public String toString() {
		return "AddressOp";
	}

}
