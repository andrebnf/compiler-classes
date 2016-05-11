package main.java.ast;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class Expr extends Stmt{

  private SimExpr simExpr;
  private RelOp relOp;
  private Expr expr;
  private Type type;

  public RelOp getRelOp() {
    return relOp;
  }

  public void setSimExpr(SimExpr simExpr) {
    this.simExpr = simExpr;
  }

  public void setRelOp(RelOp relOp) {
    this.relOp = relOp;
  }

  public void setExpr(Expr expr) {
    this.expr = expr;
  }

  public Expr setType(Type type) {
    this.type = type;
    return this;
  }

  public Type getType() {
    return type;
  }

  public void genC(PW pw){
    simExpr.genC(pw);
    if (relOp != null) {
      relOp.genC(pw);
      expr.genC(pw);
    }
  }
}
