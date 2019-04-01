package AST;

public class IfStatement extends Statement {
    
    public IfStatement( Expr expr, StatementList thenPart, StatementList elsePart ) {
        this.expr = expr;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
    }
 
    public void genC( PW pw ) {
        
    }
    
    private Expr expr;
    private StatementList thenPart, elsePart;
}
