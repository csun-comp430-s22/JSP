package lexer;

public class CommaToken implements Token {
	public boolean equals(final Object tokens){
		return tokens instanceof CommaToken;
	}
	
	public int hashCode(){
		return 25;
	}
	
	public String toString(){
		return ",";
	}
}
