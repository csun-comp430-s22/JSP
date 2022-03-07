package lexer;

public class RightCurlyToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof RightCurlyToken;
	}
	
	public int hashCode(){
		return 6;
	}
	
	public String toString(){
	return "}";
	}
}