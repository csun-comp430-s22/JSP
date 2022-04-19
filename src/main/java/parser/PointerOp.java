package parser;

public class PointerOp implements Op {
	public boolean equals(final Object ops) {
		return ops instanceof PointerOp;
	}
	
	public int hashCode() {
		return 23;
	}
	
	public String toString() {
		return "PointerOp";
	}

}
