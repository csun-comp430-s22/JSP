package parser;

public class IdentifierExp implements Exp{
	public final Identifier ident;
	
	public IdentifierExp(final Identifier ident) {
		this.ident = ident;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof IdentifierExp && ident.equals(((IdentifierExp)other).ident));
	}
	
	public int hashCode() {
		return ident.hashCode();
	}
	
	public String toString() {
		return "IdentifierExp(" + ident.toString() + ")";
	}

}
