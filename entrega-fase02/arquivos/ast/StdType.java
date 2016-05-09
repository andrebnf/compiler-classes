package main.java.ast;

import main.java.lexer.Symbol;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

abstract public class StdType extends Type {

  public static StdType doubleType  = new DoubleType();
  public static StdType intType     = new IntType();
  public static StdType charType    = new CharType();

  public StdType(String name) {
    super(name);
  }

  public String getName() {
    return super.getName();
  }

  public static StdType fromArrayType(Type arrayType) {
    StdType arr = null;
    if (arrayType.getName().equals(Symbol.INT.toString() + "Array")) {
      arr = StdType.intType;
    } else if (arrayType.getName().equals(Symbol.DOUBLE.toString() + "Array")){
      arr = StdType.doubleType;
    } else if (arrayType.getName().equals(Symbol.CHAR.toString() + "Array")){
      arr = StdType.charType;
    }

    return arr;
  }
}
