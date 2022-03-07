package lexer;

public class BreakToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof BreakToken; 
	}
	
	public int hashCode(){
		return 10;
	}
	
	public String toString(){
		return "break";
	}
}