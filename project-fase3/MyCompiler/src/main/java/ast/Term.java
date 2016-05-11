package main.java.ast;
import java.util.ArrayList;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class Term {
  private Factor leftFactor;
  private ArrayList<MulOp> mulOpsList;
  private ArrayList<Factor> rightFactorsList;
  private Type type;

  public void setLeftFactor(Factor leftFactor) {
    this.leftFactor = leftFactor;
  }

  public void addRightFactor(Factor rightFactor) {
    rightFactorsList.add(rightFactor);
  }

  public void addMulOp(MulOp mulOp){
    mulOpsList.add(mulOp);
  }

  public Type getType() {
    return type;
  }

  public boolean evaluateTypes(){
    setType(leftFactor.getType());
    for (Factor f : rightFactorsList) {
      if (f.getType() != getType())
        return false;
    }
    return true;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Term() {
    mulOpsList = new ArrayList<MulOp>();
    rightFactorsList = new ArrayList<Factor>();
  }

  public boolean hasUnchainableMulOp() {
    for (MulOp m : mulOpsList)
      if (!m.getOp().equals("&&"))
        return true;

    return false;
  }

  public void genC(PW pw){
    leftFactor.genC(pw);
    for (int i = 0; i < mulOpsList.size(); i++) {
      mulOpsList.get(i).genC(pw);
      rightFactorsList.get(i).genC(pw);
    }
  }
}
