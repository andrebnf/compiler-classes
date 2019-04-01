/**
* 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
* @author  André Bonfatti, 408182
* @author  Thales Chagas,  408557
*/

package main.java.ast;
import java.util.ArrayList;

public class WhileStmt extends Stmt {

  private Expr expr;
  private ArrayList<Stmt> stmtList;

  public void addStmt(Stmt stmt) {
    stmtList.add(stmt);
  }

  public void setExpr(Expr expr) {
    this.expr = expr;
  }

  public WhileStmt() {
    stmtList = new ArrayList<Stmt>();
  }
}
