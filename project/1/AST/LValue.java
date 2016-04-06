/**
* 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
* @author  André Bonfatti, 408182
* @author  Thales Chagas,  408557
*/

package AST;

public class LValue {

  private Ident ident;

  public LValue(Ident ident) {
    this.ident = ident;
  }
}
