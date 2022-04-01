package parser;

import java.util.List;
import java.util.ArrayList;

public class Parser {
	private final List<Token> tokens;
	
	public Parser(final List<Token> tokens) {
		this.tokens = tokens;
	}
	
	public Token getToken(final int position) throws ParseException {
		if(position >= 0 && position < tokens.size()) {
			return tokens.get(position);
		} else {
			throw new ParseException("Invalid token position: " + position);
		}
	}
	
	public void assertTokenHereIs(final int position, final Token expected) throws ParseException{
		final Token received = getToken(position);
		if(!expected.equals(received)) {
			throw new ParseException("expected: " + expected + "; received: " + received);
		}
	}

}
