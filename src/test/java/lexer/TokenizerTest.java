package comp430;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TokenizerTest {

	@Test
	public void testEmptyString() throws TokenizerException {
		Tokenizer tokenizer = new Tokenizer("");
		List<Token> tokens = tokenizer.tokenize();
		assertEquals(0, tokens.size());
	}
	
	@Test
	public void testOnlyWhitespace() throws TokenizerException {
		Tokenizer tokenizer = new Tokenizer("     ");
		List<Token> tokens = tokenizer.tokenize();
		assertEquals(0, tokens.size());
	}
	
	@Test
	public void testTrueByItself() throws TokenizerException {
		Tokenizer tokenizer = new Tokenizer("true");
		List<Token> tokens = tokenizer.tokenize();
		assertEquals(1, tokens.size());
		Token trueToken = tokens.get(0);
		assertTrue(trueToken instanceof TrueToken);
	}
	
	@Test
	public void testFalseByItself() throws TokenizerException {
		Tokenizer tokenizer = new Tokenizer("false");
		List<Token> tokens = tokenizer.tokenize();
		assertEquals(1, tokens.size());
		Token falseToken = tokens.get(0);
		assertTrue(falseToken instanceof FalseToken);
	}
	
	@Test
	public void testIfByItself() throws TokenizerException {
		Tokenizer tokenizer = new Tokenizer("if");
		List<Token> tokens = tokenizer.tokenize();
		assertEquals(1, tokens.size());
		Token ifToken = tokens.get(0);
		assertTrue(ifToken instanceof IfToken);
	}
	
	@Test
	public void testElseByItself() throws TokenizerException {
		Tokenizer tokenizer = new Tokenizer("else");
		List<Token> tokens = tokenizer.tokenize();
		assertEquals(1, tokens.size());
		Token elseToken = tokens.get(0);
		assertTrue(elseToken instanceof ElseToken);
	}
	
	@Test
	public void testBreakByItself() throws TokenizerException {
		Tokenizer tokenizer = new Tokenizer("break");
		List<Token> tokens = tokenizer.tokenize();
		assertEquals(1, tokens.size());
		Token breakToken = tokens.get(0);
		assertTrue(breakToken instanceof BreakToken);
	}
	
	@Test
	public void testReturnByItself() throws TokenizerException {
		Tokenizer tokenizer = new Tokenizer("return");
		List<Token> tokens = tokenizer.tokenize();
		assertEquals(1, tokens.size());
		Token returnToken = tokens.get(0);
		assertTrue(returnToken instanceof ReturnToken);
	}
	
	@Test
	public void testWhileByItself() throws TokenizerException {
		Tokenizer tokenizer = new Tokenizer("while");
		List<Token> tokens = tokenizer.tokenize();
		assertEquals(1, tokens.size());
		Token whileToken = tokens.get(0);
		assertTrue(whileToken instanceof WhileToken);
	}
	
	@Test
	public void testPrintByItself() throws TokenizerException {
		Tokenizer tokenizer = new Tokenizer("print");
		List<Token> tokens = tokenizer.tokenize();
		assertEquals(1, tokens.size());
		Token printToken = tokens.get(0);
		assertTrue(printToken instanceof PrintToken);
	}
	
	@Test
	public void testIntByItself() throws TokenizerException {
		Tokenizer tokenizer = new Tokenizer("Int");
		List<Token> tokens = tokenizer.tokenize();
		assertEquals(1, tokens.size());
		Token intToken = tokens.get(0);
		assertTrue(intToken instanceof IntegerToken);
	}
	
	@Test
	public void testStringByItself() throws TokenizerException {
		Tokenizer tokenizer = new Tokenizer("String");
		List<Token> tokens = tokenizer.tokenize();
		assertEquals(1, tokens.size());
		Token stringToken = tokens.get(0);
		assertTrue(stringToken instanceof StringToken);
	}
	
	@Test
	public void testBooleanByItself() throws TokenizerException {
		Tokenizer tokenizer = new Tokenizer("Boolean");
		List<Token> tokens = tokenizer.tokenize();
		assertEquals(1, tokens.size());
		Token booleanToken = tokens.get(0);
		assertTrue(booleanToken instanceof BooleanToken);
	}
	
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}*/

}
