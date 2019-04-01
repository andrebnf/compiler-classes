package AST;
import java.util.ArrayList;

/**
* 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
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

  public void genC(PW pw){
    for (VariableDecl vd : variableDeclList) {
      vd.genC(pw);
    }
    for (Stmt stmt : stmtList) {
      stmt.genC(pw);
    }
  }
}
