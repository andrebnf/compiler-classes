/**
* 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
* @author  André Bonfatti, 408182
* @author  Thales Chagas,  408557
*/

package AST;

public class WhileStmt extends Stmt {

  private Expr expr;
  private ArrayList<Stmt> stmtList;

  public void addStmt(Stmt stmt) {
    stmtList.add(stmt)
  }

  public void setExpr(Expr expr) {
    this.expr = expr;
  }

}
