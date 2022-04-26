package parser;

import java.util.List;

public class BlockStmt implements Stmt {
	public final List<Stmt> stmt;
	
	public BlockStmt(final List<Stmt> stmt) {
		this.stmt = stmt;
	}
	
	public boolean equals(final Object other) {
        if(other instanceof BlockStmt) {
            final BlockStmt otherstmt = (BlockStmt)other;
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