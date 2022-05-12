package parser;

import java.util.List;

public class FunctPointerCallExp implements Exp {
	public final VarExp var;
	public final List params;
	
	public FunctPointerCallExp(final VarExp var, final List params) {
		this.var = var;
		this.params = params;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof FunctPointerCallExp && 
			   var.equals(((FunctPointerCallExp)other).var) && 
			   params.equals(((FunctPointerCallExp)other).params));
	}
	
	public int hashCode() {
		return var.hashCode();
	}
	
	public String toString() {
		return "FunctPointerCallExp(" + var.toString() +"(" + params.toString() + ")" + ")";
	}
}
