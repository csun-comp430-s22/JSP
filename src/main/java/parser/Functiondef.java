package parser;

import java.util.List;

public class Functiondef {
	public final Type returnType;
	public final Exp functionName;
	public final List<Vardec> params;
	public final Stmt body;
	
	public Functiondef(final Type returnType, final Exp functionName, final List<Vardec> params, final Stmt body) {
		this.returnType = returnType;
		this.functionName = functionName;
		this.params = params;
		this.body = body;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof Functiondef && 
			   returnType.equals(((Functiondef)other).returnType) &&
			   functionName.equals(((Functiondef)other).functionName) &&
			   params.equals(((Functiondef)other).params) &&
			   body.equals(((Functiondef)other).body));
	}
	
	public int hashCode() {
		return (returnType.hashCode() + functionName.hashCode() + params.hashCode() + body.hashCode());
	}
	
	public String toString() {
		return "Functiondef(" + returnType.toString() + functionName.toString() +"(" + params.toString() + ")" + body.toString() + ")";
	}
}
