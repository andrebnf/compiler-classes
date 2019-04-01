package AST;

public class IfStatement extends Statement {
    
    public IfStatement( Expr expr, StatementList thenPart, StatementList elsePart ) {
        this.expr = expr;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
    }
 
    public void genC( PW pw ) {
        pw.print("if(");
        expr.genC(pw);
        pw.out.println(") {");
        pw.add();
        thenPart.genC(pw);
        pw.sub();
        pw.print("}");
        if(elsePart != null) {
        	pw.out.println(" else {");
        	pw.add();
        	elsePart.genC(pw);
        	pw.sub();
        	pw.print("}");
        }
        pw.out.println("");
    }
    
    private Expr expr;
    private StatementList thenPart, elsePart;
}
