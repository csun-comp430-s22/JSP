package parser;

public class VardecStmt implements Stmt {
	public final Vardec vardec;
	public Exp exp;

    public VardecStmt(final Vardec vardec) {
        this.vardec = vardec;
    }

    public int hashCode() {
        return vardec.hashCode();
    }

    public boolean equals(final Object other) {
        if (other instanceof VardecStmt) {
            final VardecStmt otherStmt = (VardecStmt)other;
            return (vardec.equals(otherStmt.vardec));
        } else {
            return false;
        }
    }

    public String toString() {
        return ("VardecStmt(" + vardec.toString() + ")");
    }
}
