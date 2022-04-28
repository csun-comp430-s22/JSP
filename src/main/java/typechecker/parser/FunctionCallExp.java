package typechecker.parser;

import java.util.List;

public class FunctionCallExp implements Exp {
    public final FunctionName fname;
    public final List<Exp> params;

    public FunctionCallExp(final FunctionName fname,
                           final List<Exp> params) {
        this.fname = fname;
        this.params = params;
    }

    public int hashCode() {
        return (fname.hashCode() +
                params.hashCode());
    }

    public boolean equals(final Object other) {
        if (other instanceof FunctionCallExp) {
            final FunctionCallExp asFunc = (FunctionCallExp)other;
            return (fname.equals(asFunc.fname) &&
                    params.equals(asFunc.params));
        } else {
            return false;
        }
    }

    public String toString() {
        return ("FunctionCallExp(" + fname.toString() + ", " +
                params.toString() + ")");
    }
}