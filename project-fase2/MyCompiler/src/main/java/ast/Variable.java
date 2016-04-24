/**
* 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
* @author  André Bonfatti, 408182
* @author  Thales Chagas,  408557
*/

package main.java.ast;

public class Variable {

  private Type type;
  private Ident ident;

  public void setType(Type type) {
    this.type = type;
  }

  public void setIdent(Ident ident) {
    this.ident = ident;
  }

}
