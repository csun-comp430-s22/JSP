package parser;

public class FieldOp implements Op {
	public boolean equals(final Object ops) {
		return ops instanceof FieldOp;
	}
	
	public int hashCode() {
		return 43;
	}
	
	public String toString() {
		return "FieldOp";
	}
}
