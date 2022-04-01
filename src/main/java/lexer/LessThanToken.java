package lexer;

public class LessThanToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof LessThanToken;
	}
	
	public int hashCode(){
		return 22;
	}
	
	public String toString(){
		return "<";
	}

}
