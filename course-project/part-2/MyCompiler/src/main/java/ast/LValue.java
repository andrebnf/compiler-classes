package main.java.ast;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class LValue {

  private Ident ident;
  private Expr expr;
  private Type type;

  public void setExpr(Expr expr){
    this.expr = expr;
  }

  public LValue(Ident ident) {
    this.ident = ident;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Type getType() {
    return type;
  }
}
