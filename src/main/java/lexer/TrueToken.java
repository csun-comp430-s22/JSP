package lexer;

public class TrueToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof TrueToken;
	}
	
	public int hashCode(){
		return 0;
	}
	
	public String toString(){
		return "true";
	}
}