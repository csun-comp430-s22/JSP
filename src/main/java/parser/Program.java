package parser;

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
}