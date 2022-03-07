package lexer;

public class LeftParenToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof LeftParenToken;
	}
	
	public int hashCode(){
		return 3;
	}
	
	public String toString(){
		return "(";
	}
}