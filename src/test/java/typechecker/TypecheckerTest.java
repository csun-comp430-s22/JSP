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
    public static final Map<Var, Type> emptyTypeEnvironment =
        new HashMap<Var, Type>();

    public static Type typeof(final Exp exp) throws TypeErrorException {

    	final Typechecker emptyTypechecker =
                new Typechecker(new Program(new ArrayList<Structdec>(), new ArrayList<Functiondef>()));
            return emptyTypechecker.typeofExp(exp, emptyTypeEnvironment);
    }
    
    public static final Program createProgram(final String programString) throws TypeErrorException, TokenizerException, ParseException {
    	final Tokenizer tokenizer = new Tokenizer(programString);
        List<lexer.Token> tokensLexer = tokenizer.tokenize();
        List<parser.Token> tokensParser = new ArrayList<parser.Token>();
        tokensParser.addAll((Collection<? extends Token>) tokensLexer);
        final Parser parser = new Parser(tokensParser);
        final Program program = parser.parseProgram();
        return program;
    }
    
    @Test
    public void testTypeofBoolean() throws TypeErrorException {
        // 1 -> int
        assertEquals(new BoolType(),
                     typeof(new BooleanLiteralExp(true)));
    }

	@Test
    public void testTypeofInteger() throws TypeErrorException {
        // 1 -> int
        assertEquals(new IntType(),
                     typeof(new IntegerExp(1)));
    }
	
	@Test
    public void testTypeofBooleanTrue() throws TypeErrorException, TokenizerException, ParseException {
        final String booleanTypeString = "true";
        final Program myBoolean = createProgram(booleanTypeString);
        final Typechecker typechecker = new Typechecker(myBoolean);
        typechecker.typecheck();
    }
}