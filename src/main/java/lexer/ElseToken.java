package lexer;

public class ElseToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof ElseToken;
	}
	
	public int hashCode(){
		return 7;
	}
	
	public String toString(){
		return "else";
	}
}