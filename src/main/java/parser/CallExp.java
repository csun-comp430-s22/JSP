package parser;

import java.util.List;

public class CallExp implements Exp {
	public final IdentifierExp ident;
	public final List<ParseResult> params;
	
	public CallExp(final IdentifierExp ident, final List<ParseResult> params) {
		this.ident = ident;
		this.params = params;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof CallExp && ident.equals(((CallExp)other).ident) && params.equals(((CallExp)other).params));
	}
	
	public int hashCode() {
		return ident.hashCode();
	}
	
	public String toString() {
		return "CallExp(" + ident.toString() +"(" + params.toString() + ")" + ")";
	}

}
