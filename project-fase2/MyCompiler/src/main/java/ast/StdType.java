package main.java.ast;

/**
 * 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
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
}
