/*
    comp6


    We added to the grammar more operators and a declaration of variables so that
    the grammar now accepts a program like
          a = 1  b = 3  : (- (+ a 2) 3)
    The result of the evaluation would be 0. Another program would be
          g = 3  t = 9  : (* (- t 7) g)

    Of course, the AST was extended to cope with the new rules. New classes
    were created:
         Program - represents the program
         Variable - a variable in the declaration
         VariableExpr - a variable inside an expression

    Grammar:
       Program ::= VarDecList ':' Expr
       VarDecList ::=  | VarDec VarDecList
       VarDec ::= Letter '=' Number
       Expr::=  '(' oper Expr  Expr ')' | Number | Letter
       Oper ::= '+' | '-' | '*' | '/'
       Number ::= '0'| '1' | ... | '9'
       Letter ::= 'A' | 'B'| ... | 'Z'| 'a'| 'b' | ... | 'z'


*/

import AST.*;
import java.util.ArrayList;

public class Compiler {

    public Program compile( char []p_input ) {
        input = p_input;
        tokenPos = 0;
        nextToken();

        Program e = program();
        if (tokenPos != input.length)
          error();

        return e;
    }

    //Program ::= VarDecList ':' Expr
    private Program program(){
        ArrayList<Variable> vars = varDecList();
        Expr res = null;

        if(token == ':'){
			nextToken();
			res = expr();
		} else error();

        return new Program(vars, res);
    }

    //VarDecList ::=  | VarDec VarDecList
    private ArrayList<Variable> varDecList(){
        ArrayList<Variable> varArray = new ArrayList<Variable>();

        while (token != ':')
            varArray.add(varDec());

        return varArray;
    }

    // VarDec ::= Letter '=' Number
  	private Variable varDec(){
        char let = letter();
        NumberExpr num = null;
        if (token == '=') {
            nextToken();
            num = number();
        } else
            error();
        return new Variable(let, num);
  	}

    //Expr::=  '(' oper Expr  Expr ')' | number | Letter
    private Expr expr() {
        Expr res = null;

        if (token == '(') {
            nextToken();
            char op;
            op = oper();
            Expr exprE, exprD;
            exprE = expr();
            exprD = expr();

            if (token == ')') {
                nextToken();
                res = new CompositeExpr(op, exprE, exprD);
            } else error();
        } else if (isDigit(token))
            res = number();
        else if (isLetter(token))
            res = new VariableExpr(letter());
        else error();

        return res;
    }

    //oper ::= '+' | '-' | '*' | '/'
    private char oper() {
        char op = ' ';
        if (token == '-' || token == '+' || token == '*' || token == '/') {
            op = token;
            nextToken();
        } else error();
        return op;
    }

    //number ::= '0'| '1' | ... | '9'
    private NumberExpr number() {
        NumberExpr num = null;
        if (isDigit(token)) {
            num = new NumberExpr(token);
            nextToken();
        } else error();
        return num;
    }

    private char letter(){
        char let = ' ';
        if (isLetter(token)) {
            let = token;
            nextToken();
        } else {
            error();
        }
        return let;
    }

    private boolean isLetter(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) return true;
        return false;
    }

    private boolean isDigit(char c) {
        if (c >= '0' && c <= '9') return true;
        return false;
    }

    private void nextToken() {
  		while(tokenPos < input.length && input[tokenPos] == ' '){
  			tokenPos++;
  		}

  		if(tokenPos >= input.length)
  			token = '\0';
  		else {
  			token = input[tokenPos];
  			tokenPos++;
  		}

    }

    private void error() {
        if ( tokenPos == 0 )
          tokenPos = 1;
        else
          if ( tokenPos >= input.length )
            tokenPos = input.length;

        String strInput = new String( input, tokenPos - 1, input.length - tokenPos + 1 );
        String strError = "Error at \"" + strInput + "\"";
        System.out.println( strError );
        throw new RuntimeException(strError);
    }

    private char token;
    private int  tokenPos;
    private char []input;

}
