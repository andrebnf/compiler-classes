package AST;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class Decl {

  private StmtBlock stmtBlock;

  public Decl(StmtBlock stmtBlock) {
    this.stmtBlock = stmtBlock;
  }

  public void genC(PW pw){
    pw.println("void main (){");
    pw.add();
    stmtBlock.genC(pw);
    pw.sub();
    pw.print("}");
  }
}
