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
Factor       ::= LValue ':' Expr
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
    Decl decl = null;
    if (token == 'v') {
      nextToken();
      if (token == 'm') {
        nextToken();
        if (token == '(') {
          nextToken();
          if (token = ')') {
            nextToken();
            decl = new Decl(stmtBlock());
          }
        }
      }
    }
    return decl;
  }

  // StmtBlock ::= '{' { VariableDecl } { Stmt } '}'
  private StmtBlock stmtBlock() {
    ArrayList<VariableDecl> retorno = new ArrayList<VariableDecl>();
    while(token == 'i' || token == 'd' || token == 'c'){

    }
    ArrayList<Stmt> varDec = variableDecl();

  }

  // VariableDecl ::= Variable ';'
  private ArrayList<Variable> variableDecl() {

    return varList;
  }

  // Variable ::= Type Ident
  private Variable variable() {
    type();
    ident();
  }

  // Type ::= StdType | ArrayType
  private Type type() {
    if (token == 'i' || token == 'd' || token == 'c') {
      nextToken();
      else error();
    }

    // StdType ::= 'i' | 'd' | 'c'
    private StdType stdType() {
      if (token == 'i' || token == 'd' || token == 'c') {
        nextToken();
      } else error();
    }

    // ArrayType ::= StdType '[' ']'
    private ArrayType arrayType() {

      if (token == '[') {
        nextToken();
        if (token == ']') {
          nextToken();
        } else error();
      } else error();
    }

    // Stmt ::= Expr ';' | IfStmt | WhileStmt | BreakStmt | PrintStmt
    private Stmt stmt() {

    }

    // IfStmt ::= 'f' '(' Expr ')' '{' { Stmt } '}' [ 'e' '{' { Stmt } '}' ]
    private IfStmt ifStmt() {

    }

    // WhileStmt ::= 'w' '(' Expr ')' '{' { Stmt } '}'
    private WhileStmt whiteStmt() {
      WhileStmt whileStmt = new WhileStmt();
      if (token == 'w') {
        nextToken();
        if (token == '(') {
          nextToken();
          whileStmt.setExpr(expr());
          if (token == ')') {
            nextToken();
            if (token == '{') {
              nextToken();
              whileStmt.setStmt(stmt());
              if (token == '}') {
                nextToken();
              } else error();
            } else error();
          } else error();
        } else error();
      } else error();

      return whileStmt;
    }

    // BreakStmt ::= 'b' ';'
    private BreakStmt breakStmt() {
      if (token == 'b') {
        nextToken();
        if (token == ';') {
          nextToken();
        } else error();
      } else error();
    }

    // PrintStmt ::= 'p' '(' Expr { ',' Expr }')'
    private PrintStmt printStmt() {
      if (token == 'p') {
        nextToken();
        if (token == '(')) {
          expr();
          while(token == ',') {
            nextToken();
            expr();
          }
        } else error();
      } else error();
    }

    // Expr ::= SimExpr [ RelOp Expr]
    private Expr expr() {

    }

    // SimExpr ::= [Unary] Term { AddOp Term }
    private SimExpr simExpr() {

    }

    // Term ::= Factor { MulOp Factor }
    private Term term() {

    }

    // Factor ::= LValue ':' Expr | LValue | '(' Expr ')'
    //              | 'r' '(' ')' | 's' '(' ')' | 't' '(' ')'
    private Factor factor() {
      Factor factor = null;
      if (token == 'r' && ){
        nextToken
      }
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
