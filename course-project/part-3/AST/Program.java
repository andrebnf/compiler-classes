package AST;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class Program{

	private Decl decl;

	public Program(Decl decl){
		this.decl = decl;
	}

	public void genC(PW pw){
		decl.genC(pw);
	}
}
