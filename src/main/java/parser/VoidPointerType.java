package parser;

public class VoidPointerType implements Type {
	public boolean equals(final Object types) {
		return types instanceof VoidPointerType;
	}
	
	public int hashCode() {
		return 35;
	}
	
	public String toString() {
		return "VoidPointerType";
	}
}
