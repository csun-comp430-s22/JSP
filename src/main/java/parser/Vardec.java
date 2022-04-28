package parser;

public class Vardec {
	public final Type type;
    public final Identifier ident;

    public Vardec(final Type type,
                  final Identifier ident) {
        this.type = type;
        this.ident = ident;
    }

    public int hashCode() {
        return type.hashCode() + ident.hashCode();
    }

    public boolean equals(final Object other) {
        if (other instanceof Vardec) {
            final Vardec otherVar = (Vardec)other;
            return (type.equals(otherVar.type) &&
                    ident.equals(otherVar.ident));
        } else {
            return false;
        }
    }

    public String toString() {
        return ("Vardec(" + type.toString() + ", " +
                ident.toString() + ")");
    }
}
