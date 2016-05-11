package main.java.ast;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class Variable {

  private Type type;
  private Ident ident;

  public void setType(Type type) {
    this.type = type;
  }

  public Type getType() {
    return type;
  }

  public void setIdent(Ident ident) {
    this.ident = ident;
  }

  public String getTypeName() {
    return type.getName();
  }

  public void genC(PW pw){
    type.genC(pw);
    ident.genC(pw);
  }
}
