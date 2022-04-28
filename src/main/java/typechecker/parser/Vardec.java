package typechecker.parser;

public class Vardec {
    public final Type type;
    public final Identifier variable;

    public Vardec(final Type type,
                  final Identifier variable) {
        this.type = type;
        this.variable = variable;
    }

    public int hashCode() {
        return type.hashCode() + variable.hashCode();
    }

    public boolean equals(final Object other) {
        if (other instanceof Vardec) {
            final Vardec otherVar = (Vardec)other;
            return (type.equals(otherVar.type) &&
                    variable.equals(otherVar.variable));
        } else {
            return false;
        }
    }

    public String toString() {
        return ("Vardec(" + type.toString() + ", " +
                variable.toString() + ")");
    }
}