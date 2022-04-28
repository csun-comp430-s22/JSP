package parser;

public class VardecStmt implements Stmt {
	public final Vardec vardec;
    public final Exp exp;

    public VardecStmt(final Vardec vardec,
                      final Exp exp) {
        this.vardec = vardec;
        this.exp = exp;
    }

    public int hashCode() {
        return vardec.hashCode() + exp.hashCode();
    }

    public boolean equals(final Object other) {
        if (other instanceof VardecStmt) {
            final VardecStmt otherStmt = (VardecStmt)other;
            return (vardec.equals(otherStmt.vardec) &&
                    exp.equals(otherStmt.exp));
        } else {
            return false;
        }
    }

    public String toString() {
        return ("VardecStmt(" + vardec.toString() + ", " +
                exp.toString() + ")");
    }

}
