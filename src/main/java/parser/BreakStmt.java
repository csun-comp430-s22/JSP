package parser;

public class BreakStmt implements Stmt {
	
	public boolean equals(final Object other) {
        return other instanceof BreakStmt;
    }
    public String toString() {
        return "BreakStmt";
    }
    public int hashCode() {
        return 42;
    }
}