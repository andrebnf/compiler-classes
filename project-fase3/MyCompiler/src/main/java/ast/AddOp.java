package main.java.ast;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class AddOp {
  private String op;

  public AddOp(String op) {
    this.op = op;
  }

  public String getOp() {
    return op;
  }

  public void genC(PW pw){
    pw.out.print(op);
  }
}
