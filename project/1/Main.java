/**
* 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
* @author  André Bonfatti, 408182
* @author  Thales Chagas,  408557
*/

import AST.*;

public class Main {
  public static void main(String []args) {
    char []input = "v m () { i[] aa; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }
}
