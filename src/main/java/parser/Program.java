package parser;

import java.util.List;

public class Program {
    public final List<Funcdef> functions;
    public final Stmt entry;

    public Program(final List<Funcdef> functions, final Stmt entry) {
        this.functions = functions;
        this.entry = entry;
    }

    public int hashCode() {
        return functions.hashCode() + entry.hashCode();
    }

    public boolean equals(final Object other) {
        /*return (other instanceof Program &&
                functions.equals(((Program)other).functions));*/
    	if (other instanceof Program) {
            final Program otherProgram = (Program)other;
            return (functions.equals(otherProgram.functions) &&
                    entry.equals(otherProgram.entry));
        } else {
            return false;
        }
    }

    public String toString() {
        return "Program(" + functions.toString() + "," + entry.toString() + ")";
    }
}
/*

public class Program implements Node {
	public final Stmt stmt;
	
	public Program(final Stmt stmt) {
		this.stmt = stmt;
	}
	
	public boolean equals(final Object other) {
        if (other instanceof Program) {
            Program prog = (Program) other;
            return (prog.stmt.equals(this.stmt));
        } else {
            return false;
        }
    }

    public int hashCode() {
        return 40;
    }

    public String toString() {
        return ("Program(" + stmt.toString() + ")");
    }
}*/