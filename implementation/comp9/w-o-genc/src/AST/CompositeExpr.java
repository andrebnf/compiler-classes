package AST;

import Lexer.*;

public class CompositeExpr extends Expr {
    
    public CompositeExpr( Expr pleft, Symbol poper, Expr pright ) {
        left = pleft;
        oper = poper;
        right = pright;
    }
    public void genC( PW pw ) {

    }
    
    public Type getType() {
          // left and right must be the same type
       if ( oper == Symbol.EQ || oper == Symbol.NEQ || oper == Symbol.LE || oper == Symbol.LT ||
            oper == Symbol.GE || oper == Symbol.GT ||
            oper == Symbol.AND || oper == Symbol.OR )
            return Type.booleanType;
       else
            return Type.integerType;
    }
    
    private Expr left, right;
    private Symbol oper;
}
