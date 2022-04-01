package lexer;

public class EqualToToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof EqualToToken;
	}
	
	public int hashCode(){
		return 23;
	}
	
	public String toString(){
		return "==";
	}

}
