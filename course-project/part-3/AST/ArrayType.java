package AST;

import Lexer.Symbol;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public abstract class ArrayType extends Type {

  public static ArrayType doubleArrayType  = new DoubleArrayType();
  public static ArrayType intArrayType     = new IntArrayType();
  public static ArrayType charArrayType    = new CharArrayType();

  public ArrayType(String name) {
    super(name);
  }

  public String getName() {
    return super.getName();
  }

  public void setName(String name) {
    super.setName(name);
  }

  public static ArrayType fromStdType(Type stdType) {
    ArrayType arr = null;
    if (stdType.getName().equals(Symbol.INT.toString())) {
      arr = ArrayType.intArrayType;
    } else if (stdType.getName().equals(Symbol.DOUBLE.toString())){
      arr = ArrayType.doubleArrayType;
    } else if (stdType.getName().equals(Symbol.CHAR.toString())){
      arr = ArrayType.charArrayType;
    }

    return arr;
  }

  abstract public void genC(PW pw);
}
