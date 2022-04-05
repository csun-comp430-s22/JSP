package parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ParserTest {
    @Test
    public void testEqualsOpExp() {
        // 1 + 1 == 1 + 1
        final OpExp first = new OpExp(new IntegerExp(1),
                                      new AddOp(),
                                      new IntegerExp(1));
        final OpExp second = new OpExp(new IntegerExp(1),
                                      new AddOp(),
                                      new IntegerExp(1));
        assertEquals(first, second);
    }

    @Test
    public void testPrimaryParens() throws ParseException {
        final Parser parser = new Parser(Arrays.asList(new LeftParenToken(),
                                                       new VariableToken(123),
                                                       new RightParenToken()));
        assertEquals(new ParseResult<Exp>(new IntegerExp(123), 3),
                     parser.parsePrimaryExp(0));
    }

    @Test
    public void testAdditiveExpSingleOperator() throws ParseException {
        // 1 + 2
        final Parser parser = new Parser(Arrays.asList(new VariableToken(1),
                                                       new AddToken(),
                                                       new VariableToken(2)));
        assertEquals(new ParseResult<Exp>(new OpExp(new IntegerExp(1),
                                                    new AddOp(),
                                                    new IntegerExp(2)),
                                          3),
                     parser.parseAdditiveExp(0));
    }

    @Test
    public void testAdditiveExpMultiOperator() throws ParseException {
        // 1 + 2 - 3 ==> (1 + 2) - 3
        final Parser parser = new Parser(Arrays.asList(new VariableToken(1),
                                                       new AddToken(),
                                                       new VariableToken(2),
                                                       new MinusToken(),
                                                       new VariableToken(3)));
        final Exp expected = new OpExp(new OpExp(new IntegerExp(1),
                                                 new AddOp(),
                                                 new IntegerExp(2)),
                                       new MinusOp(),
                                       new IntegerExp(3));
        assertEquals(new ParseResult<Exp>(expected, 5),
                     parser.parseAdditiveExp(0));
    }

    @Test
    public void testLessThanSingleOperator() throws ParseException {
        // 1 < 2
        final Parser parser = new Parser(Arrays.asList(new VariableToken(1),
                                                       new LessThanToken(),
                                                       new VariableToken(2)));
        final Exp expected = new OpExp(new IntegerExp(1),
                                       new LessThanOp(),
                                       new IntegerExp(2));
        assertEquals(new ParseResult<Exp>(expected, 3),
                     parser.parseLessThanExp(0));
    }

    @Test
    public void testLessThanMultiOperator() throws ParseException {
        // 1 < 2 < 3 ==> (1 < 2) < 3
        final Parser parser = new Parser(Arrays.asList(new VariableToken(1),
                                                       new LessThanToken(),
                                                       new VariableToken(2),
                                                       new LessThanToken(),
                                                       new VariableToken(3)));
        final Exp expected = new OpExp(new OpExp(new IntegerExp(1),
                                                 new LessThanOp(),
                                                 new IntegerExp(2)),
                                       new LessThanOp(),
                                       new IntegerExp(3));
        assertEquals(new ParseResult<Exp>(expected, 5),
                     parser.parseLessThanExp(0));
    }

    @Test
    public void testLessThanMixedOperator() throws ParseException {
        // 1 < 2 + 3 ==> 1 < (2 + 3)
        final Parser parser = new Parser(Arrays.asList(new VariableToken(1),
                                                       new LessThanToken(),
                                                       new VariableToken(2),
                                                       new AddToken(),
                                                       new VariableToken(3)));
        final Exp expected = new OpExp(new IntegerExp(1),
                                       new LessThanOp(),
                                       new OpExp(new IntegerExp(2),
                                                 new AddOp(),
                                                 new IntegerExp(3)));
        assertEquals(new ParseResult<Exp>(expected, 5),
                     parser.parseLessThanExp(0));
    }
    
}
