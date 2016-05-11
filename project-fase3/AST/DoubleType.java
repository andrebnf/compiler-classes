package AST;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class DoubleType extends StdType {
  public DoubleType() { super("double"); }

  public void genC(PW pw){
    pw.print("double ");
  }
}
