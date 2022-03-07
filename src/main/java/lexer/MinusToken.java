package lexer;

public class MinusToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof MinusToken;
	}
	
	public int hashCode(){
		return 14;
	}
	
	public String toString(){
		return "-";
	}
}