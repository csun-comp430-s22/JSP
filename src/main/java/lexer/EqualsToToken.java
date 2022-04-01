package lexer;

public class EqualsToToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof EqualsToToken;
	}
	
	public int hashCode(){
		return 23;
	}
	
	public String toString(){
		return "==";
	}

}
