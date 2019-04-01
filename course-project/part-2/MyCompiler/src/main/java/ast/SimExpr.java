package main.java.ast;
import java.util.ArrayList;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class SimExpr {
  private Unary unary;
  private Term leftTerm;
  private ArrayList<AddOp> addOpsList;
  private ArrayList<Term> rightTermsList;
  private Type type;

  public void setLeftTerm(Term leftTerm) {
    this.leftTerm = leftTerm;
  }

  public Term getLeftTerm() {
    return leftTerm;
  }

  public void setUnary(Unary unary) {
    this.unary = unary;
  }

  public void addRightTerm(Term term) {
    rightTermsList.add(term);
  }

  public void addAddOp(AddOp addOp) {
    addOpsList.add(addOp);
  }

  public SimExpr(){
    rightTermsList = new ArrayList<Term>();
    addOpsList = new ArrayList<AddOp>();
  }

  public boolean evaluateTypes() {
    setType(leftTerm.getType());
    for (Term t : rightTermsList) {
      if (t.getType() != getType())
        return false;
    }
    return true;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Term hasOnlyTerm() {
    if (this == null) return null;
    if (rightTermsList.isEmpty()) {
      return leftTerm;
    } else {
      return null;
    }
  }

  public boolean hasUnchainableAddOp() {
    for (AddOp a : addOpsList)
      if (!a.getOp().equals("||"))
        return true;

    return false;
  }
}
