package parser;
public class ExpStmt implements Stmt {
    public final Exp expression;
    public ExpStmt(final Exp expression){
        this.expression = expression;
    }
    public boolean equals(final Object other){
        return (other instanceof ExpStmt &&
                expression.equals(((ExpStmt)other).expression));
    }
    public int hashCode(){
        return expression.hashCode();
    }
    public String toString(){
        return "ExpStmt(" + expression.toString() + ")";
    }
}