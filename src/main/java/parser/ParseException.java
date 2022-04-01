package parser;

//parsing failure
public class ParseException extends Exception {
    public ParseException(final String msg) {
        super(msg);
    }
}
