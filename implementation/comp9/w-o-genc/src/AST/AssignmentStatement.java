package AST;

public class AssignmentStatement extends Statement {
    
    public AssignmentStatement( Variable v, Expr expr ) {
        this.v = v;
        this.expr = expr;
    }
 
    public void genC( PW pw ) {

    }

    private Variable v;
    private Expr expr;
}
