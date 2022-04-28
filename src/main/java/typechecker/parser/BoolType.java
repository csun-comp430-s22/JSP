package typechecker.parser;

public class BoolType implements Type {
	public boolean equals(final Object types) {
		return types instanceof BoolType;
	}
	
	public int hashCode() {
		return 29;
	}
	
	public String toString() {
		return "BoolType";
	}
}
