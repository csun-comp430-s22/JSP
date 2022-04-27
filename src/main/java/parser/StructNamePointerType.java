package parser;

public class StructNamePointerType implements Type {
	public boolean equals(final Object types) {
		return types instanceof StructNamePointerType;
	}
	
	public int hashCode() {
		return 36;
	}
	
	public String toString() {
		return "StructNamePointerType";
	}
}
