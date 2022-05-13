package parser;

import java.util.List;

public class Structdec {
	public final Type structName;
	public final List<Vardec> varDecs;
	public final List<Functiondef> funcDefs;
	
	public Structdec(final Type structName, final List<Vardec> varDecs, final List<Functiondef> funcDefs) {
		this.structName = structName;
		this.varDecs = varDecs;
		this.funcDefs = funcDefs;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof Structdec && 
			   structName.equals(((Structdec)other).structName) &&
			   varDecs.equals(((Structdec)other).varDecs) &&
			   funcDefs.equals(((Structdec)other).funcDefs));
	}
	
	public int hashCode() {
		return (structName.hashCode() + varDecs.hashCode() + funcDefs.hashCode());
	}
	
	public String toString() {
		return "Structdec(" + structName.toString() + varDecs.toString() + funcDefs.toString() + ")";
	}
}
