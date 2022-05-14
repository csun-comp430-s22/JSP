package parser;

public class Vardec {
	public final Type type;
    public final VarExp var;

    public Vardec(final Type type,
                  final VarExp varExp) {
        this.type = type;
        this.var = varExp;
    }

    public int hashCode() {
        return type.hashCode() + var.hashCode();
    }

    public boolean equals(final Object other) {
        if (other instanceof Vardec) {
            final Vardec otherVar = (Vardec)other;
            return (type.equals(otherVar.type) &&
                    var.equals(otherVar.var));
        } else {
            return false;
        }
    }

    public String toString() {
        return ("Vardec(" + type.toString() + ", " +
                var.toString() + ")");
    }
}
