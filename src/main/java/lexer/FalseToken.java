package lexer;

public class FalseToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof FalseToken; 
	}
	
	public int hashCode(){
		return 1;
	}
	
	public String toString(){
		return "false";
	}
}