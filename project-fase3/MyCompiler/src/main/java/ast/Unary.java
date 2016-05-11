package main.java.ast;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class Unary {
  private char c;

  public Unary(char c) {
    this.c = c;
  }

  public Unary(String c) {
    this.c = c.toCharArray()[0];
  }

  public void genC(PW pw){
    pw.out.print(c);
  }
}
