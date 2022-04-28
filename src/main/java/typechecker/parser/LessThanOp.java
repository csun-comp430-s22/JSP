package typechecker.parser;

public class LessThanOp implements Op{
	public boolean equals(final Object ops) {
		return ops instanceof LessThanOp;
	}
	
	public int hashCode() {
		return 4;
	}
	
	public String toString() {
		return "LessThanOp";
	}
}