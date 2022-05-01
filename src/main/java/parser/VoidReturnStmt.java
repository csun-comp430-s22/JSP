package parser;

public class VoidReturnStmt implements Stmt {
	public int hashCode() {
		return 41;
	}
	
	public boolean equals(final Object other) {
		return (other instanceof VoidReturnStmt);
	}
	
	public String toString() {
		return "VoidReturnStmt";
	}
}
