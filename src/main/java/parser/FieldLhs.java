package parser;

public class FieldLhs implements Lhs {
	public final Lhs left;
	public final Op op;
	public final Lhs right;
	
	public FieldLhs(final Lhs left, final Op op, final Lhs right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof FieldLhs && 
				left.equals(((FieldLhs)other).left) &&
				op.equals(((FieldLhs)other).op) &&
				right.equals(((FieldLhs)other).right));
	}
	
	public int hashCode() {
		return (left.hashCode() + op.hashCode() + right.hashCode());
	}
	
	public String toString() {
		return ("FieldLhs(" + left.toString() + "," + 
							  op.toString() + "," + 
							  right.toString() + ")");
	}

}
