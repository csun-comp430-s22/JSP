package lexer;

public class PointerToken implements Token{
	public final String name;
	
	public PointerToken(final String name){
		this.name = name;
	}
	
	public int hashCode(){
		return name.hashCode();
	}
	
	public String toString(){
		return "Pointer(*" + name + ")";
	}
	
	public boolean equals(final Object tokens){
		if(tokens instanceof PointerToken){
			final PointerToken asPoint = (PointerToken)tokens;
			return name.equals(asPoint.name);
		}else{
			return false;
		}
		
	}

}
