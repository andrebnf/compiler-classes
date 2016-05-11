package main.java.ast;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class VariableDecl {

  private Variable variable;

  public VariableDecl(Variable variable) {
    this.variable = variable;
  }

  public void genC(PW pw){
    variable.genC(pw);
    pw.out.println(";");
  }
}
