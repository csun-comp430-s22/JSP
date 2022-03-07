package lexer;

public class StringToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof StringToken;
	}
	
	public int hashCode(){
		return 12;
	}
	
	public String toString(){
		return "string";
	}
}