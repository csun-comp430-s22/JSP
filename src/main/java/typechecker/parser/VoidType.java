package typechecker.parser;

public class VoidType implements Type {
	public boolean equals(final Object types) {
		return types instanceof VoidType;
	}
	
	public int hashCode() {
		return 30;
	}
	
	public String toString() {
		return "VoidType";
	}
}
