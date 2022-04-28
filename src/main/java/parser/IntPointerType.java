package parser;

public class IntPointerType implements Type {
	public boolean equals(final Object types) {
		return types instanceof IntPointerType;
	}
	
	public int hashCode() {
		return 33;
	}
	
	public String toString() {
		return "IntPointerType";
	}
}
