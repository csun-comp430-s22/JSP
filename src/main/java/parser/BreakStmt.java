package parser;

public class BreakStmt implements Stmt {
	public final Exp exp;
	
	public BreakStmt(final Exp exp) {
		this.exp = exp;
	}
	
	public int hashCode() {
		return exp.hashCode();
	}
	
	public boolean equals(final Object other) {
		return (other instanceof BreakStmt && exp.equals(((BreakStmt)other).exp));
	}
	
	public String toString() {
		return "BreakStmt(" + exp.toString() + ")";
	}
}