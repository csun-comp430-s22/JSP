package parser;

import java.util.List;

public class FunctionDefinition implements Functiondef{
	public final Type returnType;
	public final Exp functionName;
	public final List<Vardec> params;
	public final Stmt body;
	
	public FunctionDefinition(final Type returnType, final Exp functionName, final List<Vardec> params, final Stmt body) {
		this.returnType = returnType;
		this.functionName = functionName;
		this.params = params;
		this.body = body;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof FunctionDefinition && 
			   returnType.equals(((FunctionDefinition)other).returnType) &&
			   functionName.equals(((FunctionDefinition)other).functionName) &&
			   params.equals(((FunctionDefinition)other).params) &&
			   body.equals(((FunctionDefinition)other).body));
	}
	
	public int hashCode() {
		return (returnType.hashCode() + functionName.hashCode() + params.hashCode() + body.hashCode());
	}
	
	public String toString() {
		return "FunctionDefinition(" + returnType.toString() + functionName.toString() +"(" + params.toString() + ")" + body.toString() + ")";
	}
}
