package AST;

public class ParenthesisExpr extends Expr {
    
    public ParenthesisExpr( Expr expr ) {
        this.expr = expr;
    }
    
    public void genC( PW pw ) {

    }
    
    public Type getType() {
        return expr.getType();
    }
    
    private Expr expr;
}
