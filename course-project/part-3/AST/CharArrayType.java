package AST;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class CharArrayType extends ArrayType {
  public CharArrayType() {
    super("charArray");
  }

  public void genC(PW pw){
    pw.print("char ");
  }
}
