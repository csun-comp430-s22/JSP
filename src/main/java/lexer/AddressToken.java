package lexer;

public class AddressToken implements Token {
	public boolean equals(final Object tokens){
		return tokens instanceof AddressToken;
	}
	
	public int hashCode(){
		return 26;
	}
	
	public String toString(){
		return "&";
	}
}
