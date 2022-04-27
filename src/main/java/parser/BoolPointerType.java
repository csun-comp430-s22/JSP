package parser;

public class BoolPointerType implements Type {
	public boolean equals(final Object types) {
		return types instanceof BoolPointerType;
	}
	
	public int hashCode() {
		return 34;
	}
	
	public String toString() {
		return "BoolPointerType";
	}
}
