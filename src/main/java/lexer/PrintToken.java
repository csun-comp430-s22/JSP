package lexer;

public class PrintToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof PrintToken;
	}
	
	public int hashCode(){
		return 19;
	}
	
	public String toString(){
		return "print";
	}
}