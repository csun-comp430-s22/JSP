package lexer;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TokenizerTest {
	public void assertTokenizes(final String input, final Token[] expected) throws TokenizerException {
		final Tokenizer tokenizer = new Tokenizer(input);
		final List<Token> received = tokenizer.tokenize();
		assertArrayEquals(expected, received.toArray(new Token[received.size()]));
	}

	@Test
	public void testEmptyString() throws TokenizerException {
		assertTokenizes("", new Token[0]);
	}
	
	@Test
	public void testOnlyWhitespace() throws TokenizerException {
		assertTokenizes("     ", new Token[0]);
	}
	
	@Test
	public void testTrueByItself() throws TokenizerException {
		assertTokenizes("true", new Token[] { new TrueToken()});
	}
	
	@Test
	public void testTrueSpaceTrueIsTrueToken() throws TokenizerException {
		assertTokenizes("true true", new Token[] { new TrueToken(), new TrueToken()});
	}
	
	@Test
	public void testFalseByItself() throws TokenizerException {
		assertTokenizes("false", new Token[] { new FalseToken()});
	}
	
	@Test
	public void testIfByItself() throws TokenizerException {
		assertTokenizes("if", new Token[] { new IfToken()});
	}
	
	@Test
	public void testElseByItself() throws TokenizerException {
		assertTokenizes("else", new Token[] { new ElseToken()});
	}
	
	@Test
	public void testBreakByItself() throws TokenizerException {
		assertTokenizes("break", new Token[] { new BreakToken()});
	}
	
	@Test
	public void testReturnByItself() throws TokenizerException {
		assertTokenizes("return", new Token[] { new ReturnToken()});
	}
	
	@Test
	public void testWhileByItself() throws TokenizerException {
		assertTokenizes("while", new Token[] { new WhileToken()});
	}
	
	@Test
	public void testPrintByItself() throws TokenizerException {
		assertTokenizes("print", new Token[] { new PrintToken()});
	}
	
	@Test
	public void testIntByItself() throws TokenizerException {
		assertTokenizes("Int", new Token[] { new IntegerToken()});
	}
	
	@Test
	public void testStringByItself() throws TokenizerException {
		assertTokenizes("String", new Token[] { new StringToken()});
	}
	
	@Test
	public void testBooleanByItself() throws TokenizerException {
		assertTokenizes("Boolean", new Token[] { new BooleanToken()});
	}
	
	@Test(expected = TokenizerException.class)
	public void testInvalid() throws TokenizerException {
		assertTokenizes("$", null);
	}
	
	@Test
	public void testSingleDigitInteger() throws TokenizerException {
		assertTokenizes("1", new Token[] { new IntegerVariable(1) });
	}
	
	
	@Test
	public void testMultiDigitInteger() throws TokenizerException {
		assertTokenizes("123", new Token[] { new IntegerVariable(123) });
	}
	
	@Test
	public void testLeftParen() throws TokenizerException {
		assertTokenizes("(", new Token[] { new LeftParenToken() });
	}
	
	@Test
	public void testRightParen() throws TokenizerException {
		assertTokenizes(")", new Token[] { new RightParenToken() });
	}
	
	@Test
	public void testLeftCurly() throws TokenizerException {
		assertTokenizes("{", new Token[] { new LeftCurlyToken() });
	}
	
	@Test
	public void testRightCurly() throws TokenizerException {
		assertTokenizes("}", new Token[] { new RightCurlyToken() });
	}
	
	@Test
	public void testAdd() throws TokenizerException {
		assertTokenizes("+", new Token[] { new AddToken() });
	}
	
	@Test
	public void testMinus() throws TokenizerException {
		assertTokenizes("-", new Token[] { new MinusToken() });
	}
	
	@Test
	public void testMultiplication() throws TokenizerException {
		assertTokenizes("*", new Token[] { new MultiplicationToken() });
	}
	
	@Test
	public void testDivide() throws TokenizerException {
		assertTokenizes("/", new Token[] { new DivideToken() });
	}
	
	@Test
	public void testSemicolon() throws TokenizerException {
		assertTokenizes(";", new Token[] { new SemicolonToken() });
	}
	
	@Test
	public void testGreaterThan() throws TokenizerException{
		assertTokenizes(">", new Token[] { new GreaterThanToken()});
	}
	
	@Test
	public void testLessThan() throws TokenizerException{
		assertTokenizes("<", new Token[] { new LessThanToken()} );
	}
	
	@Test
	public void testEqualsTo() throws TokenizerException{
		assertTokenizes("==", new Token[] { new EqualsToToken()} );
	}
	
	@Test
	public void testPeriod() throws TokenizerException {
		assertTokenizes(".", new Token[] { new PeriodToken() });
	}
	
	@Test
	public void testComma() throws TokenizerException {
		assertTokenizes(",", new Token[] { new CommaToken() });
	}
	
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}*/

}
