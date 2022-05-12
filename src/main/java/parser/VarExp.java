package parser;

public class VarExp implements Exp{
	public final Var var;
	
	public VarExp(final Var var) {
		this.var = var;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof VarExp && var.equals(((VarExp)other).var));
	}
	
	public int hashCode() {
		return var.hashCode();
	}
	
	public String toString() {
		return "VarExp(" + var.toString() + ")";
	}

}
