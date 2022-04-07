package parser;

public class ReturnStmt implements Stmt {
	public final Exp exp;
	
	public ReturnStmt(final Exp exp) {
		this.exp = exp;
	}
	
	public int hashCode() {
		return exp.hashCode();
	}
	
	public boolean equals(final Object other) {
		return (other instanceof ReturnStmt && exp.equals(((ReturnStmt)other).exp));
	}
	
	public String toString() {
		return "ReturnStmt(" + exp.toString() + ")";
	}
}