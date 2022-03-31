package parser;

public class DivisionOp implements Op{
	public boolean equals(final Object ops) {
		return ops instanceof DivisionOp;
	}
	
	public int hashCode() {
		return 3;
	}
	
	public String toString() {
		return "DivisionOp";
	}
}