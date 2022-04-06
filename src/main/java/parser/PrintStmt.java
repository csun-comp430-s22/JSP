package parser;

public class PrintStmt implements Stmt{
	public final Exp exp;
	
	public PrintStmt(final Exp exp) {
		this.exp = exp;
	}
	
	public int hashCode() {
		return exp.hashCode();
	}
	
	public boolean equals(final Object other) {
		return(other instanceof PrintStmt && exp.equals(((PrintStmt)other).exp));
	}
	
	public String toString() {
		return "Print(" + exp.toString() + ")";
	}
}