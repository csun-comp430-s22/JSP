package parser;

public class PointerLhsOp implements Op {
	public boolean equals(final Object ops) {
		return ops instanceof PointerLhsOp;
	}
	
	public int hashCode() {
		return 42;
	}
	
	public String toString() {
		return "PointerLhsOp";
	}
}
