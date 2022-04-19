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
    
  //Pointer op for * | &
    public ParseResult<Op> parsePointerOp(final int position) throws ParseException{
    	final Token token = getToken(position);
    	
    	if(token instanceof MultiplicationToken) {
    		
    		return new ParseResult<Op>(new PointerOp(), position + 1);
    	
    	}else if(token instanceof AddressToken) {
    		
    		return new ParseResult<Op>(new AddressOp(), position + 1);
    	
    	} else {
    		throw new ParseException("expected * or &; recieved: " + token);
    	}
    }
    
    //parses variable, integer, or (exp)
    public ParseResult<Exp> parsePrimaryExp(final int position) throws ParseException {
    	final Token token = getToken(position);
    	
    	if(token instanceof MultiplicationToken) {
    		final Token pointerToken = getToken(position + 1);
    		final ParseResult<Exp> pointer = parsePrimaryExp(position + 1);
    		if(pointerToken instanceof IdentifierToken) {
    			final ParseResult<Op> pointerOp = parsePointerOp(pointer.position - 2);
    			return new ParseResult<Exp>(new PointerExp(pointerOp.result,
    														pointer.result), 
    										pointer.position);
    		} else {
    			throw new ParseException("expected IdentifierToken; received: " + pointerToken);
    		}
    	} else if(token instanceof AddressToken) {
    		final Token addressToken = getToken(position + 1);
    		final ParseResult<Exp> address = parsePrimaryExp(position + 1);
    		if(addressToken instanceof IdentifierToken) {
    			final ParseResult<Op> addressOp = parsePointerOp(address.position - 2);
    			return new ParseResult<Exp>(new PointerExp(addressOp.result,
    														address.result), 
    										address.position);
    		} else {
    			throw new ParseException("expected IdentifierToken; received: " + addressToken);
    		}
    	} else if(token instanceof IdentifierToken) {
    		final String name = ((IdentifierToken)token).name;
    		return new ParseResult<Exp>(new IdentifierExp(new Identifier(name)), position + 1); 
    	}else if(token instanceof IntegerToken) {
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
    
    //dot op for .
    public ParseResult<Op> parseDotOp(final int position) throws ParseException{
    	final Token token = getToken(position);
    	
    	if(token instanceof PeriodToken) {
    		
    		return new ParseResult<Op>(new DotOp(), position + 1);
    	
    	}else {
    		throw new ParseException("expected .; recieved: " + token);
    	}
    }
    
    //parses an expression that contains a . (example: x.a where x and a are both variables)
    public ParseResult<Exp> parseDotExp(final int position) throws ParseException {
    	ParseResult<Exp> current = parsePrimaryExp(position);
    	boolean shouldRun = true;
    	
    	while(shouldRun) {
    		try {
    			final ParseResult<Op> dotOp = parseDotOp(current.position);
    			final ParseResult<Exp> anotherPrimary = parsePrimaryExp(dotOp.position);
    			current = new ParseResult<Exp>(new OpExp(current.result,
    													dotOp.result,
    													anotherPrimary.result),
    											anotherPrimary.position);
    		} catch(final ParseException e) {
    			shouldRun = false;
    		}
    	}
    	
    	return current;
    }
    
    //multiplicative op for * | /
    public ParseResult<Op> parseMultiplicativeOp(final int position) throws ParseException{
    	final Token token = getToken(position);
    	
    	if(token instanceof MultiplicationToken) {
    		
    		return new ParseResult<Op>(new MultiplicationOp(), position + 1);
    	
    	}else if(token instanceof DivideToken) {
    		
    		return new ParseResult<Op>(new DivisionOp(), position + 1);
    	
    	} else {
    		throw new ParseException("expected * or /; recieved: " + token);
    	}
    }
    
    //parses an expression that contains a * or / operation (example: 3 * 2, or 6 / 3
    public ParseResult<Exp> parseMultiplicativeExp(final int position) throws ParseException {
    	ParseResult<Exp> current = parseDotExp(position);
    	boolean shouldRun = true;
    	
    	while(shouldRun) {
    		try {
    			final ParseResult<Op> multiplicativeOp = parseMultiplicativeOp(current.position);
    			final ParseResult<Exp> anotherDot = parseDotExp(multiplicativeOp.position);
    			current = new ParseResult<Exp>(new OpExp(current.result,
    													multiplicativeOp.result,
    													anotherDot.result),
    											anotherDot.position);
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
    public ParseResult<Op> parseComparisonOp(final int position) throws ParseException{
    	final Token token = getToken(position);
    	
    	if(token instanceof GreaterThanToken) {
    		
    		return new ParseResult<Op>(new GreaterThanOp(), position + 1);
    	
    	}else if(token instanceof LessThanToken) {
    		
    		return new ParseResult<Op>(new LessThanOp(), position + 1);
    	
    	}else {
    		
    		throw new ParseException("expected > or <; received: " + token);
    		
    	}
    }
    
    
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
    public ParseResult<Op> parseEqualsOp(final int position) throws ParseException{
    	final Token token = getToken(position);
    	
    	if(token instanceof EqualsToToken) {
    		
    		return new ParseResult<Op>(new EqualToOp(), position + 1);
    	
    	}else {
    		
    		throw new ParseException("expected ==; recieved: " + token);
    	}
    }
    
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
    
    //returns a parameter list to fill the parenthesis of a call expression
   /* public List<Exp> getParams(final int position) throws ParseException {
    	final List<Exp> paramsList = new ArrayList<Exp>();
    	final Token token = getToken(position);
    	if(token instanceof RightParenToken) {
    		return null;
    	} else {
    		ParseResult<Exp> parameter = parseExp(position);
    		final Token checkCommaToken = getToken(parameter.position);
			paramsList.add(parameter);
			if(checkCommaToken instanceof CommaToken) {
				boolean shouldRun = true;
	        	while(shouldRun) {
	        		try {
	        			parameter = parseExp(parameter.position + 1);
	        			paramsList.add(parameter);
	        		} catch(final ParseException e) {
	        			shouldRun = false;
	        		}
	        	}
	        	
	        	return paramsList;
	        	
			} else {
				return paramsList;
			}
    	}
    }*/
    
    //parses a generic expression
    public ParseResult<Exp> parseExp(final int position) throws ParseException {
    	return parseEqualsExp(position);
    }
   
    //parse stmt
	public ParseResult<Stmt> parseStmt(final int position) throws ParseException {
        final Token token = getToken(position);
		
        if (token instanceof IfToken) {
            assertTokenHereIs(position + 1, new LeftParenToken());
            final ParseResult<Exp> guard = parseExp(position + 2);
            assertTokenHereIs(guard.position, new RightParenToken());
            final ParseResult<Stmt> trueBranch = parseStmt(guard.position + 1);
            assertTokenHereIs(trueBranch.position, new ElseToken());
            final ParseResult<Stmt> falseBranch = parseStmt(trueBranch.position + 1);
            return new ParseResult<Stmt>(new IfStmt(guard.result,
                                                    trueBranch.result,
                                                    falseBranch.result),
                                         falseBranch.position);
        } else if (token instanceof LeftCurlyToken) {
            final List<Stmt> stmts = new ArrayList<Stmt>();
            int curPosition = position + 1;
            boolean shouldRun = true;
            while (shouldRun) {
                try {
                    final ParseResult<Stmt> stmt = parseStmt(curPosition);
                    stmts.add(stmt.result);
                    curPosition = stmt.position;
                } catch (final ParseException e) {
                    shouldRun = false;
                }
            }
            return new ParseResult<Stmt>(new BlockStmt(stmts),
                                         curPosition);
            
        } else if (token instanceof PrintToken) {
            assertTokenHereIs(position + 1, new LeftParenToken());
            final ParseResult<Exp> exp = parseExp(position + 2);
            assertTokenHereIs(exp.position, new RightParenToken());
            assertTokenHereIs(exp.position + 1, new SemiColonToken());
            return new ParseResult<Stmt>(new PrintStmt(exp.result),
                                         exp.position + 2);
            
        } /*else if (token instanceof BreakToken) { //break;
			assertTokenHereIs(position + 1, new SemiColonToken());
			return new ParseResult<Stmt>(new BreakStmt(), position);
		
		} else if (token instanceof ReturnToken) { //return;
			assertTokenHereIs(position + 1, new SemiColonToken());
			return new ParseResult<Stmt>(new ReturnStmt(), position);
		
		} */
		
		else {
            throw new ParseException("expected statement; received: " + token);
        }
    }
	
	//parse program
    public ParseResult<Program> parseProgram(final int position) throws ParseException {
        final ParseResult<Stmt> stmt = parseStmt(position);
        return new ParseResult<Program>(new Program(stmt.result),
                                        stmt.position);
	}
	
	public Program parseProgram() throws ParseException {
        final ParseResult<Program> program = parseProgram(0);
      
        if (program.position == tokens.size()) {
            return program.result;
        } else {
            throw new ParseException("Remaining tokens at end");
        }
    }
}
