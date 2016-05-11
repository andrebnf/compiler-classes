package AST;
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
    pw.print("if (");
    expr.genC(pw);
    pw.println(") {");
    pw.add();

    for (Stmt stmt : stmtTopList)
      stmt.genC(pw);

    pw.sub();
    pw.print("} ");

    if (stmtBottomList.size() > 0) {
      pw.println("else {");
      pw.add();

      for (Stmt stmt : stmtBottomList)
        stmt.genC(pw);

      pw.sub();
      pw.print("} ");
    }
    pw.println("");
  }
}
