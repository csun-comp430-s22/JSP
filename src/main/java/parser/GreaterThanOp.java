package parser;

public class GreaterThanOp implements Op{
	public boolean equals(final Object ops) {
		return ops instanceof GreaterThanOp;
	}
	
	public int hashCode() {
		return 5;
	}
	
	public String toString() {
		return "GreaterThanOp";
	}
}