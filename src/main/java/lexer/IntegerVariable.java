package lexer;

public class IntegerVariable implements Token {

    public final int val;

    public IntegerVariable(final int val) {
        this.val = val;
    }

    public boolean equals(final Object tokens) {
        if (tokens instanceof IntegerVariable) {
            final IntegerVariable forInt = (IntegerVariable) tokens;
            return val == forInt.val;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return val;
    }

    public String toString() {
        return "IntegerVar(" + val + ")";
    }
}