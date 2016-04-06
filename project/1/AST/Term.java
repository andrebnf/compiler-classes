/**
* 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
* @author  André Bonfatti, 408182
* @author  Thales Chagas,  408557
*/

package AST;

public class Term {
  private Factor leftFactor;
  private ArrayList<MulOp> mulOpsList;
  private ArrayList<Factor> rightFactorsList;

  public void setLeftFactor(Factor leftFactor) {
    this.leftFactor = leftFactor;
  }

  public void addRightFactor(Factor rightFactor) {
    rightFactorsList.add(rightFactor);
  }

  public void addMulOp(MulOp mulop){
    mulOpsList.add(mulOp);
  }

  public Term() {
    mulOpsList = new ArrayList<MulOp>();
    rightFactorsList = new ArrayList<Factor>();
  }
}
