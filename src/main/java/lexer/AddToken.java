package lexer;

public class AddToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof AddToken;
	}
	
	public int hashCode(){
		return 13;
	}
	
	public String toString(){
		return "+";
	}
}