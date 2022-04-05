package lexer;

public class PeriodToken implements Token{
	public boolean equals(final Object tokens){
		return tokens instanceof PeriodToken;
	}
	
	public int hashCode(){
		return 24;
	}
	
	public String toString(){
		return ".";
	}

}
