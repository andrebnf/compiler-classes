package main.java.ast;
import java.util.ArrayList;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class IfStmt extends Stmt {

  private Expr expr;
  private ArrayList<Stmt> stmtTopList;
  private ArrayList<Stmt> stmtBottomList;


  public void setExpr(Expr expr) {
    this.expr = expr;
  }

  public void addTopStmt(Stmt stmt) {
    stmtTopList.add(stmt);
  }

  public void addBottomStmt(Stmt stmt) {
    stmtBottomList.add(stmt);
  }

  public IfStmt() {
    stmtTopList = new ArrayList<Stmt>();
    stmtBottomList = new ArrayList<Stmt>();
  }

  public void genC(PW pw){
    pw.out.print("if (");
    expr.genC(pw);
    pw.out.print(") {");
    pw.add();

    for (Stmt stmt : stmtTopList)
      stmt.genC(pw);

    pw.sub();
    pw.out.print("} ");

    if (stmtBottomList.size() > 0) {
      pw.out.print("else {");
      pw.add();

      for (Stmt stmt : stmtBottomList)
        stmt.genC(pw);


      pw.sub();
      pw.out.print("} ");
    }
    pw.out.println();
  }
}
