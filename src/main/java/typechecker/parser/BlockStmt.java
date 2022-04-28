package typechecker.parser;

import java.util.List;

public class BlockStmt implements Stmt {
    public final List<Stmt> body;

    public BlockStmt(final List<Stmt> body) {
        this.body = body;
    }

    public int hashCode() {
        return body.hashCode();
    }
    
    public boolean equals(final Object other) {
        return (other instanceof BlockStmt &&
                body.equals(((BlockStmt)other).body));
    }

    public String toString() {
        return "BlockStmt(" + body.toString() + ")";
    }
}