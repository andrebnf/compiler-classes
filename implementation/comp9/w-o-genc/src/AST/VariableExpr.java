package AST;

public class VariableExpr extends Expr {
    
    public VariableExpr( Variable v ) {
        this.v = v;
    }
    
    public void genC( PW pw ) {

    }
    
    public Type getType() {
        return v.getType();
    }
    
    private Variable v;
}
