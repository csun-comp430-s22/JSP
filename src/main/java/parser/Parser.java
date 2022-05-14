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
    
  //returns a parameter list to fill the parenthesis of a call expression
    public List getParams(final int position) throws ParseException {
    	if(tokens.size() > position) {
			final Token callToken = getToken(position);
			if(callToken instanceof LeftParenToken) {
				final Token typeToken = getToken(position + 1);
				if(typeToken instanceof IntToken || typeToken instanceof BooleanToken ||
				   typeToken instanceof VoidToken || typeToken instanceof StructNameToken) {
					return null;
				} else {
					final List paramsList = new ArrayList();
					ParseResult<Exp> parameter = new ParseResult<Exp>(null, position + 1);
		 			boolean shouldRun = true;
		 	        while(shouldRun) {
		 	        	try {
		 	        		Token commaToken = getToken(parameter.position);
		 	        		if(commaToken instanceof CommaToken) {
		 	        			parameter = parseExp(parameter.position + 1);
			 	        		paramsList.add(parameter);
		 	        		} else {
		 	        			parameter = parseExp(parameter.position);
			 	        		paramsList.add(parameter);
		 	        		}
		 	        	} catch(final ParseException e) {
		 	        		shouldRun = false;
		 	        	}
		 	        }
		 	        assertTokenHereIs(parameter.position, new RightParenToken());
		 	        paramsList.add(parameter.position + 1);
		 	        return paramsList;
				}
	 		} else {
	 			return null;
	 		}
		} else {
			return null;
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
    		if(pointerToken instanceof VarToken) {
    			final ParseResult<Op> pointerOp = parsePointerOp(pointer.position - 2);
    			return new ParseResult<Exp>(new PointerExp(pointerOp.result,
    														pointer.result), 
    										pointer.position);
    		} else {
    			throw new ParseException("expected VarToken; received: " + pointerToken);
    		}
    	} else if(token instanceof AddressToken) {
    		final Token addressToken = getToken(position + 1);
    		final ParseResult<Exp> address = parsePrimaryExp(position + 1);
    		if(addressToken instanceof VarToken || addressToken instanceof FunctNameToken) {
    			final ParseResult<Op> addressOp = parsePointerOp(address.position - 2);
    			return new ParseResult<Exp>(new PointerExp(addressOp.result,
    														address.result), 
    										address.position);
    		} else {
    			throw new ParseException("expected VarToken or FunctNameToken; received: " + addressToken);
    		}
    	} else if(token instanceof VarToken) {
    		final String name = ((VarToken)token).name;
    		List params = getParams(position + 1);
    		if(params != null) {
    			final int callPosition = (Integer) params.get(params.size() - 1);
    			return new ParseResult<Exp>(new FunctPointerCallExp(new VarExp(new Var(name)), params), callPosition);
    		} else {
        		return new ParseResult<Exp>(new VarExp(new Var(name)), position + 1);
    		}
    	} else if(token instanceof FunctNameToken) {
    		final String name = ((FunctNameToken)token).name;
    		List params = getParams(position + 1);
    		if(params != null) {
    			final int callPosition = (Integer) params.get(params.size() - 1);
    			return new ParseResult<Exp>(new FunctCallExp(new FunctNameExp(new FunctName(name)), params), callPosition);
    		} else {
        		return new ParseResult<Exp>(new FunctNameExp(new FunctName(name)), position + 1);
    		}
    	}
    	else if(token instanceof IntegerVariable) {
    		final int value = ((IntegerVariable)token).value;
    		return new ParseResult<Exp>(new IntegerExp(value), position + 1);
    	} else if(token instanceof TrueToken) {
    		return new ParseResult<Exp>(new BooleanLiteralExp(true), position + 1);
    	} else if(token instanceof FalseToken) {
    		return new ParseResult<Exp>(new BooleanLiteralExp(false), position + 1);
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
  
    //parses a generic expression
    public ParseResult<Exp> parseExp(final int position) throws ParseException {
    	return parseEqualsExp(position);
    }
	
	public List getArgs(final int position) throws ParseException {
		final List argsList = new ArrayList();
		if(tokens.size() > position + 1) {
			final Token typeToken = getToken(position); 
			if(typeToken instanceof RightParenToken) {
				argsList.add(position + 1);
				return argsList;
			} else {
				ParseResult<Type> argument = new ParseResult<Type>(null, position);
			 	boolean shouldRun = true;
			 	while(shouldRun) {
			 		try {
			 	        Token commaToken = getToken(argument.position);
			 	        if(commaToken instanceof CommaToken) {
			 	        	argument = parseType(argument.position + 1);
				 	        argsList.add(argument);
			 	        } else {
			 	        	argument = parseType(argument.position);
		 	        		argsList.add(argument);
			 	        }
			 	     } catch(final ParseException e) {
			 	        shouldRun = false;
			 	     }
			 	}
			 	assertTokenHereIs(argument.position, new RightParenToken());
			 	argsList.add(argument.position + 1);
			 	return argsList;
			} 
		} else {
			throw new ParseException("expected arguments or RightParenToken but there are no more tokens");
		}
     }
	
	public ParseResult<Op> parsePointerLhsOp(final int position) throws ParseException{
		return new ParseResult<Op>(new PointerLhsOp(), position + 1);
    }
	
	public ParseResult<Lhs> parsePrimaryLhs(final int position) throws ParseException {
		final Token lhsToken = getToken(position);
		if(lhsToken instanceof MultiplicationToken) {
			final ParseResult<Op> pointerLhsOp = parsePointerLhsOp(position);
			final ParseResult<Lhs> lhs = parsePrimaryLhs(pointerLhsOp.position);
			return new ParseResult<Lhs>(new PointerLhs(pointerLhsOp.result,
													   lhs.result), 
										lhs.position);
		} else if(lhsToken instanceof VarToken) {
			final ParseResult<Exp> var = parsePrimaryExp(position);
			return new ParseResult<Lhs>(new VarLhs(var.result), position + 1);
		} else {
			throw new ParseException("expected primaryLhs; recieved: " + lhsToken);
		}
	}
	
	public ParseResult<Op> parseFieldOp(final int position) throws ParseException{
    	final Token token = getToken(position);
    	
    	if(token instanceof PeriodToken) {
    		return new ParseResult<Op>(new FieldOp(), position + 1);
    	} else {
    		throw new ParseException("expected . token; recieved: " + token);
    	}
    }
	
	public ParseResult<Lhs> parseFieldLhs(final int position) throws ParseException{
		ParseResult<Lhs> current = parsePrimaryLhs(position);
		boolean shouldRun = true;
		
		while(shouldRun) {
			try {
				final ParseResult<Op> fieldOp = parseFieldOp(current.position);
				final ParseResult<Lhs> field = parseLhs(fieldOp.position);
    			current = new ParseResult<Lhs>(new FieldLhs(current.result,
    														fieldOp.result,
    														field.result),
    										   field.position);
			} catch (final ParseException e) {
				shouldRun = false;
			}
		}
    	return current;
    }
	
	public ParseResult<Lhs> parseLhs(final int position) throws ParseException {
    	return parseFieldLhs(position);
    }
	
    public ParseResult<Type> parsePrimaryType(final int position) throws ParseException{
    	final Token type = getToken(position);
    	if(type instanceof IntToken) {
    		return new ParseResult<Type>(new IntType(), position + 1);
    	} else if(type instanceof BooleanToken) {
    		return new ParseResult<Type>(new BoolType(), position + 1);
    	} else if(type instanceof VoidToken) {
    		return new ParseResult<Type>(new VoidType(), position + 1);
    	} else if(type instanceof StructNameToken) {
    		final String name = ((StructNameToken)type).name;
    		return new ParseResult<Type>(new StructNameType(new StructName(name)), position + 1);
    	} else if(type instanceof LeftParenToken) {
    		List argTypes = getArgs(position + 1);
    		final int argsPosition = (Integer) argTypes.get(argTypes.size() - 1);
        	if(tokens.size() > argsPosition + 1) {
           		final Token functionPointerToken = getToken(argsPosition);
           	    if(functionPointerToken instanceof FunctionPointerToken) {
           	    	final ParseResult<Type> returnType = parseType(argsPosition + 1);
           	    	return new ParseResult<Type>(new FunctionPointerType(argTypes, returnType), returnType.position);
           	    } else {
           	    	throw new ParseException("expected special function pointer token; recieved: " + functionPointerToken);
           	    }
           	} else {
           		throw new ParseException("expected special function pointer type; No tokens left after function pointer arguments ");
        	}
    	} else { 
    		throw new ParseException("expected Int, Bool, Void, Pointer, or Struct; recieved: " + type);
    	}
    }
    
    public ParseResult<Op> parsePointerTypeOp(final int position) throws ParseException{
    	final Token token = getToken(position);
    	
    	if(token instanceof MultiplicationToken) {
    		return new ParseResult<Op>(new PointerTypeOp(), position + 1);
    	} else {
    		throw new ParseException("expected *; recieved: " + token);
    	}
    }
    
    public ParseResult<Type> parsePointerType(final int position) throws ParseException{
    	ParseResult<Type> current = parsePrimaryType(position);
    	boolean shouldRun = true;
    	
    	while(shouldRun) {
    		try {
    			final ParseResult<Op> pointerTypeOp = parsePointerTypeOp(current.position);
    			current = new ParseResult<Type>(new PointerType(current.result,
    													pointerTypeOp.result),
    											pointerTypeOp.position);
    		} catch(final ParseException e) {
    			shouldRun = false;
    		}
    	}
    	return current;
    }
    
    public ParseResult<Type> parseType(final int position) throws ParseException{
    	return parsePointerType(position);
    }
    
    public ParseResult<Vardec> parseVardec(final int position) throws ParseException{
		final ParseResult<Type> type = parseType(position);
		final Token token = getToken(type.position);
		if(token instanceof VarToken) {
			final String name = ((VarToken)token).name;
			return new ParseResult<Vardec>(new Vardec(type.result, 
													 new VarExp(new Var(name))), 
										   type.position + 1);
		} else {
			throw new ParseException("expected var; recieved: " + token);
		}
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
          } else if (token instanceof WhileToken){
          	assertTokenHereIs(position + 1, new LeftParenToken());
          	final ParseResult<Exp> guard = parseExp(position + 2);
          	assertTokenHereIs(guard.position, new RightParenToken());
          	final ParseResult<Stmt> stmtBranch = parseStmt(guard.position + 1);
          	return new ParseResult<Stmt>(new WhileStmt(guard.result, 
          											   stmtBranch.result), 
          								 stmtBranch.position);
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
              assertTokenHereIs(curPosition, new RightCurlyToken());
              return new ParseResult<Stmt>(new BlockStmt(stmts), curPosition + 1);
          } else if (token instanceof PrintToken) {
              assertTokenHereIs(position + 1, new LeftParenToken());
              final ParseResult<Exp> exp = parseExp(position + 2);
              assertTokenHereIs(exp.position, new RightParenToken());
              assertTokenHereIs(exp.position + 1, new SemiColonToken());
              return new ParseResult<Stmt>(new PrintStmt(exp.result),
                                           exp.position + 2);
          } else if (token instanceof BreakToken) { //break;
          	assertTokenHereIs(position + 1, new SemiColonToken());
  			return new ParseResult<Stmt>(new BreakStmt(), position + 2);
  		} else if (token instanceof ReturnToken) { //return;
  			if(tokens.size() > position + 1) {
  				final Token semiColonToken = getToken(position + 1);
  				if(semiColonToken instanceof SemiColonToken) {
  					return new ParseResult<Stmt>(new VoidReturnStmt(), position + 2);
  				} else {
  					final ParseResult<Exp> exp = parseExp(position + 1);
  					assertTokenHereIs(exp.position, new SemiColonToken());
  					return new ParseResult<Stmt>(new ReturnStmt(exp.result), exp.position + 1);
  				}
  			} else {
  				throw new ParseException("expected return statement but there are no more tokens");
  			}
  		} else {
  			try {
  				final ParseResult<Exp> exp = parseExp(position);
		  			assertTokenHereIs(exp.position, new SemiColonToken());
		  			return new ParseResult<Stmt>(new ExpStmt(exp.result), exp.position + 1);
  			} catch (final ParseException e) {
  				try {
  					final ParseResult<Vardec> vardec = parseVardec(position);
			  			assertTokenHereIs(vardec.position, new SemiColonToken());
			  			return new ParseResult<Stmt>(new VardecStmt(vardec.result), vardec.position + 1);
  				} catch (final ParseException f) {
  					try {
  						final ParseResult<Lhs> lhs = parseLhs(position);
  			  			assertTokenHereIs(lhs.position, new AssignmentToken());
  			  			final ParseResult<Exp> exp = parseExp(lhs.position + 1);
  			  			assertTokenHereIs(exp.position, new SemiColonToken());
  			  			return new ParseResult<Stmt>(new LhsStmt(lhs.result, exp.result), exp.position + 1);
  					} catch (final ParseException g) {
  						throw new ParseException("expected statement but could not parse");
  					}
  				}
  			}
  		}
    }
  	
  	public ParseResult<Functiondef> parseFunctiondef(final int position) throws ParseException {
        final ParseResult<Type> returnType = parseType(position);
        final Token token = getToken(returnType.position);
        if(token instanceof FunctNameToken) {
        	final ParseResult<Exp> functionname = parsePrimaryExp(returnType.position);
        	assertTokenHereIs(functionname.position, new LeftParenToken());
        	final List<Vardec> parameters = new ArrayList<Vardec>();
        	int curPosition = functionname.position + 1;
        	boolean shouldRun = true;
            while (shouldRun) {
                try {
                    Token commaToken = getToken(curPosition);
		 	        if(commaToken instanceof CommaToken) {
		 	        	final ParseResult<Vardec> param = parseVardec(curPosition + 1);
	                    parameters.add(param.result);
	                    curPosition = param.position;
		 	        } else {
		 	        	final ParseResult<Vardec> param = parseVardec(curPosition);
	                    parameters.add(param.result);
	                    curPosition = param.position;
		 	        }
                } catch (final ParseException e) {
                    shouldRun = false;
                }
            }
            assertTokenHereIs(curPosition, new RightParenToken());
            final ParseResult<Stmt> body = parseStmt(curPosition + 1);
            return new ParseResult<Functiondef>(new Functiondef(returnType.result, 
            														   functionname.result, 
            														   parameters, 
            														   body.result),
                    							body.position);
        } else {
        	throw new ParseException("expected FunctName; recieved: " + token);
        }
	}
  	
  	public ParseResult<Structdec> parseStructdec(final int position) throws ParseException {
  		final Token token = getToken(position);
  		if(token instanceof StructToken) {
  			final Token structNameToken = getToken(position + 1);
  			if(structNameToken instanceof StructNameToken) {
  				//Parse the name of the structure as a type
  				final ParseResult<Type> structName = parseType(position + 1);
  				assertTokenHereIs(structName.position, new LeftCurlyToken());
  				//Checking if the structure has variable declarations
  				final List<Vardec> vardecs = new ArrayList<Vardec>();
  	        	int curPosition = structName.position + 1;
  	        	boolean shouldRunVardecs = true;
  	            while (shouldRunVardecs) {
  	                try {
  	                	final ParseResult<Vardec> singleVardec = parseVardec(curPosition);
			 	        	assertTokenHereIs(singleVardec.position, new SemiColonToken());
	  		                vardecs.add(singleVardec.result);
	  		                curPosition = singleVardec.position + 1;
  	                } catch (final ParseException e) {
  	                    shouldRunVardecs = false;
  	                }
  	            }
  	           
  	            //Checking if the structure has function definitions
  	            final List<Functiondef> functiondefs = new ArrayList<Functiondef>();
	        	boolean shouldRunFunctiondefs = true;
	            while (shouldRunFunctiondefs) {
	                try {
			 	        final ParseResult<Functiondef> singleFunctiondef = parseFunctiondef(curPosition);
		                functiondefs.add(singleFunctiondef.result);
		                curPosition = singleFunctiondef.position;
	                } catch (final ParseException e) {
	                    shouldRunFunctiondefs = false;
	                }
	            }
	            
	            assertTokenHereIs(curPosition, new RightCurlyToken());
  				return new ParseResult<Structdec>(new Structdec(structName.result, vardecs, functiondefs),
  												  curPosition + 1);
  			} else {
  				throw new ParseException("expected structName; recieved: " + structNameToken);
  			} 
  		} else {
  			throw new ParseException("expected struct; recieved: " + token);
  		}
	}
    
  //parse program
    public ParseResult<Program> parseProgram(final int position) throws ParseException {
    	final List<Structdec> structuredecs = new ArrayList<Structdec>();
    	int curPosition = position;
        boolean shouldRunStructuredecs = true;
        while (shouldRunStructuredecs) {
            try {
	 	        final ParseResult<Structdec> structdec = parseStructdec(curPosition);
                structuredecs.add(structdec.result);
                curPosition = structdec.position;
            } catch (final ParseException e) {
                shouldRunStructuredecs = false;
            }
        }
        final List<Functiondef> functiondefs = new ArrayList<Functiondef>();
        final ParseResult<Functiondef> entry = parseFunctiondef(curPosition);
        curPosition = entry.position;
        functiondefs.add(entry.result);
        boolean shouldRunFunctiondefs = true;
        while (shouldRunFunctiondefs) {
            try {
	 	        final ParseResult<Functiondef> funcdef = parseFunctiondef(curPosition);
                functiondefs.add(funcdef.result);
                curPosition = funcdef.position;
            } catch (final ParseException e) {
                shouldRunFunctiondefs = false;
            }
        }
        return new ParseResult<Program>(new Program(structuredecs, 
        											functiondefs),
                                        curPosition);
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