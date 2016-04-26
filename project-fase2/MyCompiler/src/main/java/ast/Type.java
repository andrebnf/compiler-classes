package main.java.ast;

import main.java.lexer.Symbol;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

abstract public class Type {

  private Symbol t;
  private String name;

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setTypeId(Symbol t) {
    this.t = t;
  }

  public Type(String name) {
    this.name = name;
  }
}
