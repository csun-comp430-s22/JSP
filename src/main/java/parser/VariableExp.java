package parser;

public class VariableExp implements Exp{
	public final Var var;
	
	public VariableExp(final Var var) {
		this.var = var;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof VariableExp && var.equals(((VariableExp)other).var));
	}
	
	public int hashCode() {
		return var.hashCode();
	}
	
	public String toString() {
		return "VariableExp(" + var.toString() + ")";
	}

}
