package AST;

public class VariableExpr extends Expr {

	private char id;

	public VariableExpr(char id){
		this.id = id;
	}

	public void genC(){
		System.out.print(id);
	}
}
