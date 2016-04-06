/**
* 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
* @author  André Bonfatti, 408182
* @author  Thales Chagas,  408557
*/

package AST;

public class StmtBlock {

  private ArrayList<VariableDecl> variableDecl;
  private ArrayList<Stmt> stmt;

  public StmtBloc() {
    variableDecl = new ArrayList<VariableDecl>();
    stmt = new ArrayList<Stmt>();
  }
}
