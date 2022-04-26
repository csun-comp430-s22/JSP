package parser;

public class BreakStmt implements Stmt {
	public final Stmt stmt;
	
	public BreakStmt(final Stmt stmt) {
		this.stmt = stmt;
	}

	public boolean equals(final Object other) {
        if(other instanceof BreakStmt) {
            final BreakStmt otherstmt = (BreakStmt)other;
            return (otherstmt.stmt.equals(this.stmt));
        } else {
            return false;
        }
    }
    public String toString() {
        return "BlockStmt(" +
                stmt.toString() + ")";
    }
    public int hashCode() {
        return (stmt.hashCode());
    }
}