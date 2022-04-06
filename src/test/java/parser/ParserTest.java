package parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import org.junit.Test;

public class ParserTest {
	
	@Test
	public void testPrimaryVariable() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new VariableToken("x"), new RightParenToken()));
		assertEquals(new ParseResult<Exp>(new VariableExp(new Variable("x")),
											1),
				parser.parsePrimaryExp(0));
	}
	
	
	@Test
	public void testPrimaryInteger() throws ParseException {
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new IntegerToken(123)));
		assertEquals(new ParseResult<Exp>(new IntegerExp(123), 1), parser.parsePrimaryExp(0));
	}
	
	 @Test
	 public void testAdditiveOpAdd() throws ParseException {
	    final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new AddToken(), new RightParenToken()));
	    assertEquals(new ParseResult<Op>(new AddOp(), 1),
	             parser.parseAdditiveOp(0));
	}
	
	@Test
	public void testAdditiveOpMinus() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new MinusToken(), new RightParenToken()));
		assertEquals(new ParseResult<Op>(new MinusOp(), 1), parser.parseAdditiveOp(0));
	}
	
	@Test 
	public void testMultiplicativeOpMultiply() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new MultiplicationToken(), new RightParenToken()));
		assertEquals(new ParseResult<Op>(new MultiplicationOp(), 1), parser.parseMultiplicativeOp(0));
	}
	
	@Test
	public void testMultiplicativeOpDivision() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new DivisionToken(), new RightParenToken()));
		assertEquals(new ParseResult<Op>(new DivisionOp(), 1), parser.parseMultiplicativeOp(0));
	}
	@Test
	public void testPrimaryParenth() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new IntegerToken(123), new RightParenToken()));
		assertEquals(new ParseResult<Exp>(new IntegerExp(123), 3), parser.parsePrimaryExp(0));
	}
	
	@Test
	public void testGreaterThanOp() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new GreaterThanToken(), new RightParenToken()));
		assertEquals(new ParseResult<Op>(new GreaterThanOp(), 1), parser.parseComparisonOp(0));
	}
	
	@Test
	public void testLessThanOp() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new LessThanToken(), new RightParenToken()));
		assertEquals(new ParseResult<Op>(new LessThanOp(), 1), parser.parseComparisonOp(0));
	}
	
	@Test
	public void testEqualsToken() throws ParseException{
		final Parser parser = new Parser(Arrays.asList(new LeftParenToken(), new EqualToToken(), new RightParenToken()));
		assertEquals(new ParseResult<Op>(new EqualToOp(), 1), parser.parseComparisonOp(0));
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
    public void testEqualsExpSingleOperator() throws ParseException {
        // 1 == 1
        final Parser parser = new Parser(Arrays.asList(new IntegerToken(1),
                                                       new EqualToToken(),
                                                       new IntegerToken(2)));
        assertEquals(new ParseResult<Exp>(new OpExp(new IntegerExp(1),
                                                    new EqualToOp(),
                                                    new IntegerExp(2)),
                                          1),
                     parser.parseEqualsExp(0));
    }

    @Test
    public void testPrimaryParens() throws ParseException {
        final Parser parser = new Parser(Arrays.asList(new LeftParenToken(),
                                                       new IntegerToken(123),
                                                       new RightParenToken()));
        assertEquals(new ParseResult<Exp>(new IntegerExp(123), 3),
                     parser.parsePrimaryExp(0));
    }
    
   
    @Test
    public void testMultiplicationExpSingleOperator() throws ParseException {
        // 1 * 2
        final Parser parser = new Parser(Arrays.asList(new IntegerToken(1),
                                                       new MultiplicationToken(),
                                                       new IntegerToken(2)));
        assertEquals(new ParseResult<Exp>(new OpExp(new IntegerExp(1),
                                                    new MultiplicationOp(),
                                                    new IntegerExp(2)),
                                          			2),
                     parser.parseMultiplicativeExp(0));
    }
    
    @Test
    public void testDivisionExpSingleOperator() throws ParseException{
    	//2 / 1
    	final Parser parser = new Parser(Arrays.asList(new IntegerToken(1),
                										new DivisionToken(),
                										new IntegerToken(2)));
    		assertEquals(new ParseResult<Exp>(new OpExp(new IntegerExp(1),
    													new DivisionOp(),
    													new IntegerExp(2)),
    													1),
    					parser.parseMultiplicativeExp(0));
    }
    @Test
    public void testAdditiveExpSingleOperator() throws ParseException {
        // 1 + 2
        final Parser parser = new Parser(Arrays.asList(new IntegerToken(1),
                                                       new AddToken(),
                                                       new IntegerToken(2)));
        assertEquals(new ParseResult<Exp>(new OpExp(new IntegerExp(1),
                                                    new AddOp(),
                                                    new IntegerExp(2)),
                                          3),
                     parser.parseAdditiveExp(0));
    }

    @Test
    public void testAdditiveExpMultiOperator() throws ParseException {
        // 1 + 2 - 3 ==> (1 + 2) - 3
        final Parser parser = new Parser(Arrays.asList(new IntegerToken(1),
                                                       new AddToken(),
                                                       new IntegerToken(2),
                                                       new MinusToken(),
                                                       new IntegerToken(3)));
        final Exp expected = new OpExp(new OpExp(new IntegerExp(1),
                                                 new AddOp(),
                                                 new IntegerExp(2)),
                                       new MinusOp(),
                                       new IntegerExp(3));
        assertEquals(new ParseResult<Exp>(expected, 5),
                     parser.parseAdditiveExp(0));
    }

    
    @Test
    public void testGreaterThanSingleOperator() throws ParseException{
    	//x > 4
    	final Parser parser = new Parser(Arrays.asList(new VariableToken("x"),
    													new LessThanToken(),
    													new IntegerToken(4)));
    	final Exp expected = new OpExp(new IntegerExp(1), new LessThanOp(), new IntegerExp(2));
    	assertEquals(new ParseResult<Exp>(expected, 4),
    			parser.parseComparisonExp(0));
    }
    
    @Test
    public void testGreaterThanMultiOperator() throws ParseException{
    	//1 > 8 > 4 ==> (1 > 8) > 4
    	final Parser parser = new Parser(Arrays.asList(new IntegerToken(1),
                										new GreaterThanToken(),
                										new IntegerToken(8),
                										new LessThanToken(),
                										new IntegerToken(4)));
    	final Exp expected = new OpExp(new OpExp(new IntegerExp(1),
    											new GreaterThanOp(),
    											new IntegerExp(8)),
    											new GreaterThanOp(),
    											new IntegerExp(4));
    	assertEquals(new ParseResult<Exp>(expected, 5),
    					parser.parseComparisonExp(0));
    }
    
    @Test
    public void testGreaterThanMixedOperator() throws ParseException{
    	//1 > 4 + 3 ==> 1 > (4 + 3)
    	final Parser parser = new Parser(Arrays.asList(new IntegerToken(1),
    												   new GreaterThanToken(),
    												   new IntegerToken(4),
    												   new AddToken(),
    												   new IntegerToken(3)));
    	final Exp expected = new OpExp(new IntegerExp(1),
                						new GreaterThanOp(),
                						new OpExp(new IntegerExp(4),
                									new AddOp(),
                									new IntegerExp(3)));
    	assertEquals(new ParseResult<Exp>(expected, 7),
    					parser.parseComparisonExp(0));
    }
    
    @Test
    public void testLessThanSingleOperator() throws ParseException {
        // 1 < 2
        final Parser parser = new Parser(Arrays.asList(new IntegerToken(1),
                                                       new LessThanToken(),
                                                       new IntegerToken(2)));
        final Exp expected = new OpExp(new IntegerExp(1),
                                       new LessThanOp(),
                                       new IntegerExp(2));
        assertEquals(new ParseResult<Exp>(expected, 3),
                     parser.parseComparisonExp(0));
    }

    @Test
    public void testLessThanMultiOperator() throws ParseException {
        // 1 < 2 < 3 ==> (1 < 2) < 3
        final Parser parser = new Parser(Arrays.asList(new IntegerToken(1),
                                                       new LessThanToken(),
                                                       new IntegerToken(2),
                                                       new LessThanToken(),
                                                       new IntegerToken(3)));
        final Exp expected = new OpExp(new OpExp(new IntegerExp(1),
                                                 new LessThanOp(),
                                                 new IntegerExp(2)),
                                       new LessThanOp(),
                                       new IntegerExp(3));
        assertEquals(new ParseResult<Exp>(expected, 5),
                     parser.parseComparisonExp(0));
    }

    @Test
    public void testLessThanMixedOperator() throws ParseException {
        // 1 < 2 + 3 ==> 1 < (2 + 3)
        final Parser parser = new Parser(Arrays.asList(new IntegerToken(1),
                                                       new LessThanToken(),
                                                       new IntegerToken(2),
                                                       new AddToken(),
                                                       new IntegerToken(3)));
        final Exp expected = new OpExp(new IntegerExp(1),
                                       new LessThanOp(),
                                       new OpExp(new IntegerExp(2),
                                                 new AddOp(),
                                                 new IntegerExp(3)));
        assertEquals(new ParseResult<Exp>(expected, 5),
                     parser.parseComparisonExp(0));
    }
    
    //Modify test later, tests all statements
    @Test
    public void testIfStmts() throws ParseException{
    	final Parser parser = new Parser(Arrays.asList(new IfToken(), 
    												   new LeftParenToken(), 
    												   new IntegerToken(1),
    												   new RightParenToken(),
    												   new PrintToken(),
    												   new LeftParenToken(),
    												   new IntegerToken(1),
    												   new RightParenToken(),
    												   new SemiColonToken()));
        final Stmt expected = new IfStmt(new IfExp(), new PrintStmt(new IntegerExp(1)), 
        															new PrintStmt(new IntegerExp(1)));
        assertNotEquals(new ParseResult<Stmt>(expected, 3), parser.parseStmt(0));
    }
   
}