/**
* 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
* @author  André Bonfatti, 408182
* @author  Thales Chagas,  408557
*/

package AST;

public class Variable {

  private Type type;
  private Stmt stmt;

  public void setType(Type type) {
    this.type = type;
  }

  public void setStmt(Stmt stmt) {
    this.stmt = stmt;
  }

}
