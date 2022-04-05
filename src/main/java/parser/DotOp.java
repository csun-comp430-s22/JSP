package parser;

public class DotOp implements Op{
	public boolean equals(final Object ops) {
		return ops instanceof DotOp;
	}
	
	public int hashCode() {
		return 17;
	}
	
	public String toString() {
		return "DotOp";
	}

}
