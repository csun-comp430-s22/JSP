package parser;

import java.util.List;

public class ReturnStmt implements Stmt {
	
	public final List<Stmt> stmt;
	
	public ReturnStmt(final List<Stmt> stmt) {
		this.stmt = stmt;
	}
	
	public boolean equals(final Object other) {
        if(other instanceof ReturnStmt) {
            final ReturnStmt otherstmt = (ReturnStmt)other;
            return (otherstmt.stmt.equals(this.stmt));
        } else {
            return false;
        }
    }
    public String toString() {
        return "ReturnStmt(" +
                stmt.toString() + ")";
    }
    public int hashCode() {
        return (stmt.hashCode());
    }
}