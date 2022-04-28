package typechecker.parser;

/*public class IdentifierExp implements Exp{
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

}*/

public class IdentifierExp implements Exp {
    public final Identifier identifier;

    public IdentifierExp(final Identifier identifier) {
        this.identifier = identifier;
    }

    public int hashCode() {
        return identifier.hashCode();
    }

    public boolean equals(final Object other) {
        return (other instanceof IdentifierExp &&
        		identifier.equals(((IdentifierExp)other).identifier));
    }

    public String toString() {
        return "IdentifierExp(" + identifier.toString() + ")";
    }
}
