package parser;

public class VarLhs implements Lhs {
	public final Exp var;
	
	public VarLhs(final Exp var) {
		this.var = var;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof VarLhs && var.equals(((VarLhs)other).var));
	}
	
	public int hashCode() {
		return var.hashCode();
	}
	
	public String toString() {
		return "VarLhs(" + var.toString() + ")";
	}

}
