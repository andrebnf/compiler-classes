package main.java.ast;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class CharValue {
  private char c;

  public CharValue(char c){
    this.c = c;
  }

  public void genC(PW pw){
    pw.out.print(c);
  }
}
