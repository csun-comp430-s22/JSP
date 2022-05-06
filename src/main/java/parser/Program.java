package parser;

import java.util.List;

public class Program {
    public final List<Functiondef> functions;

    public Program(final List<Functiondef> functions) {
        this.functions = functions;
    }

    public int hashCode() {
        return functions.hashCode();
    }

    public boolean equals(final Object other) {
    	if (other instanceof Program) {
            final Program otherProgram = (Program)other;
            return (functions.equals(otherProgram.functions));
        } else {
            return false;
        }
    }

    public String toString() {
        return "Program(" + functions.toString() + ")";
    }
}