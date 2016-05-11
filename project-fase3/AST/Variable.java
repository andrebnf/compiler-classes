package AST;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class Variable {

  private Type type;
  private Ident ident;
  private boolean isArrayVariable = false;
  private int arraySize;

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

  public void setArraySize(int arraySize){
    this.arraySize = arraySize;
  }

  public void setArrayVariable() {
    isArrayVariable = true;
  }

  public Variable() {
  }

  public void genC(PW pw){
    type.genC(pw);
    ident.genC(pw);
    if (isArrayVariable) {
      pw.print("[" + arraySize + "]");
    }
  }
}
