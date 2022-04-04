package parser;

public class Program implements Node {
	public final Stmt stmt;
	
	public Program(final Stmt stmt) {
		this.stmt = stmt;
	}
}