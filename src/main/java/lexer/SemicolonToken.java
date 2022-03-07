package lexer;

public class SemicolonToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof SemicolonToken;
	}
	
	public int hashCode(){
		return 11;
	}
	
	public String toString(){
		return ";";
	}
}