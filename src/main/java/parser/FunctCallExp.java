package parser;

import java.util.List;

public class FunctCallExp implements Exp {
	public final FunctNameExp fname;
	public final List params;
	
	public FunctCallExp(final FunctNameExp fname, final List params) {
		this.fname = fname;
		this.params = params;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof FunctCallExp && 
			   fname.equals(((FunctCallExp)other).fname) && 
			   params.equals(((FunctCallExp)other).params));
	}
	
	public int hashCode() {
		return fname.hashCode();
	}
	
	public String toString() {
		return "FunctCallExp(" + fname.toString() +"(" + params.toString() + ")" + ")";
	}
}
