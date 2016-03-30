/*
UFSCar - Universidade Federal de São Carlos - Campus Sorocaba
Compiladores - 2016/01

=================================
Projeto de Implementação - Fase 1
=================================

== Grammar

    Program      ::= Decl
    Decl         ::= 'v' 'm' '(' ')' StmtBlock
    StmtBlock    ::= '{' { VariableDecl } { Stmt } '}'
    VariableDecl ::= Variable ';'
    Variable     ::= Type Ident
    Type         ::= StdType | ArrayType
    StdType      ::= 'i' | 'd' | 'c'
    ArrayType    ::= StdType '[' ']'
    Stmt         ::= Expr ';' | IfStmt | WhileStmt | BreakStmt | PrintStmt
    IfStmt       ::= 'f' '(' Expr ')' '{' { Stmt } '}' [ 'e' '{' { Stmt } '}' ]
    WhileStmt    ::= 'w' '(' Expr ')' '{' { Stmt } '}'
    BreakStmt    ::= 'b' ';'
    PrintStmt    ::= 'p' '(' Expr { ',' Expr }')'
    Expr         ::= SimExpr [ RelOp Expr]
    SimExpr      ::= [Unary] Term { AddOp Term }
    Term         ::= Factor { MulOp Factor }
    Factor       ::= LValue '=' Expr
                        | LValue
                        | '(' Expr ')'
                        | 'r' '(' ')' | 's' '(' ')' | 't' '(' ')'
    LValue       ::= Ident | Ident '[' Expr ']'
    Ident        ::= Letter { Letter | Digit }
    RelOp        ::= '=' | '#' | '<' | '>'
    AddOp        ::= '+' | '-'
    MulOp        ::= '*' | '/' | '%'
    Unary        ::= '+' | '-' | '!'
    Digit        ::= '0' | '1' | ... | '9'
    Letter       ::= 'A' | 'B' | ... | 'Z' | 'a' | 'b' | ... | 'z'

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


    // Program ::= Decl
    private Program program() {
        return new Program(decl());
    }

    // Decl ::= 'v' 'm' '(' ')' StmtBlock
    private Decl decl() {

        return decl;
    }

    // StmtBlock ::= '{' { VariableDecl } { Stmt } '}'
    private StmtBlock stmtBlock() {
        List<Variable> = variableDecl();
    }

    // VariableDecl ::= Variable ';'
    private List<Variable> variableDecl() {


        return varList;
    }
    // Variable ::= Type Ident
    // Type ::= StdType | ArrayType
    // StdType ::= 'i' | 'd' | 'c'
    // ArrayType ::= StdType '[' ']'
    // Stmt ::= Expr ';' | IfStmt | WhileStmt | BreakStmt | PrintStmt
    // IfStmt ::= 'f' '(' Expr ')' '{' { Stmt } '}' [ 'e' '{' { Stmt } '}' ]
    // WhileStmt ::= 'w' '(' Expr ')' '{' { Stmt } '}'
    // BreakStmt ::= 'b' ';'
    // PrintStmt ::= 'p' '(' Expr { ',' Expr }')'
    // Expr ::= SimExpr [ RelOp Expr]
    // SimExpr ::= [Unary] Term { AddOp Term }
    // Term ::= Factor { MulOp Factor }
    // Factor ::= LValue '=' Expr | LValue | '(' Expr ')'
    //              | 'r' '(' ')' | 's' '(' ')' | 't' '(' ')'
    private Factor factor() {
        Factor factor = null;
        return factor;
    }

    // LValue ::= Ident | Ident '[' Expr ']'
    private LValue lValue() { //# LValue return a string (within LValue class!!!)

        return lValue;
    }


    // Ident ::= Letter { Letter | Digit }
    private Ident ident() {

        return ident;
    }

    // RelOp ::= '=' | '#' | '<' | '>'
    private RelOp relOp() {
        RelOp relOp = null;
        if (token == '=' || token == '#' || token == '<' || token == '>'){
            relOp = new RelOp(token);
            nextToken();
        } else error();
        return relOp;
    }

    // AddOp ::= '+' | '-'
    private AddOp addOp() {
        AddOp addOp = null;
        if (token == '+' || token == '-'){
            addOp = new AddOp(token);
            nextToken();
        } else error();
        return addOp;
    }

    // MulOp ::= '*' | '/' | '%'
    private MulOp mulOp() {
        MulOp mulOp = null;
        if (token == '*' || token == '/' || token == '%'){
            mulOp = new MulOp(token);
            nextToken();
        } else error();
        return mulOp;
    }

    // Unary ::= '+' | '-' | '!'
    private Unary unary() {
        Unary un = null;
        if (token == '+' || token == '-' || token == '!'){
            un = new Unary(token);
            nextToken();
        } else error();
        return un;
    }

    // Digit ::= '0' | '1' | ... | '9'
    private Digit digit() {
        Digit num = null;
        if (c >= '0' && c <= '9') {
            num = new Digit(token);
            nextToken();
        } else error();
        return num;
    }

    // Letter ::= 'A' | 'B' | ... | 'Z' | 'a' | 'b' | ... | 'z'
    private char letter(){
        char let = ' ';
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            let = token;
            nextToken();
        } else {
            error();
        }
        return let;
    }

    // END OF GRAMMAR ============================================
    private void nextToken() {
  		while(tokenPos < input.length && input[tokenPos] == ' ')
  			tokenPos++;

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
