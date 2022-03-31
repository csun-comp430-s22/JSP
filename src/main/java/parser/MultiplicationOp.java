package parser;

public class MultiplicationOp implements Op{
	public boolean equals(final Object ops) {
		return ops instanceof MultiplicationOp;
	}
	
	public int hashCode() {
		return 2;
	}
	
	public String toString() {
		return "MultiplicationOp";
	}
}