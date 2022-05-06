package parser;

public class IdentifierLhs implements Lhs {
	public final Exp ident;
	
	public IdentifierLhs(final Exp ident) {
		this.ident = ident;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof IdentifierLhs && ident.equals(((IdentifierLhs)other).ident));
	}
	
	public int hashCode() {
		return ident.hashCode();
	}
	
	public String toString() {
		return "IdentifierLhs(" + ident.toString() + ")";
	}

}
