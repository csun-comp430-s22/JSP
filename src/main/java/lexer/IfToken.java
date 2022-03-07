package lexer;

public class IfToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof IfToken;
	}
	
	public int hashCode(){
		return 2;
	}
	
	public String toString(){
		return "if";
	}
}