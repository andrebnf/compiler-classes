package AST;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class CharType extends StdType {
  public CharType() {
    super("char");
  }

  public void genC(PW pw){
    pw.print("char ");
  }
}
