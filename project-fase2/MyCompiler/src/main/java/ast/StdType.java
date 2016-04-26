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

  public static StdType fromVariable(Variable v) {
    if (v.getTypeName().equals(Symbol.INT.toString())) {
      return StdType.intType;
    } else if (v.getTypeName().equals(Symbol.CHAR.toString())) {
      return StdType.charType;
    } else if (v.getTypeName().equals(Symbol.DOUBLE.toString())) {
      return StdType.doubleType;
    }
    return null;
  }
}
