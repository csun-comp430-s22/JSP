package parser;

import java.util.List;

public class StructureDeclaration implements Structdec {
	public final Type structName;
	public final List<Vardec> varDecs;
	public final List<Functiondef> funcDefs;
	
	public StructureDeclaration(final Type structName, final List<Vardec> varDecs, final List<Functiondef> funcDefs) {
		this.structName = structName;
		this.varDecs = varDecs;
		this.funcDefs = funcDefs;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof StructureDeclaration && 
			   structName.equals(((StructureDeclaration)other).structName) &&
			   varDecs.equals(((StructureDeclaration)other).varDecs) &&
			   funcDefs.equals(((StructureDeclaration)other).funcDefs));
	}
	
	public int hashCode() {
		return (structName.hashCode() + varDecs.hashCode() + funcDefs.hashCode());
	}
	
	public String toString() {
		return "StructureDeclaration(" + structName.toString() + varDecs.toString() + funcDefs.toString() + ")";
	}
}
