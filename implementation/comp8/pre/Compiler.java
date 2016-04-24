/*
    comp8

    Variables now can have any number of characters and numbers any number
    of digits. There are new keywords and new non-terminals. The operator
    set includes the comparison operators. There are a few statements.
    Anything after // till the end of the line is a comment.
    Note that VarDecList was modified.

    The input is now taken from a file.
    Method error now prints the line in which the error occurred.


    Grammar:
       Program ::= [ "var" VarDecList ";" ] CompositeStatement
       CompositeStatement ::= "begin" StatementList "end"
       StatementList ::= | Statement ";" StatementList
       Statement ::= AssignmentStatement | IfStatement | ReadStatement |
          WriteStatement
       AssignmentStatement ::= Variable "="  Expr
       IfStatement ::= "if" Expr "then" StatementList [ "else" StatementList ] "endif"
       ReadStatement ::= "read" "(" Variable ")"
       WriteStatement ::= "write" "(" Expr ")"

       Variable ::= Letter { Letter }
       VarDecList ::= Variable | Variable "," VarDecList
       Expr::=  '(' oper Expr  Expr ')' | Number | Variable
       Oper ::= '+' | '-' | '*' | '/' | '<' | '<=' | '>' | '>='| '==' | '<>'
       Number ::= Digit { Digit }
       Digit ::= '0'| '1' | ... | '9'
       Letter ::= 'A' | 'B'| ... | 'Z'| 'a'| 'b' | ... | 'z'

   Anything between [] is optional. Anything between { e } can be
   repeated zero or more times.
*/

import AST.*;
import Lexer.*;

import java.util.ArrayList;
import java.util.Hashtable;



public class Compiler {

      // contains the keywords
    static private Hashtable<String, Symbol> keywordsTable;

     // this code will be executed only once for each program execution
     static {
        keywordsTable = new Hashtable<String, Symbol>();
        keywordsTable.put( "var", Symbol.VAR );

     }

    public Program compile( char []p_input ) {
        input = p_input;
        tokenPos = 0;
        nextToken();

        symbolTable = new Hashtable<String, Variable>();
        Program e = program();
        if (token != Symbol.EOF)
          error("End program expected");

        return e;
    }

	// Program ::= [ "var" VarDecList ";" ] CompositeStatement
    private Program program(){

    }

          //  CompositeStatement ::= "begin" StatementList "end"
    private StatementList compositeStatement() {

    }

          //  StatementList ::= | Statement ";" StatementList
    private StatementList statementList() {
    }

        /*  Statement ::= AssignmentStatement | IfStatement | ReadStatement | WriteStatement
        */
    private Statement statement() {

    }

    private AssignmentStatement assignmentStatement() {

          // the current token is Symbol.IDENT and stringValue
          // contains the identifier
        String name = stringValue;

          // is the variable in the symbol table ? Variables are inserted in the
          // symbol table when they are declared. It the variable is not there, it has
          // not been declared.

    }

     //  IfStatement ::= "if" Expr "then" StatementList [ "else" StatementList ] "endif"
    private IfStatement ifStatement() {
    }


     //  ReadStatement ::= "read" "(" Variable ")"
    private ReadStatement readStatement() {

          // check if the variable was declared
    }


     // WriteStatement ::= "write" "(" Expr ")"
    private WriteStatement writeStatement() {
    }



    //   VarDecList ::= Variable | Variable "," VarDecList ";"
    private ArrayList<Variable> varDecList() {
		ArrayList<Variable> retorno = new ArrayList<Variable>();
        Variable variable = varDec();
        v.add(variable);
		while(token != ':'){
            nextToken();
			retorno.add(varDec());
		}

		return retorno;
    }

    private Variable varDec() {
        Variable v;

          // semantic analysis
          // if the name is in the symbol table, the variable has been declared twice.


          // inserts the variable in the symbol table. The name is the key and an
          // object of class Variable is the value. Hash tables store a pair (key, value)
          // retrieved by the key.
    }


    //Expr::=  '(' oper Expr  Expr ')' | number | Letter
    //Expr::=  '(' oper Expr  Expr ')' | number | Letter
    private Expr expr() {

		Expr res = null;

		if(token == '('){
			nextToken();
			char op;
			op = oper();
			Expr ExprE, ExprD;
			ExprE = expr();
			ExprD = expr();

			if(token == ')'){
				nextToken();

				res = new CompositeExpr(op, ExprE, ExprD);

			}
			else
				error(") esperado");
		}
		else
			if(token >= '0' && token <= '9')
				res = number();
			else{
				char letter1 = letter();
				Variable aux = symbolTable.get(letter1);
				if(aux != null){
					res = new VariableExpr(aux);
				}
				else
					error("Variavel nao declarada: " + letter1);
			}

		return res;
    }


	private Symbol oper() {
		Symbol aux = null;
		if (token == Symbol.PLUS || token == Symbol.MINUS || token == Symbol.MULT || token == Symbol.DIV || token == Symbol.LE || token == Symbol.LT || token == Symbol.GE || token == Symbol.GT || token == Symbol.EQ || token == Symbol.NEQ) {
			aux = token;
			nextToken();
		}
		else {
			error("Operador inválido.");
		}
		return aux;
	}

	//number ::= '0'| '1' | ... | '9'
	private NumberExpr number() {
	}


    private void nextToken() {

    }

    private void error(String errorMsg) {
        if ( tokenPos == 0 )
          tokenPos = 1;
        else
          if ( tokenPos >= input.length )
            tokenPos = input.length;

        String strInput = new String( input, tokenPos - 1, input.length - tokenPos + 1 );
        String strError = "Error at \"" + strInput + "\"";
        System.out.println( strError );
        System.out.println( errorMsg );
        throw new RuntimeException(strError);
    }

    private int  tokenPos;
    private char []input;
 	private Hashtable<String, Variable> symbolTable;

 	private Symbol token; // notem a mudanca aqui!
    private String stringValue;
    private int numberValue;
    // number of current line. Starts with 1
    private int lineNumber;
    private static final int MaxValueInteger = 32768; // 2 ^ 15


}
