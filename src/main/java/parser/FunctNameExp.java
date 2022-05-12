package parser;

public class FunctNameExp implements Exp {
	public final FunctName fname;
	
	public FunctNameExp(final FunctName fname) {
		this.fname = fname;
	}
	
	public boolean equals(final Object other) {
		return(other instanceof FunctNameExp && fname.equals(((FunctNameExp)other).fname));
	}
	
	public int hashCode() {
		return fname.hashCode();
	}
	
	public String toString() {
		return "FunctNameExp(" + fname.toString() + ")";
	}
}
