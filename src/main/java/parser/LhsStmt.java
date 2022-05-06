package parser;

public class LhsStmt implements Stmt {
	public final Lhs lhs;
	public final Exp exp;

    public LhsStmt(final Lhs lhs, final Exp exp) {
        this.lhs = lhs;
        this.exp = exp;
    }

    public int hashCode() {
        return (lhs.hashCode() + exp.hashCode());
    }

    public boolean equals(final Object other) {
        if (other instanceof LhsStmt) {
            final LhsStmt otherStmt = (LhsStmt)other;
            return (lhs.equals(otherStmt.lhs) &&
            		exp.equals(otherStmt.exp));
        } else {
            return false;
        }
    }

    public String toString() {
        return ("LhsStmt(" + lhs.toString() + "," + exp.toString() + ")");
    }

}
