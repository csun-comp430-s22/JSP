package parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ParserTest {
	public List<Token> createTokenArrayList(final Token token) {
		List<Token> singleTokenList = new ArrayList<Token>();
		singleTokenList.add(token);
		return singleTokenList;
	}
	
	@Test
	public void testLeftCurlyToken() throws ParseException{
		final List<Token> singleLeftCurlyToken = createTokenArrayList(new LeftCurlyToken());
		final Parser parser = new Parser(singleLeftCurlyToken);
		assertEquals(new LeftCurlyToken(), parser.getToken(0));
	}
	
	@Test
	public void testIdentifierToken() throws ParseException{
		final List<Token> singleIdentifierToken = createTokenArrayList(new IdentifierToken("x"));
		final Parser parser = new Parser(singleIdentifierToken);
		assertEquals(new IdentifierToken("x"), parser.getToken(0));
	}
	
	@Test
	public void testIntegerToken() throws ParseException{
		final List<Token> singleIntegerToken = createTokenArrayList(new IntegerVariable(123));
		final Parser parser = new Parser(singleIntegerToken);
		assertEquals(new IntegerVariable(123), parser.getToken(0));
	}
	
	@Test
	public void testPrimaryIdentifierPrimaryExp() throws ParseException{
		final List<Token> singleIdentifierToken = createTokenArrayList(new IdentifierToken("x"));
		final Parser parser = new Parser(singleIdentifierToken);
		assertEquals(new ParseResult<Exp>(new IdentifierExp(new Identifier("x")), 1), parser.parsePrimaryExp(0));
	}
	
	@Test
	public void testPrimaryIdentifierExp() throws ParseException{
		final List<Token> singleIdentifierToken = createTokenArrayList(new IdentifierToken("y"));
		final Parser parser = new Parser(singleIdentifierToken);
		assertEquals(new ParseResult<Exp>(new IdentifierExp(new Identifier("y")), 1), parser.parseExp(0));
	}
	
	
	@Test
	public void testPrimaryInteger() throws ParseException {
		final List<Token> singleIntegerToken = createTokenArrayList(new IntegerVariable(123));
		final Parser parser = new Parser(singleIntegerToken);
		assertEquals(new ParseResult<Exp>(new IntegerExp(123), 1), parser.parsePrimaryExp(0));
	}
	
	@Test
	public void testFirstPositionPrimaryParenth() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new IntegerVariable(123), new RightParenToken()));
		assertEquals(new LeftParenToken(), parser.getToken(0));
	}
	
	@Test
	public void testSecondPositionPrimaryParenth() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new IntegerVariable(123), new RightParenToken()));
		assertEquals(new IntegerVariable(123), parser.getToken(1));
	}
	
	@Test
	public void testPrimaryParenthInt() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new IntegerVariable(123), new RightParenToken()));
		assertEquals(new ParseResult<Exp>(new IntegerExp(123), 3), parser.parsePrimaryExp(0));
	}
	
	@Test
	public void testPrimaryParenthIdent() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new IdentifierToken("x"), new RightParenToken()));
		assertEquals(new ParseResult<Exp>(new IdentifierExp(new Identifier("x")), 3), parser.parsePrimaryExp(0));
	}
	
	 @Test
	 public void testAdditiveOpAdd() throws ParseException {
		final List<Token> singleAddToken = createTokenArrayList(new AddToken());
		final Parser parser = new Parser(singleAddToken);
	    assertEquals(new ParseResult<Op>(new AddOp(), 1), parser.parseAdditiveOp(0));
	}
	
	@Test
	public void testAdditiveOpMinus() throws ParseException{
		final List<Token> singleMinusToken = createTokenArrayList(new MinusToken());
		final Parser parser = new Parser(singleMinusToken);
		assertEquals(new ParseResult<Op>(new MinusOp(), 1), parser.parseAdditiveOp(0));
	}
	
	@Test 
	public void testMultiplicativeOpMultiply() throws ParseException{
		final List<Token> singleMultiplicationToken = createTokenArrayList(new MultiplicationToken());
		final Parser parser = new Parser(singleMultiplicationToken);
		assertEquals(new ParseResult<Op>(new MultiplicationOp(), 1), parser.parseMultiplicativeOp(0));
	}
	
	@Test
	public void testMultiplicativeOpDivision() throws ParseException{
		final List<Token> singleDivideToken = createTokenArrayList(new DivideToken());
		final Parser parser = new Parser(singleDivideToken);
		assertEquals(new ParseResult<Op>(new DivisionOp(), 1), parser.parseMultiplicativeOp(0));
	}
	
	@Test
	public void testGreaterThanOp() throws ParseException{
		final List<Token> singleGreaterThanToken = createTokenArrayList(new GreaterThanToken());
		final Parser parser = new Parser(singleGreaterThanToken);
		assertEquals(new ParseResult<Op>(new GreaterThanOp(), 1), parser.parseComparisonOp(0));
	}
	
	@Test
	public void testLessThanOp() throws ParseException{
		final List<Token> singleLessThanToken = createTokenArrayList(new LessThanToken());
		final Parser parser = new Parser(singleLessThanToken);
		assertEquals(new ParseResult<Op>(new LessThanOp(), 1), parser.parseComparisonOp(0));
	}
	
	@Test
	public void testEqualToOp() throws ParseException{
		final List<Token> singleEqualsToToken = createTokenArrayList(new EqualsToToken());
		final Parser parser = new Parser(singleEqualsToToken);
		assertEquals(new ParseResult<Op>(new EqualToOp(), 1), parser.parseEqualsOp(0));
	}
	
	@Test
	public void testDotOp() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new IntegerVariable(1),
													   new PeriodToken(),
													   new IntegerVariable(1)));
		final Exp expected = new OpExp(new IntegerExp(1),
									   new DotOp(),
									   new IntegerExp(1));
		assertEquals(new ParseResult<Exp>(expected, 3), parser.parseDotExp(0));
	}
	
	@Test
	public void testPointerOp() throws ParseException{
		final List<Token> singlePointerToken = createTokenArrayList(new MultiplicationToken());
		final Parser parser = new Parser(singlePointerToken);
		assertEquals(new ParseResult<Op>(new PointerOp(), 1), parser.parsePointerOp(0));
	}
	
	@Test
	public void testAddressOp() throws ParseException{
		final List<Token> singleAddressToken = createTokenArrayList(new AddressToken());
		final Parser parser = new Parser(singleAddressToken);
		assertEquals(new ParseResult<Op>(new AddressOp(), 1), parser.parsePointerOp(0));
	}
	
	@Test
	public void testPointerExpPointer() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new MultiplicationToken(),
				   									   new IdentifierToken("x")));
		final Exp expected = new PointerExp(new PointerOp(),
									   		new IdentifierExp(new Identifier("x")));
		assertEquals(new ParseResult<Exp>(expected, 2), parser.parsePrimaryExp(0));
	}
	
	@Test
	public void testPointerExpAddress() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new AddressToken(),
				   									   new IdentifierToken("x")));
		final Exp expected = new PointerExp(new AddressOp(),
									   		new IdentifierExp(new Identifier("x")));
		assertEquals(new ParseResult<Exp>(expected, 2), parser.parsePrimaryExp(0));
	}
	
	@Test
	public void testCallExpEmpty() throws ParseException{
		List params = new ArrayList();
		params.add(3);
		final Parser parser = new Parser(Arrays.asList(new IdentifierToken("x"),
				   									   new LeftParenToken(),
				   									   new RightParenToken()));
		final Exp expected = new CallExp(new IdentifierExp(new Identifier("x")),
										 params);
		assertEquals(new ParseResult<Exp>(expected, 3), parser.parsePrimaryExp(0));
	}
	
	@Test
	public void testCallExpIdentifierParam() throws ParseException{
		List params = new ArrayList();
		params.add(new ParseResult<Exp>(new IdentifierExp(new Identifier("y")), 3));
		params.add(4);
		final Parser parser = new Parser(Arrays.asList(new IdentifierToken("x"),
				   									   new LeftParenToken(),
				   									   new IdentifierToken("y"),
				   									   new RightParenToken()));
		final Exp expected = new CallExp(new IdentifierExp(new Identifier("x")),
										 params);
		assertEquals(new ParseResult<Exp>(expected, 4), parser.parsePrimaryExp(0));
	}
	
	@Test
	public void testCallExpMultiParam() throws ParseException{
		List params = new ArrayList();
		params.add(new ParseResult<Exp>(new IdentifierExp(new Identifier("y")), 3));
		params.add(new ParseResult<Exp>(new IntegerExp(1), 5));
		params.add(6);
		final Parser parser = new Parser(Arrays.asList(new IdentifierToken("x"),
				   									   new LeftParenToken(),
				   									   new IdentifierToken("y"),
				   									   new CommaToken(),
				   									   new IntegerVariable(1),
				   									   new RightParenToken()));
		final Exp expected = new CallExp(new IdentifierExp(new Identifier("x")),
										 params);
		assertEquals(new ParseResult<Exp>(expected, 6), parser.parsePrimaryExp(0));
	}
	
	
    @Test
    public void testEqualsOpExp() {
        // 1 + 1 == 1 +  1
        final OpExp first = new OpExp(new IntegerExp(1),
                                      new AddOp(),
                                      new IntegerExp(1));
        final OpExp second = new OpExp(new IntegerExp(1),
                                      new AddOp(),
                                      new IntegerExp(1));
        assertEquals(first, second);
    }
    
    @Test
    public void testEqualsMultiplicationOpExp() {
        // 1 * 1 == 1 * 1
        final OpExp first = new OpExp(new IntegerExp(1),
                                      new MultiplicationOp(),
                                      new IntegerExp(1));
        final OpExp second = new OpExp(new IntegerExp(1),
                                      new MultiplicationOp(),
                                      new IntegerExp(1));
        assertEquals(first, second);
    }
    
    @Test
    public void testEqualsExpSingleOperator() throws ParseException {
        // 1 == 2
        final Parser parser = new Parser(Arrays.asList(new IntegerVariable(1),
                                                       new EqualsToToken(),
                                                       new IntegerVariable(2)));
        assertEquals(new ParseResult<Exp>(new OpExp(new IntegerExp(1),
                                                    new EqualToOp(),
                                                    new IntegerExp(2)),
                                          3),
                     parser.parseEqualsExp(0));
    }
    
    
   
    @Test
    public void testMultiplicationExpSingleOperator() throws ParseException {
        // 1 * 2
        final Parser parser = new Parser(Arrays.asList(new IntegerVariable(1),
                                                       new MultiplicationToken(),
                                                       new IntegerVariable(2)));
        assertEquals(new ParseResult<Exp>(new OpExp(new IntegerExp(1),
                                                    new MultiplicationOp(),
                                                    new IntegerExp(2)),
                                          3),
                     parser.parseMultiplicativeExp(0));
    }
    
    @Test
    public void testDivisionExpSingleOperator() throws ParseException{
    	// 2 / 1
    	final Parser parser = new Parser(Arrays.asList(new IntegerVariable(1),
                										new DivideToken(),
                										new IntegerVariable(2)));
    		assertEquals(new ParseResult<Exp>(new OpExp(new IntegerExp(1),
    													new DivisionOp(),
    													new IntegerExp(2)),
    										  3),
    					parser.parseMultiplicativeExp(0));
    }
    
    @Test
    public void testAdditiveExpSingleOperator() throws ParseException {
        // 1 + 2
        final Parser parser = new Parser(Arrays.asList(new IntegerVariable(1),
                                                       new AddToken(),
                                                       new IntegerVariable(2)));
        assertEquals(new ParseResult<Exp>(new OpExp(new IntegerExp(1),
                                                    new AddOp(),
                                                    new IntegerExp(2)),
                                          3),
                     parser.parseAdditiveExp(0));
    }

    @Test
    public void testAdditiveExpMultiOperator() throws ParseException {
        // 1 + 2 - 3 ==> (1 + 2) - 3
        final Parser parser = new Parser(Arrays.asList(new IntegerVariable(1),
                                                       new AddToken(),
                                                       new IntegerVariable(2),
                                                       new MinusToken(),
                                                       new IntegerVariable(3)));
        final Exp expected = new OpExp(new OpExp(new IntegerExp(1),
                                                 new AddOp(),
                                                 new IntegerExp(2)),
                                       new MinusOp(),
                                       new IntegerExp(3));
        assertEquals(new ParseResult<Exp>(expected, 5), parser.parseAdditiveExp(0));
    }

    
    @Test
    public void testGreaterThanSingleOperator() throws ParseException{
    	// x > 4
    	final Parser parser = new Parser(Arrays.asList(new IdentifierToken("x"),
    													new LessThanToken(),
    													new IntegerVariable(4)));
    	final Exp expected = new OpExp(new IdentifierExp(new Identifier("x")), new LessThanOp(), new IntegerExp(4));
    	assertEquals(new ParseResult<Exp>(expected, 3), parser.parseComparisonExp(0));
    }
    
    @Test
    public void testGreaterThanMultiOperator() throws ParseException{
    	//1 > 8 > 4 ==> (1 > 8) > 4
    	final Parser parser = new Parser(Arrays.asList(new IntegerVariable(1),
                										new GreaterThanToken(),
                										new IntegerVariable(8),
                										new GreaterThanToken(),
                										new IntegerVariable(4)));
    	final Exp expected = new OpExp(new OpExp(new IntegerExp(1),
    											new GreaterThanOp(),
    											new IntegerExp(8)),
    											new GreaterThanOp(),
    											new IntegerExp(4));
    	assertEquals(new ParseResult<Exp>(expected, 5), parser.parseComparisonExp(0));
    }
    
    @Test
    public void testGreaterThanMixedOperator() throws ParseException{
    	//1 > 4 + 3 ==> 1 > (4 + 3)
    	final Parser parser = new Parser(Arrays.asList(new IntegerVariable(1),
    												   new GreaterThanToken(),
    												   new IntegerVariable(4),
    												   new AddToken(),
    												   new IntegerVariable(3)));
    	final Exp expected = new OpExp(new IntegerExp(1),
                						new GreaterThanOp(),
                						new OpExp(new IntegerExp(4),
                									new AddOp(),
                									new IntegerExp(3)));
    	assertEquals(new ParseResult<Exp>(expected, 5), parser.parseComparisonExp(0));
    }
    
    @Test
    public void testLessThanSingleOperator() throws ParseException {
        // 1 > 2
        final Parser parser = new Parser(Arrays.asList(new IntegerVariable(1),
                                                       new LessThanToken(),
                                                       new IntegerVariable(2)));
        final Exp expected = new OpExp(new IntegerExp(1),
                                       new LessThanOp(),
                                       new IntegerExp(2));
        assertEquals(new ParseResult<Exp>(expected, 3), parser.parseComparisonExp(0));
    }

    @Test
    public void testLessThanMultiOperator() throws ParseException {
        // 1 < 2 < 3 ==> (1 < 2) < 3
        final Parser parser = new Parser(Arrays.asList(new IntegerVariable(1),
                                                       new LessThanToken(),
                                                       new IntegerVariable(2),
                                                       new LessThanToken(),
                                                       new IntegerVariable(3)));
        final Exp expected = new OpExp(new OpExp(new IntegerExp(1),
                                                 new LessThanOp(),
                                                 new IntegerExp(2)),
                                       new LessThanOp(),
                                       new IntegerExp(3));
        assertEquals(new ParseResult<Exp>(expected, 5), parser.parseComparisonExp(0));
    }

    @Test
    public void testLessThanMixedOperator() throws ParseException {
        // 1 < 2 + 3 ==> 1 < (2 + 3)
        final Parser parser = new Parser(Arrays.asList(new IntegerVariable(1),
                                                       new LessThanToken(),
                                                       new IntegerVariable(2),
                                                       new AddToken(),
                                                       new IntegerVariable(3)));
        final Exp expected = new OpExp(new IntegerExp(1),
                                       new LessThanOp(),
                                       new OpExp(new IntegerExp(2),
                                                 new AddOp(),
                                                 new IntegerExp(3)));
        assertEquals(new ParseResult<Exp>(expected, 5), parser.parseComparisonExp(0));
    }
    
    @Test
	public void testIntType() throws ParseException{
		final List<Token> singleIntToken = createTokenArrayList(new IntToken());
		final Parser parser = new Parser(singleIntToken);
		assertEquals(new ParseResult<Type>(new IntType(), 1), parser.parseType(0));
	}
    
    @Test
	public void testBoolType() throws ParseException{
		final List<Token> singleBooleanToken = createTokenArrayList(new BooleanToken());
		final Parser parser = new Parser(singleBooleanToken);
		assertEquals(new ParseResult<Type>(new BoolType(), 1), parser.parseType(0));
	}
    
    @Test
	public void testVoidType() throws ParseException{
		final List<Token> singleVoidToken = createTokenArrayList(new VoidToken());
		final Parser parser = new Parser(singleVoidToken);
		assertEquals(new ParseResult<Type>(new VoidType(), 1), parser.parseType(0));
	}
    
    @Test
	public void testStructNameType() throws ParseException{
		final List<Token> singleStructNameToken = createTokenArrayList(new StructNameToken("x"));
		final Parser parser = new Parser(singleStructNameToken);
		assertEquals(new ParseResult<Type>(new StructNameType(new StructName("x")), 1), parser.parseType(0));
	}
    
    @Test
	public void testPointerTypeInt() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new IntToken(),
				   									   new MultiplicationToken()));
		final Type expected = new PointerType(new IntType(),
									   		new PointerTypeOp());
		assertEquals(new ParseResult<Type>(expected, 2), parser.parsePointerType(0));
	}
    
    @Test
	public void testFunctionPointerTypeNoArgs() throws ParseException{
    	List args = new ArrayList();
		args.add(2);
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(),
				   									   new RightParenToken(),
				   									   new FunctionPointerToken(),
				   									   new IntToken()));
		final Type expected = new FunctionPointerType(args,
									   				  new ParseResult<Type>(new IntType(), 4));
		assertEquals(new ParseResult<Type>(expected, 4), parser.parsePrimaryType(0));
	}
    
    @Test
	public void testFunctionPointerTypeVoidReturn() throws ParseException{
    	List args = new ArrayList();
		args.add(2);
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(),
				   									   new RightParenToken(),
				   									   new FunctionPointerToken(),
				   									   new VoidToken()));
		final Type expected = new FunctionPointerType(args,
									   				  new ParseResult<Type>(new VoidType(), 4));
		assertEquals(new ParseResult<Type>(expected, 4), parser.parsePrimaryType(0));
	}
    
    @Test
    public void testFunctionPointerTypeIntArg() throws ParseException{
    	List args = new ArrayList();
    	args.add(new ParseResult<Type>(new IntType(), 2));
    	args.add(3);
    	final Parser parser = new Parser(Arrays.asList(new LeftParenToken(),
    												   new IntToken(),
    												   new RightParenToken(),
    												   new FunctionPointerToken(),
    												   new IntToken()));
    	final Type expected = new FunctionPointerType(args,
    												  new ParseResult<Type>(new IntType(), 5));
    	assertEquals(new ParseResult<Type>(expected, 5), parser.parsePrimaryType(0));
    }
    
    @Test
    public void testFunctionPointerTypeMultiArg() throws ParseException{
    	List args = new ArrayList();
    	args.add(new ParseResult<Type>(new IntType(), 2));
    	args.add(new ParseResult<Type>(new BoolType(), 4));
    	args.add(5);
    	final Parser parser = new Parser(Arrays.asList(new LeftParenToken(),
    												   new IntToken(),
    												   new CommaToken(),
    												   new BooleanToken(),
    												   new RightParenToken(),
    												   new FunctionPointerToken(),
    												   new VoidToken()));
    	final Type expected = new FunctionPointerType(args,
    												  new ParseResult<Type>(new VoidType(), 7));
    	assertEquals(new ParseResult<Type>(expected, 7), parser.parsePrimaryType(0));
    }
    
    @Test
    public void testIfStmts() throws ParseException{
    	//if(1) print(x); else x < 3;
    	final Parser parser = new Parser(Arrays.asList(new IfToken(), 
    												   new LeftParenToken(), 
    												   new IntegerVariable(1),
    												   new RightParenToken(),
    												   new PrintToken(),
    												   new LeftParenToken(),
    												   new IdentifierToken("x"),
    												   new RightParenToken(),
    												   new SemiColonToken(),
    												   new ElseToken(),
    												   new IdentifierToken("x"),
    												   new LessThanToken(),
    												   new IntegerVariable(3),
    												   new SemiColonToken()));
        final Stmt expected = new IfStmt(new IntegerExp(1), 
        								 new PrintStmt(new IdentifierExp(new Identifier("x"))), 
        								 new ExpStmt(new OpExp(new IdentifierExp(new Identifier("x")),
        										   			   new LessThanOp(),
        										   			   new IntegerExp(3)))
        								 );
        assertEquals(new ParseResult<Stmt>(expected, 14), parser.parseStmt(0));
    }
    
    @Test
    public void testWhileStmts() throws ParseException{
    	//while(x < 4) print(2);
    	final Parser parser = new Parser(Arrays.asList(new WhileToken(),
    												   new LeftParenToken(),
    												   new IdentifierToken("x"),
    												   new LessThanToken(),
    												   new IntegerVariable(4),
    												   new RightParenToken(),
    												   new PrintToken(),
    												   new LeftParenToken(),
    												   new IntegerVariable(2),
    												   new RightParenToken(),
    												   new SemiColonToken()));
        final Stmt expected = new WhileStmt(new OpExp(new IdentifierExp(new Identifier("x")),
        											  new LessThanOp(),
        											  new IntegerExp(4)),
        									new PrintStmt(new IntegerExp(2)));
        assertEquals(new ParseResult<Stmt>(expected, 11), parser.parseStmt(0));
    }
    
    @Test
    public void testSingleBlockStmts() throws ParseException{
    	//{ 1 - 2; }
    	List<Stmt> stmts = new ArrayList();
    	stmts.add(new ParseResult<Stmt>(new ExpStmt(new OpExp(new IntegerExp(1),
    													  new MinusOp(),
    													  new IntegerExp(2))), 
    									3).result);
    	final Parser parser = new Parser(Arrays.asList(new LeftCurlyToken(),
    												   new IntegerVariable(1),
    												   new MinusToken(),
    												   new IntegerVariable(2),
    												   new SemiColonToken(),
    												   new RightCurlyToken()));
        final Stmt expected = new BlockStmt(stmts);
        assertEquals(new ParseResult<Stmt>(expected, 6), parser.parseStmt(0));
    }
    
    @Test
    public void testPrintStmts() throws ParseException{
    	//print((x + 1));
    	final Parser parser = new Parser(Arrays.asList(new PrintToken(),
    												   new LeftParenToken(),
    												   new LeftParenToken(),
    												   new IdentifierToken("x"),
    												   new AddToken(),
    												   new IntegerVariable(1),
    												   new RightParenToken(),
    												   new RightParenToken(),
    												   new SemiColonToken()));
        final Stmt expected = new PrintStmt(new OpExp(new IdentifierExp(new Identifier("x")),
        											  new AddOp(),
        											  new IntegerExp(1)));
        assertEquals(new ParseResult<Stmt>(expected, 9), parser.parseStmt(0));
    }
    
    @Test
    public void testBreakStmts() throws ParseException{
    	//break;
    	final Parser parser = new Parser(Arrays.asList(new BreakToken(), 
    												   new SemiColonToken()));
        final Stmt expected = new BreakStmt();
        assertEquals(new ParseResult<Stmt>(expected, 2), parser.parseStmt(0));
    }
    
    @Test
    public void testVoidReturnStmts() throws ParseException{
    	//return;
    	final Parser parser = new Parser(Arrays.asList(new ReturnToken(), 
    												   new SemiColonToken()));
        final Stmt expected = new VoidReturnStmt();
        assertEquals(new ParseResult<Stmt>(expected, 2), parser.parseStmt(0));
    }
    
    @Test
    public void testReturnStmts() throws ParseException{
    	//return 1;
    	final Parser parser = new Parser(Arrays.asList(new ReturnToken(),
    												   new IntegerVariable(1),
    												   new SemiColonToken()));
        final Stmt expected = new ReturnStmt(new IntegerExp(1));
        assertEquals(new ParseResult<Stmt>(expected, 3), parser.parseStmt(0));
    }
    
    @Test
    public void testExpStmts() throws ParseException{
    	//x;
    	final Parser parser = new Parser(Arrays.asList(new IdentifierToken("x"), 
    												   new SemiColonToken()));
        final Stmt expected = new ExpStmt(new IdentifierExp(new Identifier("x")));
        assertEquals(new ParseResult<Stmt>(expected, 2), parser.parseStmt(0));
    }  
}