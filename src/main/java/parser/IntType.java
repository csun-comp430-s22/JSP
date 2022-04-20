package parser;

public class IntType implements Type {
	public boolean equals(final Object types) {
		return types instanceof IntType;
	}
	
	public int hashCode() {
		return 28;
	}
	
	public String toString() {
		return "IntType";
	}
}
