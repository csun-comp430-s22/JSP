package parser;

public class AddOp implements Op{
	public boolean equals(final Object ops) {
		return ops instanceof AddOp;
	}
	
	public int hashCode() {
		return 0;
		
	}
	
	public String toString() {
		return "AddOp";
	}
}