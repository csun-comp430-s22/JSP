package parser;

public class MinusOp implements Op{
	public boolean equals(final Object ops) {
		return ops instanceof MinusOp;
	}
	
	public int hashCode() {
		return 1;
	}
	
	public String toString() {
		return "MinusOp";
	}
}