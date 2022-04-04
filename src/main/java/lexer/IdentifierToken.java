package lexer;

public class IdentifierToken implements Token {
	public final String name;
	
	public IdentifierToken(final String name){
		this.name = name;
	}
	
	public int hashCode(){
		return name.hashCode();
	}
	
	public String toString(){
		return "Identifier(" + name + ")";
	}
	
	public boolean equals(final Object tokens){
		if(tokens instanceof IdentifierToken){
			final IdentifierToken asIdent = (IdentifierToken)tokens;
			return name.equals(asIdent.name);
		}else{
			return false;
		}
		
	}
}