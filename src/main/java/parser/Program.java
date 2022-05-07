package parser;

import java.util.List;

public class Program {
	public final List<Structdec> structures;
    public final List<Functiondef> functions;

    public Program(final List<Structdec> structures, final List<Functiondef> functions) {
        this.structures = structures;
    	this.functions = functions;
    }

    public int hashCode() {
        return (structures.hashCode() + functions.hashCode());
    }

    public boolean equals(final Object other) {
    	if (other instanceof Program) {
            final Program otherProgram = (Program)other;
            return (structures.equals(otherProgram.structures) && functions.equals(otherProgram.functions));
        } else {
            return false;
        }
    }

    public String toString() {
        return "Program(" + structures.toString() + functions.toString() + ")";
    }
}