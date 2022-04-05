package lexer;

public class AddressToken implements Token {
	public final String name;
	
	public AddressToken(final String name){
		this.name = name;
	}
	
	public int hashCode(){
		return name.hashCode();
	}
	
	public String toString(){
		return "Address(&" + name + ")";
	}
	
	public boolean equals(final Object tokens){
		if(tokens instanceof AddressToken){
			final AddressToken asAddress = (AddressToken)tokens;
			return name.equals(asAddress.name);
		}else{
			return false;
		}
		
	}

}
