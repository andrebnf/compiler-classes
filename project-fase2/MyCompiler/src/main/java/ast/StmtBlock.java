package main.java.ast;
import java.util.ArrayList;

/**
* 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
* @author  André Bonfatti, 408182
* @author  Thales Chagas,  408557
 */

public class StmtBlock {

  private ArrayList<VariableDecl> variableDeclList;
  private ArrayList<Stmt> stmtList;

  public void addVariableDecl(VariableDecl vd) {
    variableDeclList.add(vd);
  }

  public void addStmt(Stmt stmt) {
    stmtList.add(stmt);
  }

  public StmtBlock() {
    variableDeclList = new ArrayList<VariableDecl>();
    stmtList = new ArrayList<Stmt>();
  }
}
