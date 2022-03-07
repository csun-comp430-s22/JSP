package lexer;

public class VariableToken implements Token {
	public final String name;
	
	public VariableToken(final String name){
		this.name = name;
	}
	
	public int hashCode(){
		return name.hashCode();
	}
	
	public String toString(){
		return "Variable(" + name + ")";
	}
	
	public boolean equals(final Object tokens){
		if(tokens instanceof VariableToken){
			final VariableToken asVar = (VariableToken)tokens;
			return name.equals(asVar.name);
		}else{
			return false;
		}
		
	}
}