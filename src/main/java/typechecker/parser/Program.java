package typechecker.parser;

import java.util.List;

public class Program {
    public final List<Funcdef> functions;

    public Program(final List<Funcdef> functions) {
        this.functions = functions;
    }

    public int hashCode() {
        return functions.hashCode();
    }

    public boolean equals(final Object other) {
        return (other instanceof Program &&
                functions.equals(((Program)other).functions));
    }

    public String toString() {
        return "Program(" + functions.toString() + ")";
    }
}