package typechecker;

import parser.*;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class TypecheckerTest {
    public static final Map<Identifier, Type> emptyTypeEnvironment =
        new HashMap<Identifier, Type>();

    public static Type typeof(final Exp exp) throws TypeErrorException {

    	final Typechecker emptyTypechecker =
                new Typechecker(new Program(new ArrayList<Funcdef>()));
            return emptyTypechecker.typeof(exp, emptyTypeEnvironment);
    }
    
    @Test
    public void testTypeofBoolean() throws TypeErrorException {
        // true -> bool
        assertEquals(new BoolType(),
                     typeof(new BooleanLiteralExp(true)));
    }

    @Test
    public void testTypeofInteger() throws TypeErrorException {
        // 1 -> int
        assertEquals(new IntType(),
                     typeof(new IntegerLiteralExp(1)));
    }
    
}