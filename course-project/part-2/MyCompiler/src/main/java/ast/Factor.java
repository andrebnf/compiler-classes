package main.java.ast;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class Factor {
  private LValue lValue;
  private Expr expr;
  private Number number;
  private CharValue charValue;

  private Type type;

  public void setType(Type type) {
    this.type = type;
  }

  public Type getType() {
    return type;
  }

  public String getTypeName() {
    return type.getName();
  }

  public void setLValue(LValue lValue) {
    this.lValue = lValue;
  }

  public void setExpr(Expr expr) {
    this.expr = expr;
  }

  public void setCharValue(CharValue charValue) {
    this.charValue = charValue;
  }

  public void setNumber(Number number) {
    this.number = number;
  }

  public Factor() {
    expr = null;
    lValue = null;
    number = null;
    charValue = null;
    type = null;
  }

  public boolean hasOnlyOneSideValue() {
    if (this == null) return false;

    if (lValue != null)
      if (charValue == null && number == null && expr == null)
        return true;

    return false;
  }

}
