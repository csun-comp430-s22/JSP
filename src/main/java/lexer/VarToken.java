package lexer;

public class VarToken implements Token {
	public final String name;
	
	public VarToken(final String name){
		this.name = name;
	}
	
	public int hashCode(){
		return name.hashCode();
	}
	
	public String toString(){
		return "Var(" + name + ")";
	}
	
	public boolean equals(final Object tokens){
		if(tokens instanceof VarToken){
			final VarToken asIdent = (VarToken)tokens;
			return name.equals(asIdent.name);
		}else{
			return false;
		}
	}
}
