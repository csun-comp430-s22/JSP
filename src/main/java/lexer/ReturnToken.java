package lexer;

public class ReturnToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof ReturnToken;
	}
	
	public int hashCode(){
		return 17;
	}
	
	public String toString(){
		return "return";
	}
}