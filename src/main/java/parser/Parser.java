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
    
    //parses variable, integer, or (exp)
    public ParseResult<Exp> parsePrimaryExp(final int position) throws ParseException {
    	final Token token = getToken(position);
    	if(token instanceof VariableToken) {
    		final String name = ((VariableToken)token).name;
    		return new ParseResult<Exp>(new VariableExp(name), position + 1);
    	} else if(token instanceof IntegerToken) {
    		final int value = ((IntegerToken)token).value;
    		return new ParseResult<Exp>(new IntegerExp(value), position + 1);
    	} else if(token instanceof LeftParenToken) {
    		final ParseResult<Exp> inParens = parseExp(position + 1);
    		assertTokenHereIs(inParens.position, new RightParenToken());
    		return new ParseResult<Exp>(inParens.result, inParens.position + 1);
    	} else {
    		throw new ParseException("expected primaryExp; received: " + token);
    	}
    }
    
    //multiplicative op for * | /
    public ParseResult<Op> parseMultiplicativeOp(final int position) throws ParseException{
    	final Token token = getToken(position);
    	
    	if(token instanceof MultiplicationOp) {
    		
    		return new ParseResult<Op>(new MultiplicationOp(), position + 1);
    	
    	}else if(token instanceof DivisionOp) {
    		
    		return new ParseResult<Op>(new DivisionOp(), position + 1);
    	
    	} else {
    		throw new ParseException("expected * or /; recieved: " + token);
    	}
    }
    
    //parses an expression that contains a * or / operation (example: 3 * 2, or 6 / 3
    public ParseResult<Exp> parseMultiplicativeExp(final int position) throws ParseException {
    	ParseResult<Exp> current = parsePrimaryExp(position);
    	boolean shouldRun = true;
    	
    	while(shouldRun) {
    		try {
    			final ParseResult<Op> multiplicativeOp = parseMultiplicativeOp(current.position);
    			final ParseResult<Exp> anotherPrimary = parsePrimaryExp(multiplicativeOp.position);
    			current = new ParseResult<Exp>(new OpExp(current.result,
    													multiplicativeOp.result,
    													anotherPrimary.result),
    											anotherPrimary.position);
    		} catch(final ParseException e) {
    			shouldRun = false;
    		}
    	}
    	
    	return current;
    }
    
    //additive op for + | -
    public ParseResult<Op> parseAdditiveOp(final int position) throws ParseException {
        final Token token = getToken(position);
        
        if (token instanceof AddToken) {
          
        	return new ParseResult<Op>(new AddOp(), position + 1);
        
        } else if (token instanceof MinusToken) {
            
        	return new ParseResult<Op>(new MinusOp(), position + 1);
        
        } else {
            
        	throw new ParseException("expected + or -; received: " + token);
        
        }
    }
    
    //parses an expression that contains a + or - operation (example: 1 + 2, or 3 - 1)
    public ParseResult<Exp> parseAdditiveExp(final int position) throws ParseException {
    	ParseResult<Exp> current = parseMultiplicativeExp(position);
    	boolean shouldRun = true;
    	
    	while(shouldRun) {
    		try {
    			final ParseResult<Op> additiveOp = parseAdditiveOp(current.position);
    			final ParseResult<Exp> anotherMultiplicative = parseMultiplicativeExp(additiveOp.position);
    			current = new ParseResult<Exp>(new OpExp(current.result,
    													additiveOp.result,
    													anotherMultiplicative.result),
    											anotherMultiplicative.position);
    		} catch(final ParseException e) {
    			shouldRun = false;
    		}
    	}
    	
    	return current;
    }
   
    //comparison op for < | >
    
    
    //parses an expression that contains a < or > operation (example: x < 2, or 3 > 1)
    public ParseResult<Exp> parseComparisonExp(final int position) throws ParseException {
    	ParseResult<Exp> current = parseAdditiveExp(position);
    	boolean shouldRun = true;
    	
    	while(shouldRun) {
    		try {
    			final ParseResult<Op> comparisonOp = parseComparisonOp(current.position);
    			final ParseResult<Exp> anotherAdditive = parseAdditiveExp(comparisonOp.position);
    			current = new ParseResult<Exp>(new OpExp(current.result,
    													comparisonOp.result,
    													anotherAdditive.result),
    											anotherAdditive.position);
    		} catch(final ParseException e) {
    			shouldRun = false;
    		}
    	}
    	
    	return current;
    }
    
    //equals op for ==
    
    //parses an expression that contains a == operation (example: a == b )
    public ParseResult<Exp> parseEqualsExp(final int position) throws ParseException {
    	ParseResult<Exp> current = parseComparisonExp(position);
    	boolean shouldRun = true;
    	
    	while(shouldRun) {
    		try {
    			final ParseResult<Op> equalsOp = parseEqualsOp(current.position);
    			final ParseResult<Exp> anotherComparison = parseComparisonExp(equalsOp.position);
    			current = new ParseResult<Exp>(new OpExp(current.result,
    													equalsOp.result,
    													anotherComparison.result),
    											anotherComparison.position);
    		} catch(final ParseException e) {
    			shouldRun = false;
    		}
    	}
    	
    	return current;
    }
    
    //parses a generic expression
    public ParseResult<Exp> parseExp(final int position) throws ParseException {
    	final Token token = getToken(position);
    	if(token instanceof VariableToken) {
    		assertTokenHereIs(position + 1, new LeftParenToken());
    		boolean shouldRun = true;
    		while(shouldRun) {
        		try {
        			final ParseResult<Exp> anotherExp = parseMultiplicativeOp(current.position);
        			current = new ParseResult<Exp>(new OpExp(current.result,
        													multiplicativeOp.result,
        													anotherPrimary.result),
        											anotherPrimary.position);
        		} catch(final ParseException e) {
        			shouldRun = false;
        		}
        	}
    		
    	} else if(token instanceof AddressVarToken) {
    		
    	} else if(token instanceof AddressFuncNameToken) {
    		
    	} else {
    		ParseResult<Exp> current = parseEqualsExp(position);
        	return current;
    	}
    }
   
    //parse stmt
    
}
