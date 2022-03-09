package lexer;

public class DivideToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof DivideToken;
	}
	
	public int hashCode(){
		return 16;
	}
	
	public String toString(){
		return "/";
	}
}
