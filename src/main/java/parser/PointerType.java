package parser;

public class PointerType implements Type {
	public final Type primaryType;
	public final Op pointerTypeOp;
	public PointerType(final Type primaryType,final Op pointerTypeOp) {
		this.primaryType = primaryType;
		this.pointerTypeOp = pointerTypeOp;
	}
	
	public int hashCode() {
		return (primaryType.hashCode() + pointerTypeOp.hashCode());
	}
	
	public boolean equals(final Object other) {
		if(other instanceof PointerType) {
			final PointerType otherType = (PointerType)other;
			return(primaryType.equals(otherType.primaryType) && pointerTypeOp.equals(otherType.pointerTypeOp));
		}else {
			return false;
		}
	}
	
	public String toString() {
		return ("PointerType(" + primaryType.toString() + "," + pointerTypeOp.toString() + ")");
	}
}
