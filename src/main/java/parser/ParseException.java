package parser;

//parsing failure
public class ParseException extends Exp {
    public ParseException(final String msg) {
        super(msg);
    }
}