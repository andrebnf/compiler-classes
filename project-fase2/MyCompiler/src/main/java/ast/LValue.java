package main.java.ast;

/**
 * 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class LValue {

  private Ident ident;
  private Expr expr;

  public void setExpr(Expr expr){
    this.expr = expr;
  }

  public LValue(Ident ident) {
    this.ident = ident;
  }
}
