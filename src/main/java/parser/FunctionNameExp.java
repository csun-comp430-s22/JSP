package parser;

public class FunctionNameExp implements Exp{
public final FunctionName name;
	
	public FunctionNameExp(final FunctionName name) {
		this.name = name;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof FunctionNameExp && name.equals(((FunctionNameExp)other).name));
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return "FunctionNameExp(" + name.toString() + ")";
	}

}
