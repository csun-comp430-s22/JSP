package parser;

public class PointerTypeOp implements Op {
	public boolean equals(final Object ops) {
		return ops instanceof PointerTypeOp;
	}
	
	public int hashCode() {
		return 38;
	}
	
	public String toString() {
		return "PointerTypeOp";
	}
}
