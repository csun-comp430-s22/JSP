package typechecker;

import parser.*;
import parser.Token;
import lexer.*;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class TypecheckerTest {
    public static final Map<Var, Type> typeEnvironment =
        new HashMap<Var, Type>();

    public static Type typeof(final Exp exp) throws TypeErrorException {

    	final Typechecker typechecker =
                new Typechecker(new Program(new ArrayList<Structdec>(), new ArrayList<Functiondef>()));
            return typechecker.typeofExp(exp, typeEnvironment);
    }
    
    /*public static final Program createProgram(final String programString) throws TypeErrorException, TokenizerException, ParseException {
    	final Tokenizer tokenizer = new Tokenizer(programString);
        List<lexer.Token> tokensLexer = tokenizer.tokenize();
        List<parser.Token> tokensParser = new ArrayList<parser.Token>();
        tokensParser.addAll((Collection<? extends Token>) tokensLexer);
        final Parser parser = new Parser(tokensParser);
        final Program program = parser.parseProgram();
        return program;
    }*/
    
    @Test
    public void testTypeofBoolean() throws TypeErrorException {
        // (bool)true -> bool
        assertEquals(new BoolType(), typeof(new BooleanLiteralExp(true)));
    }

	@Test
    public void testTypeofInteger() throws TypeErrorException {
        // (int)1 -> int
        assertEquals(new IntType(), typeof(new IntegerExp(1)));
    }
	
	@Test
    public void testTypeofVar() throws TypeErrorException {
        // (int)x -> int
		Var variable = new Var("x");
		Type type = new IntType();
		typeEnvironment.put(variable, type);
        assertEquals(new IntType(), typeof(new VarExp(new Var("x"))));
        typeEnvironment.remove(variable, type);
    }
	
	@Test(expected = TypeErrorException.class)
    public void testTypeofEmptyMap() throws TypeErrorException {
        // (int)x -> int
        assertEquals(new IntType(), typeof(new VarExp(new Var("x"))));
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidVar() throws TypeErrorException {
		// (bool)y -> int
		Var variable = new Var("x");
		Type type = new BoolType();
		typeEnvironment.put(variable, type);
		assertEquals(new IntType(), typeof(new VarExp(new Var("z"))));
		typeEnvironment.remove(variable, type);
	}
	
	@Test
    public void testTypeofOpAdd() throws TypeErrorException {
        // (int)x + (int)y -> int
		OpExp addOp = new OpExp(new VarExp(new Var("x")),
                			    new AddOp(),
                			    new VarExp(new Var("y")));
		Var var1 = new Var("x");
		Var var2 = new Var("y");
		Type type1 = new IntType();
		Type type2 = new IntType();
		typeEnvironment.put(var1, type1);
		typeEnvironment.put(var2, type2);
        assertEquals(new IntType(), typeof(addOp));
        typeEnvironment.remove(var1, type1);
		typeEnvironment.remove(var2, type2);
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidAdd() throws TypeErrorException {
		// (bool)x + (bool)y -> int
		OpExp addOp = new OpExp(new VarExp(new Var("x")),
			    				new AddOp(),
			    				new VarExp(new Var("y")));
		Var var1 = new Var("x");
		Var var2 = new Var("y");
		Type type1 = new BoolType();
		Type type2 = new BoolType();
		typeEnvironment.put(var1, type1);
		typeEnvironment.put(var2, type2);
		assertEquals(new IntType(), typeof(addOp));
		typeEnvironment.remove(var1, type1);
		typeEnvironment.remove(var2, type2);
	}
	
	@Test
    public void testTypeofOpMinus() throws TypeErrorException {
        // 1 - 1 -> int
		OpExp minusOp = new OpExp(new IntegerExp(1),
                			      new MinusOp(),
                			      new IntegerExp(1));
        assertEquals(new IntType(), typeof(minusOp));
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidMinus() throws TypeErrorException {
		// (bool)true - (bool)false -> int
		OpExp minusOp = new OpExp(new BooleanLiteralExp(true),
			    				  new MinusOp(),
			    				  new BooleanLiteralExp(false));
		assertEquals(new IntType(), typeof(minusOp));
	}
	
	@Test
    public void testTypeofOpMult() throws TypeErrorException {
        // 2 * 3 -> int
		OpExp multOp = new OpExp(new IntegerExp(2),
                			     new MultiplicationOp(),
                			     new IntegerExp(3));
        assertEquals(new IntType(), typeof(multOp));
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidMult() throws TypeErrorException {
		// (bool)true * (int)3 -> int
		OpExp multOp = new OpExp(new BooleanLiteralExp(true),
			    				 new MultiplicationOp(),
			    				 new IntegerExp(3));
		assertEquals(new IntType(), typeof(multOp));
	}
	
	@Test
    public void testTypeofOpDiv() throws TypeErrorException {
        // 6 / 3 -> int
		OpExp divOp = new OpExp(new IntegerExp(6),
                			    new DivisionOp(),
                			    new IntegerExp(3));
        assertEquals(new IntType(), typeof(divOp));
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidDiv() throws TypeErrorException {
		// (int)3 / (bool)true -> int
		OpExp divOp = new OpExp(new IntegerExp(3),
			    				new DivisionOp(),
			    				new BooleanLiteralExp(true));
		assertEquals(new IntType(), typeof(divOp));
	}
	
	@Test
    public void testTypeofOpLessThan() throws TypeErrorException {
        // x < y -> bool
		OpExp lessThanOp = new OpExp(new VarExp(new Var("x")),
			    					 new LessThanOp(),
			    					 new VarExp(new Var("y")));
		Var var1 = new Var("x");
		Var var2 = new Var("y");
		Type type1 = new IntType();
		Type type2 = new IntType();
		typeEnvironment.put(var1, type1);
		typeEnvironment.put(var2, type2);
        assertEquals(new BoolType(), typeof(lessThanOp));
        typeEnvironment.remove(var1, type1);
		typeEnvironment.remove(var2, type2);
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidLessThan() throws TypeErrorException {
		// (int)3 < (bool)true -> bool
		OpExp lessThanOp = new OpExp(new IntegerExp(3),
			    					 new LessThanOp(),
			    					 new BooleanLiteralExp(true));
		assertEquals(new BoolType(), typeof(lessThanOp));
	}
	
	@Test
    public void testTypeofOpGreaterThan() throws TypeErrorException {
        // 6 > 3 -> bool
		OpExp greaterThanOp = new OpExp(new IntegerExp(6),
                			    		new GreaterThanOp(),
                			    		new IntegerExp(3));
        assertEquals(new BoolType(), typeof(greaterThanOp));
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidGreaterThan() throws TypeErrorException {
		// (int)3 > (bool)true -> bool
		OpExp greaterThanOp = new OpExp(new IntegerExp(3),
			    					 	new GreaterThanOp(),
			    					 	new BooleanLiteralExp(true));
		assertEquals(new BoolType(), typeof(greaterThanOp));
	}
	
	@Test
    public void testTypeofOpEqualTo() throws TypeErrorException {
        // 6 == 3 -> bool
		OpExp equalToOp = new OpExp(new IntegerExp(6),
                			    	new EqualToOp(),
                			    	new IntegerExp(3));
        assertEquals(new BoolType(), typeof(equalToOp));
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidEqualTo() throws TypeErrorException {
		// (int)3 == (bool)true -> bool
		OpExp equalToOp = new OpExp(new IntegerExp(3),
			    					new EqualToOp(),
			    					new BooleanLiteralExp(true));
		assertEquals(new BoolType(), typeof(equalToOp));
	}
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidOp() throws TypeErrorException {
		OpExp lessThanOp = new OpExp(new IntegerExp(6),
		    						 new FieldOp(),
		    						 new IntegerExp(3));
		assertEquals(new BoolType(), typeof(lessThanOp));
	}
	
	/*@Test
    public void testTypeofBooleanTrue() throws TypeErrorException, TokenizerException, ParseException {
        final String booleanTypeString = "true";
        final Program myBoolean = createProgram(booleanTypeString);
        final Typechecker typechecker = new Typechecker(myBoolean);
        typechecker.typecheck();
    }*/
}