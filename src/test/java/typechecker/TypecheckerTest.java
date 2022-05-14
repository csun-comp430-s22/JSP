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

    public static Type typeofExp(final Exp exp) throws TypeErrorException {

    	final Typechecker typechecker = new Typechecker(new Program(new ArrayList<Structdec>(), 
    																new ArrayList<Functiondef>()));
            return typechecker.typeofExp(exp, typeEnvironment);
    }
    
    public static Map<Var, Type> typeofVardec(final VardecStmt asDec, 
			  								  final Map<Var, Type> actualEnvironment,
			  								  final Type returnType) throws TypeErrorException {

    	final Typechecker typechecker = new Typechecker(new Program(new ArrayList<Structdec>(), 
    																new ArrayList<Functiondef>()));
            return typechecker.typecheckVardec(asDec, actualEnvironment, returnType);
    }
    
    public static Map<Var, Type> typeofIf(final IfStmt asIf, 
			  							  final Map<Var, Type> actualEnvironment,
			  							  final Type returnType) throws TypeErrorException {

    		  final Typechecker typechecker = new Typechecker(new Program(new ArrayList<Structdec>(), 
																		  new ArrayList<Functiondef>()));
    		  return typechecker.typecheckIf(asIf, actualEnvironment, returnType);
    }
    
    public static Map<Var, Type> typeofWhile(final WhileStmt asWhile, 
			  							  	 final Map<Var, Type> actualEnvironment,
			  							  	 final Type returnType) throws TypeErrorException {

    	final Typechecker typechecker = new Typechecker(new Program(new ArrayList<Structdec>(), 
											  						new ArrayList<Functiondef>()));
    	return typechecker.typecheckWhile(asWhile, actualEnvironment, returnType);
    }
    
    public static Map<Var, Type> typeofReturn(final ReturnStmt asReturn, 
			  							  final Map<Var, Type> actualEnvironment,
			  							  final Type returnType) throws TypeErrorException {

    	final Typechecker typechecker = new Typechecker(new Program(new ArrayList<Structdec>(), 
											  						new ArrayList<Functiondef>()));
    	return typechecker.typecheckReturn(asReturn, actualEnvironment, returnType);
    }
    
    public static Map<Var, Type> typeofBlock(final BlockStmt asBlock, 
				 							 final Map<Var, Type> actualEnvironment,
				 							 final Type returnType) throws TypeErrorException {

    	final Typechecker typechecker = new Typechecker(new Program(new ArrayList<Structdec>(), 
																	new ArrayList<Functiondef>()));
    	return typechecker.typecheckBlock(asBlock, actualEnvironment, returnType);
    }
    
    public static Map<Var, Type> typeofPrint(final PrintStmt asPrint, 
			  								 final Map<Var, Type> actualEnvironment,
			  								 final Type returnType) throws TypeErrorException {

    	final Typechecker typechecker = new Typechecker(new Program(new ArrayList<Structdec>(), 
				  													new ArrayList<Functiondef>()));
    	return typechecker.typecheckPrint(asPrint, actualEnvironment, returnType);
    }
    
    public static Map<Var, Type> typeofStmt(final Stmt stmt, 
				 							final Map<Var, Type> actualEnvironment,
				 							final Type returnType) throws TypeErrorException {

    	final Typechecker typechecker = new Typechecker(new Program(new ArrayList<Structdec>(), 
																	new ArrayList<Functiondef>()));
    	return typechecker.typecheckStmt(stmt, actualEnvironment, returnType);
    }
    
    /*public static Map<Var, Type> typeofFunction(final Functiondef funcdef) throws TypeErrorException {
    	final Typechecker typechecker = new Typechecker(new Program(new ArrayList<Structdec>(), 
																	new ArrayList<Functiondef>()));
    	return typechecker.typecheckFunction(funcdef);
    }*/
    
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
        assertEquals(new BoolType(), typeofExp(new BooleanLiteralExp(true)));
    }

	@Test
    public void testTypeofInteger() throws TypeErrorException {
        // (int)1 -> int
        assertEquals(new IntType(), typeofExp(new IntegerExp(1)));
    }
	
	@Test
    public void testTypeofVar() throws TypeErrorException {
        // (int)x -> int
		Var variable = new Var("x");
		Type type = new IntType();
		typeEnvironment.put(variable, type);
        assertEquals(new IntType(), typeofExp(new VarExp(new Var("x"))));
        typeEnvironment.remove(variable, type);
    }
	
	@Test(expected = TypeErrorException.class)
    public void testTypeofEmptyMap() throws TypeErrorException {
        // (int)x -> int
        assertEquals(new IntType(), typeofExp(new VarExp(new Var("x"))));
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidVar() throws TypeErrorException {
		// (bool)y -> int
		Var variable = new Var("x");
		Type type = new BoolType();
		typeEnvironment.put(variable, type);
		assertEquals(new IntType(), typeofExp(new VarExp(new Var("z"))));
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
        assertEquals(new IntType(), typeofExp(addOp));
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
		assertEquals(new IntType(), typeofExp(addOp));
		typeEnvironment.remove(var1, type1);
		typeEnvironment.remove(var2, type2);
	}
	
	@Test
    public void testTypeofOpMinus() throws TypeErrorException {
        // 1 - 1 -> int
		OpExp minusOp = new OpExp(new IntegerExp(1),
                			      new MinusOp(),
                			      new IntegerExp(1));
        assertEquals(new IntType(), typeofExp(minusOp));
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidMinus() throws TypeErrorException {
		// (bool)true - (bool)false -> int
		OpExp minusOp = new OpExp(new BooleanLiteralExp(true),
			    				  new MinusOp(),
			    				  new BooleanLiteralExp(false));
		assertEquals(new IntType(), typeofExp(minusOp));
	}
	
	@Test
    public void testTypeofOpMult() throws TypeErrorException {
        // 2 * 3 -> int
		OpExp multOp = new OpExp(new IntegerExp(2),
                			     new MultiplicationOp(),
                			     new IntegerExp(3));
        assertEquals(new IntType(), typeofExp(multOp));
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidMult() throws TypeErrorException {
		// (bool)true * (int)3 -> int
		OpExp multOp = new OpExp(new BooleanLiteralExp(true),
			    				 new MultiplicationOp(),
			    				 new IntegerExp(3));
		assertEquals(new IntType(), typeofExp(multOp));
	}
	
	@Test
    public void testTypeofOpDiv() throws TypeErrorException {
        // 6 / 3 -> int
		OpExp divOp = new OpExp(new IntegerExp(6),
                			    new DivisionOp(),
                			    new IntegerExp(3));
        assertEquals(new IntType(), typeofExp(divOp));
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidDiv() throws TypeErrorException {
		// (int)3 / (bool)true -> int
		OpExp divOp = new OpExp(new IntegerExp(3),
			    				new DivisionOp(),
			    				new BooleanLiteralExp(true));
		assertEquals(new IntType(), typeofExp(divOp));
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
        assertEquals(new BoolType(), typeofExp(lessThanOp));
        typeEnvironment.remove(var1, type1);
		typeEnvironment.remove(var2, type2);
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidLessThan() throws TypeErrorException {
		// (int)3 < (bool)true -> bool
		OpExp lessThanOp = new OpExp(new IntegerExp(3),
			    					 new LessThanOp(),
			    					 new BooleanLiteralExp(true));
		assertEquals(new BoolType(), typeofExp(lessThanOp));
	}
	
	@Test
    public void testTypeofOpGreaterThan() throws TypeErrorException {
        // 6 > 3 -> bool
		OpExp greaterThanOp = new OpExp(new IntegerExp(6),
                			    		new GreaterThanOp(),
                			    		new IntegerExp(3));
        assertEquals(new BoolType(), typeofExp(greaterThanOp));
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidGreaterThan() throws TypeErrorException {
		// (int)3 > (bool)true -> bool
		OpExp greaterThanOp = new OpExp(new IntegerExp(3),
			    					 	new GreaterThanOp(),
			    					 	new BooleanLiteralExp(true));
		assertEquals(new BoolType(), typeofExp(greaterThanOp));
	}
	
	@Test
    public void testTypeofOpEqualTo() throws TypeErrorException {
        // 6 == 3 -> bool
		OpExp equalToOp = new OpExp(new IntegerExp(6),
                			    	new EqualToOp(),
                			    	new IntegerExp(3));
        assertEquals(new BoolType(), typeofExp(equalToOp));
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidEqualTo() throws TypeErrorException {
		// (int)3 == (bool)true -> bool
		OpExp equalToOp = new OpExp(new IntegerExp(3),
			    					new EqualToOp(),
			    					new BooleanLiteralExp(true));
		assertEquals(new BoolType(), typeofExp(equalToOp));
	}
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidOp() throws TypeErrorException {
		OpExp lessThanOp = new OpExp(new IntegerExp(6),
		    						 new FieldOp(),
		    						 new IntegerExp(3));
		assertEquals(new BoolType(), typeofExp(lessThanOp));
	}
	
	@Test
    public void testTypeofVardec() throws TypeErrorException {
        // int x -> int
		VardecStmt vardecstmt = new VardecStmt(new Vardec(new IntType(),
														  new VarExp(new Var("x"))));
		Type returnType = new IntType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		actualEnvironment.put(new Var("x"), new IntType());
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
		expectedEnvironment.put(new Var("x"), new IntType());
        assertEquals(expectedEnvironment, typeofVardec(vardecstmt, actualEnvironment, returnType));
    }
	
	@Test
    public void testTypeofIf() throws TypeErrorException {
        // if(x) print(1); else return 2; -> bool
		IfStmt ifstmt = new IfStmt(new VarExp(new Var("x")),
								   new PrintStmt(new IntegerExp(1)),
								   new ReturnStmt(new IntegerExp(2)));
		Type returnType = new IntType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		actualEnvironment.put(new Var("x"), new BoolType());
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
		expectedEnvironment.put(new Var("x"), new BoolType());
        assertEquals(expectedEnvironment, typeofIf(ifstmt, actualEnvironment, returnType));
    }
	
	@Test
    public void testTypeofWhile() throws TypeErrorException {
        // while(true) print(7); -> bool
		WhileStmt whilestmt = new WhileStmt(new BooleanLiteralExp(true),
								   			new PrintStmt(new IntegerExp(7)));
		Type returnType = new IntType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
        assertEquals(expectedEnvironment, typeofWhile(whilestmt, actualEnvironment, returnType));
    }
	
	@Test
    public void testTypeofReturn() throws TypeErrorException {
        // return 1 -> int
		ReturnStmt returnstmt = new ReturnStmt(new IntegerExp(1));
		Type returnType = new IntType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
        assertEquals(expectedEnvironment, typeofReturn(returnstmt, actualEnvironment, returnType));
    }
	
	@Test(expected = TypeErrorException.class)
	public void testInvalidReturn() throws TypeErrorException {
		// return (bool)x -> int
		ReturnStmt returnstmt = new ReturnStmt(new VarExp(new Var("x")));
		Type returnType = new IntType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		actualEnvironment.put(new Var("x"), new BoolType());
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
		expectedEnvironment.put(new Var("x"), new BoolType());
		assertEquals(expectedEnvironment, typeofReturn(returnstmt, actualEnvironment, returnType));
	}
	
	@Test
    public void testTypeofBlock() throws TypeErrorException {
        // { int x;
		//   print(7);
		//   return true;-> bool
		final List<Stmt> stmts = new ArrayList<Stmt>();
		stmts.add(new VardecStmt(new Vardec(new IntType(),new VarExp(new Var("x")))));
		stmts.add(new PrintStmt(new IntegerExp(7)));
		stmts.add(new ReturnStmt(new BooleanLiteralExp(true)));
		BlockStmt blockstmt = new BlockStmt(stmts);
		Type returnType = new BoolType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		actualEnvironment.put(new Var("x"), new IntType());
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
		expectedEnvironment.put(new Var("x"), new IntType());
        assertEquals(expectedEnvironment, typeofBlock(blockstmt, actualEnvironment, returnType));
    }
	
	@Test
    public void testTypeofPrint() throws TypeErrorException {
        // print(x) -> bool
		PrintStmt printstmt = new PrintStmt(new VarExp(new Var("x")));
		Type returnType = new BoolType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		actualEnvironment.put(new Var("x"), new BoolType());
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
		expectedEnvironment.put(new Var("x"), new BoolType());
        assertEquals(expectedEnvironment, typeofPrint(printstmt, actualEnvironment, returnType));
    }
	
	@Test
    public void testTypeofStmtVardec() throws TypeErrorException {
		// int x -> int
		VardecStmt vardecstmt = new VardecStmt(new Vardec(new IntType(),
														  new VarExp(new Var("x"))));
		Type returnType = new IntType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		actualEnvironment.put(new Var("x"), new IntType());
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
		expectedEnvironment.put(new Var("x"), new IntType());
		assertEquals(expectedEnvironment, typeofStmt(vardecstmt, actualEnvironment, returnType));
    }
	
	@Test
    public void testTypeofStmtIf() throws TypeErrorException {
        // if(x) print(1); else return 2; -> bool
		IfStmt ifstmt = new IfStmt(new VarExp(new Var("x")),
								   new PrintStmt(new IntegerExp(1)),
								   new ReturnStmt(new IntegerExp(2)));
		Type returnType = new IntType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		actualEnvironment.put(new Var("x"), new BoolType());
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
		expectedEnvironment.put(new Var("x"), new BoolType());
        assertEquals(expectedEnvironment, typeofStmt(ifstmt, actualEnvironment, returnType));
    }
	
	@Test
    public void testTypeofStmtWhile() throws TypeErrorException {
        // while(true) print(7); -> bool
		WhileStmt whilestmt = new WhileStmt(new BooleanLiteralExp(true),
								   			new PrintStmt(new IntegerExp(7)));
		Type returnType = new IntType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
        assertEquals(expectedEnvironment, typeofStmt(whilestmt, actualEnvironment, returnType));
    }
	
	@Test
    public void testTypeofStmtReturn() throws TypeErrorException {
		// return 1 -> int
		ReturnStmt returnstmt = new ReturnStmt(new IntegerExp(1));
		Type returnType = new IntType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
		assertEquals(expectedEnvironment, typeofStmt(returnstmt, actualEnvironment, returnType));
    }
	
	@Test
    public void testTypeofStmtBlock() throws TypeErrorException {
        // { int x;
		//   print(7);
		//   return true;-> bool
		final List<Stmt> stmts = new ArrayList<Stmt>();
		stmts.add(new VardecStmt(new Vardec(new IntType(),new VarExp(new Var("x")))));
		stmts.add(new PrintStmt(new IntegerExp(7)));
		stmts.add(new ReturnStmt(new BooleanLiteralExp(true)));
		BlockStmt blockstmt = new BlockStmt(stmts);
		Type returnType = new BoolType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		actualEnvironment.put(new Var("x"), new IntType());
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
		expectedEnvironment.put(new Var("x"), new IntType());
        assertEquals(expectedEnvironment, typeofStmt(blockstmt, actualEnvironment, returnType));
    }
	
	@Test
    public void testTypeofStmtPrint() throws TypeErrorException {
		// print(x) -> bool
		PrintStmt printstmt = new PrintStmt(new VarExp(new Var("x")));
		Type returnType = new BoolType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		actualEnvironment.put(new Var("x"), new BoolType());
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
		expectedEnvironment.put(new Var("x"), new BoolType());
		assertEquals(expectedEnvironment, typeofStmt(printstmt, actualEnvironment, returnType));
    }
	
	/*@Test
    public void testTypeofFunction() throws TypeErrorException {
		// print(x) -> bool
		Functiondef funcdef = new Functiondef(new VarExp(new Var("x")));
		Type returnType = new BoolType();
		final Map<Var, Type> actualEnvironment = new HashMap<Var, Type>();
		actualEnvironment.put(new Var("x"), new BoolType());
		final Map<Var, Type> expectedEnvironment = new HashMap<Var, Type>();
		expectedEnvironment.put(new Var("x"), new BoolType());
		assertEquals(expectedEnvironment, typeofFunction(funcdef));
    }*/
	
	
	
	/*@Test
    public void testTypeofBooleanTrue() throws TypeErrorException, TokenizerException, ParseException {
        final String booleanTypeString = "true";
        final Program myBoolean = createProgram(booleanTypeString);
        final Typechecker typechecker = new Typechecker(myBoolean);
        typechecker.typecheck();
    }*/
}