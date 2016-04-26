package main.java.ast;
import java.util.ArrayList;

/**
 * 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class PrintStmt extends Stmt {

  private ArrayList<Expr> exprList;

  public void addExpr(Expr expr) {
    exprList.add(expr);
  }

  public PrintStmt() {
    exprList = new ArrayList<Expr>();
  }

}
