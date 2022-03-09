package lexer;

public class IntegerToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof IntegerToken;
	}
	
	public int hashCode(){
		return 10;
	}
	
	public String toString(){
		return "Int";
	}
}