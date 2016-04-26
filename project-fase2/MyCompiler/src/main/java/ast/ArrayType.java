package main.java.ast;

import main.java.lexer.Symbol;

/**
 * 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class ArrayType extends Type {

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

  public ArrayType fromStdType(StdType stdType) {
    ArrayType arr = null;
    if (stdType.getName().equals(Symbol.INT)) {
      arr = ArrayType.intArrayType;
    } else if (stdType.getName().equals(Symbol.DOUBLE)){
      arr = ArrayType.doubleArrayType;
    } else if (stdType.getName().equals(Symbol.CHAR)){
      arr = ArrayType.doubleArrayType;
    }

    return arr;
  }
}
