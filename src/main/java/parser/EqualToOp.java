package parser;

public class EqualToOp implements Op{
	public boolean equals(final Object ops) {
		return ops instanceof EqualToOp;
	}
	
	public int hashCode() {
		return 6;
	}
	
	public String toString() {
		return "EqualToOp";
	}
}