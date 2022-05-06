package parser;

public class AssignmentToken implements Token {
	public boolean equals(final Object other) {
		return other instanceof AssignmentToken;
	}
	
	public int hashCode() {
		return 43;
	}
	
	public String toString() {
		return "AssignmentToken";
	}
}
