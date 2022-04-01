package parser;

public class MultiplicationToken {
	public boolean equals(final Object other) {
		return other instanceof MultiplicationToken;
	}
	
	public int hashCode() {
		return 0;
	}
	
	public String toString() {
		return "MultiplicationToken";
	}

}
