/**
* 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
* @author  André Bonfatti, 408182
* @author  Thales Chagas,  408557
*/

package AST;

public class SimExpr extends Expr {
  private Unary unary;
  private Term leftTerm;
  private ArrayList<AddOp> addOpsList;
  private ArrayList<Term> rightTermsList;

  public void setLeftTerm(Term leftTerm) {
    this.leftTerm = leftTerm;
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
}
