package parser;

import java.util.List;

public class FunctionPointerType implements Type {
	public final List argTypes;
	public final ParseResult<Type> returnType;
	
	public FunctionPointerType(final List argTypes,final ParseResult<Type> returnType) {
		this.argTypes = argTypes;
		this.returnType = returnType;
	}
	
	public int hashCode() {
		return (argTypes.hashCode() + returnType.hashCode());
	}
	
	public boolean equals(final Object other) {
		if(other instanceof FunctionPointerType) {
			final FunctionPointerType otherFunctionPointerType = (FunctionPointerType)other;
			return(argTypes.equals(otherFunctionPointerType.argTypes) && returnType.equals(otherFunctionPointerType.returnType));
		}else {
			return false;
		}
	}
	
	public String toString() {
		return ("FunctionPointerType(" + "(" + argTypes.toString() + ") => " + returnType.toString() + ")");
	}

}
