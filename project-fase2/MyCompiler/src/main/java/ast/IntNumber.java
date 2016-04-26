package main.java.ast;

/**
 * 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class IntNumber {

  private int number;

  public IntNumber(int number) {
    this.number = number;
  }

  public IntNumber(double number) {
    this.number = (int) number;
  }
}
