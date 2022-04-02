package parser;

import java.util.List;
import java.util.ArrayList;

public class Parser {
    private final List<Token> tokens;

    public Parser(final List<Token> tokens) {
    	
        this.tokens = tokens;
    
    }
    
    public Token getToken(final int position) throws ParseException {
        if (position >= 0 && position < tokens.size()) {
      
            return tokens.get(position);
        
        } else {
            
        	
        	throw new ParseException("Invalid token position: " + position);
        
        }
        
    }
    public void assertTokenHereIs(final int position, final Token expected) throws ParseException {
        final Token received = getToken(position);
        
        if (!expected.equals(received)) {
        	
            throw new ParseException("expected: " + expected + "; received: " + received);
        
        }
    }
    
    //additive op for +, -
    public ParseResult<Op> AdditiveOp(final int position) throws ParseException {
        final Token token = getToken(position);
        
        if (token instanceof AddToken) {
          
        	return new ParseResult<Op>(new AddOp(), position + 1);
        
        } else if (token instanceof MinusToken) {
            
        	return new ParseResult<Op>(new MinusOp(), position + 1);
        
        } else {
            
        	throw new ParseException("expected + or -; received: " + token);
        
        }
    }
    
    //ParseResult<Exp>, exp
   
    //ParserResult<Op>, additive_exp
   
    //parse stmt
    
}