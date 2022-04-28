package typechecker.parser;

public class StructNameType implements Type {
	public boolean equals(final Object types) {
		return types instanceof StructNameType;
	}
	
	public int hashCode() {
		return 32;
	}
	
	public String toString() {
		return "StuctNameType";
	}
}
