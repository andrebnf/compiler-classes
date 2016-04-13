package AST;
import java.util.ArrayList;

public class Program{

	private Expr expr;
	private ArrayList<Variable> varList;

	public Program(ArrayList<Variable> vars, Expr expr){
		this.expr = expr;
		this.varList = vars;
	}

	public void genC(){
		System.out.println("void main () {");

		for(Variable v:varList){
			v.genC();
		}

		System.out.print("\t");
		expr.genC();
		System.out.println(";\n }");
	}

}
