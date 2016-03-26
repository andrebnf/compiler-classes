package AST;

public class Variable {

	private char id;
	private NumberExpr num;

	public Variable(char id, NumberExpr num){
		this.id = id;
		this.num = num;
	}

	public void genC(){
		System.out.print("\tint "+ id +" = ");
		num.genC();
		System.out.println(";");
	}

}
