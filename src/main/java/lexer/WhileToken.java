package lexer;

public class WhileToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof WhileToken;
	}
	
	public int hashCode(){
		return 18;
	}
	
	public String toString(){
		return "while";
	}
}