/**
* 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
* @author  André Bonfatti, 408182
* @author  Thales Chagas,  408557
*/

package main.java.ast;

public class Factor {
  private LValue lValue;
  private Expr expr;

  public void setLValue(LValue lValue) {
    this.lValue = lValue;
  }

  public void setExpr(Expr expr) {
    this.expr = expr;
  }

  public Factor() {
    expr = null;
    lValue = null;
  }

}
