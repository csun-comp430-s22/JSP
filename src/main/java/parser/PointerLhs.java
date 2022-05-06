package parser;

public class PointerLhs implements Lhs {
	public final Op op;
	public final Lhs right;
	
	public PointerLhs(final Op op,final Lhs right) {
		this.op = op;
		this.right = right;
	}
	
	public int hashCode() {
		return (op.hashCode() + right.hashCode());
	}
	
	public boolean equals(final Object other) {
		if(other instanceof PointerLhs) {
			final PointerLhs otherLhs = (PointerLhs)other;
			return(op.equals(otherLhs.op) && right.equals(otherLhs.right));
		}else {
			return false;
		}
	}
	
	public String toString() {
		return ("PointerLhs(" + op.toString() + "," + right.toString() + ")");
	}
}
