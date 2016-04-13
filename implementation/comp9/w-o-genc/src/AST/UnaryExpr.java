package AST;

import Lexer.Symbol;


public class UnaryExpr extends Expr {
    
    public UnaryExpr( Expr expr, Symbol op ) {
        this.expr = expr;
        this.op = op;
    }
    
    public void genC( PW pw ) {

    }
    
    public Type getType() {
        return expr.getType();
    }
    
    private Expr expr;
    private Symbol op;
}
              
