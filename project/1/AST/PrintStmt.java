/**
* 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
* @author  André Bonfatti, 408182
* @author  Thales Chagas,  408557
*/

package AST;
import java.util.ArrayList;

public class PrintStmt extends Stmt {

  private ArrayList<Expr> exprList;

  public void addExpr(Expr expr) {
    exprList.add(expr);
  }

}
