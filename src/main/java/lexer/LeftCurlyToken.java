package lexer;

public class LeftCurlyToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof LeftCurlyToken;
	}
	
	public int hashCode(){
		return 5;
	}
	
	public String toString(){
		return "{";
	}
}