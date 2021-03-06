package lexer;

import java.util.List;
import java.util.ArrayList;

public class Tokenizer {
    private final String input;
    private int offset;

    public Tokenizer(final String input) {
        this.input = input;
        offset = 0;
    }
    
    public void skipWhitespace() {
        while (offset < input.length() &&
               Character.isWhitespace(input.charAt(offset))) {
            offset++;
        }
    }

    // returns null if it wasn't an integer token
    public IntegerVariable tryTokenizeInteger() {
        skipWhitespace();

        String num = "";

        while (offset < input.length() &&
               Character.isDigit(input.charAt(offset))) {
            num += input.charAt(offset);
            offset++;
        }

        if (num.length() > 0) {
            //string conversion to int
            return new IntegerVariable(Integer.parseInt(num));
        } else {
            return null;
        }
    }
    
    public Token tryTokenizeStructName() {
    	skipWhitespace();
    	String name = "";

        //read characters individually
        //first variable char is a capital letter
        //followed by letter or integer
        if (offset < input.length() &&
        	Character.isUpperCase(input.charAt(offset))) {
            name += input.charAt(offset);
            offset++;

            while (offset < input.length() &&
                   Character.isLetterOrDigit(input.charAt(offset))) {
                name += input.charAt(offset);
                offset++;
            }
            return new StructNameToken(name);
        } else {
        	return null;
        }
    }
    
    public Token tryTokenizeFunctName() {
    	skipWhitespace();
    	String name = "";

        //read characters individually
        //first functionname char is a dollar symbol
        //followed by letter or integer
        if (offset < input.length() &&
        	input.startsWith("$")) {
            name += "$";
            offset += 1;

            while (offset < input.length() &&
                    Character.isLetterOrDigit(input.charAt(offset))) {
                 name += input.charAt(offset);
                 offset++;
             }
             return new FunctNameToken(name);
        } else {
        	return null;
        }
    }
    
    // returns null if no variable or keyword
    public Token tryTokenizeIdentifierOrKeyword() {
        skipWhitespace();
        
        String name = "";

        //read characters individually
        //first variable char is a letter
        //followed by letter or integer
        if (offset < input.length() &&
        	Character.isLowerCase(input.charAt(offset))) {
            name += input.charAt(offset);
            offset++;

            while (offset < input.length() &&
                   Character.isLetterOrDigit(input.charAt(offset))) {
                name += input.charAt(offset);
                offset++;
            }

            //name holds potential variable
            if (name.equals("true")) {
                return new TrueToken();
            } else if (name.equals("false")) {
                return new FalseToken();
            } else if (name.equals("if")) {
                return new IfToken();
            } else if (name.equals("else")) {
                return new ElseToken();
            } else if (name.equals("break")) {
            	return new BreakToken();
            } else if (name.equals("return")) {
            	return new ReturnToken();
            } else if(name.equals("while")) {
            	return new WhileToken();
            } else if(name.equals("print")) {
            	return new PrintToken();
            } else if(name.equals("int")) {
            	return new IntToken();
            } else if(name.equals("boolean")) {
            	return new BooleanToken();
            } else if(name.equals("void")) {
            	return new VoidToken();
            } else if(name.equals("struct")) {
            	return new StructToken();
            } else {
                return new VarToken(name);
            }
        } else {
            return null;
        }
    }

    // returns null if it couldn't read in a symbol
    public Token tryTokenizeSymbol() {
        skipWhitespace();
        Token val = null;
        
        if (input.startsWith("(", offset)) {
            offset += 1;
            val = new LeftParenToken();
        } else if (input.startsWith(")", offset)) {
            offset += 1;
            val = new RightParenToken();
        }  else if (input.startsWith("{", offset)) {
            offset += 1;
            val = new LeftCurlyToken();
        } else if (input.startsWith("}", offset)) {
            offset += 1;
            val = new RightCurlyToken();
        } else if (input.startsWith("+", offset)) {
        	offset += 1;
        	val = new AddToken();
        } else if (input.startsWith("-", offset)) {
        	offset += 1;
        	val = new MinusToken();
        } else if (input.startsWith("*", offset)) {
        	offset += 1;
        	val = new MultiplicationToken();
        } else if (input.startsWith("/", offset)) {
        	offset += 1;
        	val = new DivideToken();
        } else if (input.startsWith(";", offset)) {
        	offset += 1;
        	val = new SemicolonToken();
        } else if (input.startsWith(">", offset)) {
        	offset += 1;
        	val = new GreaterThanToken();
        } else if (input.startsWith("<", offset)) {
        	offset += 1;
        	val = new LessThanToken();
        } else if (input.startsWith("=>", offset)) {
        	offset += 2;
        	val = new FunctionPointerToken();
        } else if (input.startsWith("==", offset)) {
        	offset += 2;
        	val = new EqualsToToken();
        } else if(input.startsWith("=", offset)) {
        	offset += 1;
        	val = new AssignmentToken();
        } else if (input.startsWith(".", offset)) {
        	offset += 1;
        	val = new PeriodToken();
        } else if (input.startsWith(",", offset)) {
        	offset += 1;
        	val = new CommaToken();
        } else if (input.startsWith("&", offset)) {
        	offset += 1;
        	val = new AddressToken();
        }

        return val;
    }
    
    // returns null if there are no more tokens left
    public Token tokenizeSingle() throws TokenizerException {
        Token retval = null;
        skipWhitespace();
        if (offset < input.length() &&
        	(retval = tryTokenizeStructName()) == null &&
        	(retval = tryTokenizeFunctName()) == null &&
            (retval = tryTokenizeIdentifierOrKeyword()) == null &&
            (retval = tryTokenizeInteger()) == null &&
            (retval = tryTokenizeSymbol()) == null) {
            throw new TokenizerException();
        }

        return retval;
    }
    
    public List<Token> tokenize() throws TokenizerException {
        final List<Token> tokens = new ArrayList<Token>();
        Token token = tokenizeSingle();

        while (token != null) {
            tokens.add(token);
            token = tokenizeSingle();
        }

        return tokens;
    }
}
