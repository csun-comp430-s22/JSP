package lexer;

public class RightParenToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof RightParenToken;
	}
	
	public int hashCode(){
		return 4;
	}
	
	public String toString(){
		return ")";
	}
}