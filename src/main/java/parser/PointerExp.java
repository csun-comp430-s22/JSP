package parser;

public class PointerExp implements Exp {
	public final Op op;
	public final Exp right;
	
	public PointerExp(final Op op,final Exp right) {
		this.op = op;
		this.right = right;
	}
	
	public int hashCode() {
		return (op.hashCode() + right.hashCode());
	}
	
	public boolean equals(final Object other) {
		if(other instanceof PointerExp) {
			final PointerExp otherExp = (PointerExp)other;
			return(op.equals(otherExp.op) && right.equals(otherExp.right));
		}else {
			return false;
		}
	}
	
	public String toString() {
		return ("PointerExp(" + op.toString() + "," + right.toString() + ")");
	}

}
