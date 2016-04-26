package main.java.compiler;

import main.java.ast.*;
import main.java.ast.Number;
import main.java.lexer.*;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Hashtable;

/**
* UFSCar - Universidade Federal de São Carlos - Campus Sorocaba
* 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
* @author  André Bonfatti, 408182
* @author  Thales Chagas,  408557

=================================
Projeto de Implementação - Fase 1
=================================

== Grammar

Program      ::= Decl
Decl         ::= 'void' 'main' '(' ')' StmtBlock
StmtBlock    ::= '{' { VariableDecl } { Stmt } '}'
VariableDecl ::= Variable ';'
Variable     ::= Type Ident
Type         ::= StdType | ArrayType
StdType      ::= 'int' | 'double' | 'cchar'
ArrayType    ::= StdType '[' IntNumber ']'
Stmt         ::= Expr ';' | IfStmt | WhileStmt | BreakStmt | PrintStmt
IfStmt       ::= 'if' '(' Expr ')' '{' { Stmt } '}' [ 'else' '{' { Stmt } '}' ]
WhileStmt    ::= 'while' '(' Expr ')' '{' { Stmt } '}'
BreakStmt    ::= 'break' ';'
PrintStmt    ::= 'print' '(' Expr { ',' Expr } ')' ';'
Expr         ::= SimExpr [ RelOp Expr]
SimExpr      ::= [Unary] Term { AddOp Term }
Term         ::= Factor { MulOp Factor }
Factor       ::= LValue ':=' Expr
                  | LValue
                  | '(' Expr ')'
                  | 'readInteger' '(' ')' | 'readDouble' '(' ')' | 'readChar' '(' ')'
                  | CharValue
                  | Number
LValue       ::= Ident | Ident '[' Expr ']'
Ident        ::= Letter { Letter | Digit }
Number       ::= [ '+' | '-' ] IntNumber [ '.' IntNumber ]
IntNumber    ::= Digit { Digit }
RelOp        ::= '=' | '!=' | '<' | '<=' | '>' | '>='
AddOp        ::= '+' | '-' | '||'
MulOp        ::= '*' | '/' | '%' | '&&'
Unary        ::= '+' | '-' | '!'
Digit        ::= '0' | '1' | ... | '9'
Letter       ::= 'A' | 'B' | ... | 'Z' | 'a' | 'b' | ... | 'z'

*/

public class Compiler {
  private Hashtable<String, Variable> symbolTable;
  private Lexer lexer;
  private CompilerError error;

  public Program compile(char []input, PrintWriter outError) {

    symbolTable = new Hashtable<String, Variable>();
    error = new CompilerError(outError);
    lexer = new Lexer(input, error);
    error.setLexer(lexer);

    lexer.nextToken();

    return program();
  }

  // Program ::= Decl
  private Program program() {
    Program p = new Program(decl());

    if ( lexer.token != Symbol.EOF )
      error.signal("EOF expected");

    return p;
  }

  // Decl ::= 'void' 'main' '(' ')' StmtBlock
  private Decl decl() {
    Decl decl = null;
    if (lexer.token == Symbol.VOID) {
      lexer.nextToken();
      if (lexer.token == Symbol.MAIN) {
        lexer.nextToken();
        if (lexer.token == Symbol.LEFTPAR) {
          lexer.nextToken();
          if (lexer.token == Symbol.RIGHTPAR) {
            lexer.nextToken();
            decl = new Decl(stmtBlock());
          } else
            error.signal("Syntax error. Expected `)`");
        } else
          error.signal("Syntax error. Expected `(`");
      } else
        error.signal("Synxtax error. Expected name of function `main`");
    } else
      error.signal("Syntax error. Expected `void`");

    return decl;
  }

  // StmtBlock ::= '{' { VariableDecl } { Stmt } '}'
  private StmtBlock stmtBlock() {
    StmtBlock stmtBlock = new StmtBlock();

    if (lexer.token == Symbol.LEFTCURLY) {
      lexer.nextToken();
    } else
      error.signal("Expected a statement block");

    while(lexer.token == Symbol.INT || lexer.token == Symbol.DOUBLE
            || lexer.token == Symbol.CHAR){ // { VariableDecl }
      stmtBlock.addVariableDecl(variableDecl());
    }
    while(lexer.token != Symbol.RIGHTCURLY) { // { Stmt }
      stmtBlock.addStmt(stmt());
    }
    if (lexer.token == Symbol.RIGHTCURLY) {
      lexer.nextToken();
    }

    return stmtBlock;
  }

  // VariableDecl ::= Variable ';'
  private VariableDecl variableDecl() {
    VariableDecl vd = new VariableDecl(variable());
    if (lexer.token == Symbol.SEMICOLON) {
      lexer.nextToken();
    } else
      error.signal("Expected `;`");

    return vd;
  }

  // Variable ::= Type Ident
  private Variable variable() {
    Variable variable = new Variable();
    variable.setType(type());
    variable.setIdent(ident());

    // SEM - variable must not be declared twice
    String name = lexer.getStringValue();
    if (symbolTable.get(name) != null)
      error.signal("Variable " + name + " has already been declared");

    symbolTable.put(name, variable);
    return variable;
  }

  // Type ::= StdType | ArrayType
  private Type type() {
    Type type = null;
    Symbol old_token = null;
    if (lexer.token == Symbol.INT || lexer.token == Symbol.DOUBLE || lexer.token == Symbol.CHAR){
      old_token = lexer.token;
      type = stdType();
      if (lexer.token == Symbol.LEFTBRACE) {
        arrayType();
        ArrayType arr = ArrayType.fromStdType(type);
        type = arr;
      }
    } else
      error.signal("Expected standard type declaration");
    type.setTypeId(old_token);
    return type;
  }

  // StdType ::= 'int' | 'double' | 'char'
  private StdType stdType() {
    StdType stdType = null;
    if (lexer.token == Symbol.INT) {
      stdType = StdType.intType;
      lexer.nextToken();
    } else if (lexer.token == Symbol.DOUBLE) {
      stdType = StdType.doubleType;
      lexer.nextToken();
    } else if (lexer.token == Symbol.CHAR) {
      stdType = StdType.charType;
      lexer.nextToken();
    } else
      error.signal("Expected variable type");

    return stdType;
  }

  // ArrayType ::= StdType '[' IntNumber ']'
  private ArrayType arrayType() {
    if (lexer.token == Symbol.LEFTBRACE) {
      lexer.nextToken();
      if (lexer.token == Symbol.INT) {
        lexer.nextToken();
        if (lexer.token == Symbol.RIGHTBRACE) {
          lexer.nextToken();
        } else
          error.signal("Expected `]`");
      } else
        error.signal("Expected `int` value here");
    } else
      error.signal("Expected `[`");

    return null;
  }

  // Stmt ::= Expr ';' | IfStmt | WhileStmt | BreakStmt | PrintStmt
  private Stmt stmt() {
    Stmt stmt = null;

    if (lexer.token == Symbol.IF) {
      stmt = ifStmt();
    } else if (lexer.token == Symbol.WHILE) {
      stmt = whileStmt();
    } else if (lexer.token == Symbol.BREAK) {
      stmt = breakStmt();
    } else if (lexer.token == Symbol.PRINT) {
      stmt = printStmt();
    } else {
      stmt = expr();
      if (lexer.token == Symbol.SEMICOLON)
        lexer.nextToken();
      else
        error.signal("Expected `;`");
    }

    return stmt;
  }

  // IfStmt ::= 'if' '(' Expr ')' '{' { Stmt } '}' [ 'else' '{' { Stmt } '}' ]
  private IfStmt ifStmt() {
    IfStmt ifStmt = new IfStmt();
    if (lexer.token == Symbol.IF) {
      lexer.nextToken();
      if (lexer.token == Symbol.LEFTPAR) {
        lexer.nextToken();
        ifStmt.setExpr(expr());
        if (lexer.token == Symbol.RIGHTPAR) {
          lexer.nextToken();
          if (lexer.token == Symbol.LEFTCURLY) {
            lexer.nextToken();
            while(lexer.token != Symbol.RIGHTCURLY) {
              ifStmt.addTopStmt(stmt());
            }
            if (lexer.token == Symbol.RIGHTCURLY) {
              lexer.nextToken();
              if (lexer.token == Symbol.ELSE) {
                lexer.nextToken();
                if (lexer.token == Symbol.LEFTCURLY) {
                  lexer.nextToken();
                  while (lexer.token != Symbol.RIGHTCURLY) {
                    ifStmt.addBottomStmt(stmt());
                  }
                  if (lexer.token == Symbol.RIGHTCURLY)
                    lexer.nextToken();
                  else
                    error.signal("Expected `}`");
                } else
                  error.signal("Expected `{`");
              }
            } else
              error.signal("Expected `}`");
          } else
            error.signal("Expected `{`");
        } else
          error.signal("Expected `)`");
      } else
        error.signal("Expected `(`");
    } else
      error.signal("Expected `if` statement");

    return ifStmt;
  }

  // WhileStmt ::= 'while' '(' Expr ')' '{' { Stmt } '}'
  private WhileStmt whileStmt() {
    WhileStmt whileStmt = new WhileStmt();
    if (lexer.token == Symbol.WHILE) {
      lexer.nextToken();
      if (lexer.token == Symbol.LEFTPAR) {
        lexer.nextToken();
        whileStmt.setExpr(expr());
        if (lexer.token == Symbol.RIGHTPAR) {
          lexer.nextToken();
          if (lexer.token == Symbol.LEFTCURLY) {
            lexer.nextToken();
            while (lexer.token != Symbol.RIGHTCURLY) {
              whileStmt.addStmt(stmt());
            }
            if (lexer.token == Symbol.RIGHTCURLY)
              lexer.nextToken();
            else
              error.signal("Expected `}`");
          } else
            error.signal("Expected `{`");
        } else
          error.signal("Expected `)`");
      } else
        error.signal("Expected `(`");
    } else
      error.signal("Expected `while` statement");

    return whileStmt;
  }

  // BreakStmt ::= 'break' ';'
  private BreakStmt breakStmt() {
    if (lexer.token == Symbol.BREAK) {
      lexer.nextToken();
      if (lexer.token == Symbol.SEMICOLON) {
        lexer.nextToken();
      } else
        error.signal("Expected `;`");
    } else
      error.signal("Expected `break` statement");

    return new BreakStmt();
  }

  // PrintStmt ::= 'print' '(' Expr { ',' Expr }')' ';'
  private PrintStmt printStmt() {
    PrintStmt printStmt = new PrintStmt();
    if (lexer.token == Symbol.PRINT) {
      lexer.nextToken();
      if (lexer.token == Symbol.LEFTPAR) {
        lexer.nextToken();
        printStmt.addExpr(expr());
        while(lexer.token == Symbol.COMMA) {
          lexer.nextToken();
          printStmt.addExpr(expr());
        }
        if (lexer.token == Symbol.RIGHTPAR) {
          lexer.nextToken();
          if (lexer.token == Symbol.SEMICOLON) {
            lexer.nextToken();
          } else
            error.signal("Expected `;`");
        } else
          error.signal("Expected `)`");
      } else
        error.signal("Expected `(`");
    } else
      error.signal("Expected `print` statement");

    return new PrintStmt();
  }

  // Expr ::= SimExpr [ RelOp Expr]
  private Expr expr() {
    Expr expr = new Expr();
    expr.setSimExpr(simExpr());
    if (lexer.token == Symbol.LT || lexer.token == Symbol.LE ||
            lexer.token == Symbol.GT || lexer.token == Symbol.GE ||
            lexer.token == Symbol.NEQ || lexer.token == Symbol.EQ) {
      expr.setRelOp(relOp());
      expr.setExpr(expr());
    }

    return expr;
  }

  // SimExpr ::= [Unary] Term { AddOp Term }
  private SimExpr simExpr() {
    SimExpr simExpr = new SimExpr();
    if (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS || lexer.token == Symbol.NOT){
      simExpr.setUnary(unary());
    }
    simExpr.setLeftTerm(term());
    while (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS){
      simExpr.addAddOp(addOp());
      simExpr.addRightTerm(term());
    }

    return simExpr;
  }

  // Term ::= Factor { MulOp Factor }
  private Term term() {
    Term term = new Term();
    term.setLeftFactor(factor());
    while (lexer.token == Symbol.MULT || lexer.token == Symbol.DIV
            || lexer.token == Symbol.MOD || lexer.token == Symbol.AND){
      term.addMulOp(mulOp());
      term.addRightFactor(factor());
    }

    return term;
  }

  // Factor ::= LValue ':=' Expr | LValue | Number | '(' Expr ')' | CharValue
  //              | 'readInteger' '(' ')' | 'readDouble' '(' ')' | 'readChar' '(' ')'
  private Factor factor() {
    Factor factor = new Factor();

    if (lexer.token == Symbol.READINTEGER || lexer.token == Symbol.READDOUBLE
            || lexer.token == Symbol.READCHAR){

      lexer.nextToken();
      if (lexer.token == Symbol.LEFTPAR) {
        lexer.nextToken();
        if (lexer.token == Symbol.RIGHTPAR) {
          lexer.nextToken();
        } else
          error.signal("Expected `)`");
      } else
        error.signal("Expected `(`");
    } else if (lexer.token == Symbol.LEFTPAR) { // '(' Expr ')'
      lexer.nextToken();
      factor.setExpr(expr());
      if (lexer.token == Symbol.RIGHTPAR) {
        lexer.nextToken();
      } else
        error.signal("Expected `)`");
    } else if (lexer.token == Symbol.DOUBLE || lexer.token == Symbol.INT) {
      factor.setNumber(number());
    } else {
      LValue l = lValue();
      factor.setLValue(l); // LValue
      if (lexer.token == Symbol.ASSIGN) { // LValue ':=' Expr
        lexer.nextToken();
        if (lexer.token == Symbol.CHAR){
          factor.setCharValue(charValue());
        } else
          factor.setExpr(expr());
        // check if lValue type is the same as expr
      } else {
        factor.setType(l.getType()); // if only lValue, factor type is lValue's
      }
    }

    return factor;
  }

  // LValue ::= Ident | Ident '[' Expr ']'
  private LValue lValue() {
    LValue lValue = new LValue(ident());
    boolean isVector = false;

    // SEM - check if variable was declared
    String name = lexer.getStringValue();
    Variable v = (Variable) symbolTable.get(name);
    if ( v == null )
      error.signal("Variable " + name + " was not declared");

    if (lexer.token == Symbol.LEFTBRACE) {
      lexer.nextToken();
      isVector = true;

      // SEM - check if int inside [] and assign type to expr
      if (lexer.token != Symbol.INT)
        error.signal("Invalid value for array dimension");
      lValue.setExpr(expr().setType(StdType.intType));

      if (lexer.token == Symbol.RIGHTBRACE) {
        lexer.nextToken();
      } else
        error.signal("Expected `}`");
    }

    // setting a type to this lValue
    Type t = StdType.fromVariable(v);
    lValue.setType(t);
    if (isVector)
      lValue.setType(ArrayType.fromStdType(t));

    return lValue;
  }

  // Ident ::= Letter { '_' | Letter | Digit }
  private Ident ident() {
    if (lexer.token != Symbol.IDENT) {
      error.signal("Identifier expected here. Instead, got: " + lexer.token);
    }
    String oldValue = lexer.getStringValue();
    lexer.nextToken();
    // check if it exists on the var hash table
    return new Ident(oldValue);
  }

  // Number ::= [ '+' | '-' ] IntNumber [ '.' IntNumber ]
  private Number number() {
    int multip = 1;
    if (lexer.token == Symbol.MINUS) {
      lexer.nextToken();
      multip *= -1;
    } else if (lexer.token == Symbol.PLUS){
      lexer.nextToken();
    }

    if (lexer.token != Symbol.DOUBLE && lexer.token != Symbol.INT){
      error.signal("Expected number type here");
    }
    double oldValue = lexer.getNumberValue();
    lexer.nextToken();
    return new Number(multip * oldValue);
  }

  // IntNumber ::= Digit { Digit }
  private IntNumber intNumber() {
    if (lexer.token != Symbol.INT){
      error.signal("Expected integer type here");
    }
    return new IntNumber(lexer.getNumberValue());
  }

  // CharValue ::= '\'' Letter '\''
  private CharValue charValue() {
    if (lexer.token == Symbol.CHAR){
      lexer.nextToken();
    }

    return new CharValue(lexer.getCharValue());
  }

  // RelOp ::= '=' | '!=' | '<' | '<=' | '>' | '>='
  private RelOp relOp() {
    RelOp relOp = null;
    if (lexer.token == Symbol.LT || lexer.token == Symbol.LE ||
            lexer.token == Symbol.GT || lexer.token == Symbol.GE ||
            lexer.token == Symbol.NEQ || lexer.token == Symbol.EQ){
      relOp = new RelOp(lexer.getStringValue());
      lexer.nextToken();
    } else
      error.signal("Expected relational operator");
    return relOp;
  }

  // AddOp ::= '+' | '-' | '||'
  private AddOp addOp() {
    AddOp addOp = null;
    if (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS || lexer.token == Symbol.OR){
      addOp = new AddOp(lexer.getStringValue());
      lexer.nextToken();
    } else
      error.signal("Expected addition operator");
    return addOp;
  }

  // MulOp ::= '*' | '/' | '%' | '&&'
  private MulOp mulOp() {
    MulOp mulOp = null;
    if (lexer.token == Symbol.MULT || lexer.token == Symbol.DIV
            || lexer.token == Symbol.MOD || lexer.token == Symbol.AND){
      mulOp = new MulOp(lexer.getStringValue());
      lexer.nextToken();
    } else
      error.signal("Expected mult operator");
    return mulOp;
  }

  // Unary ::= '+' | '-' | '!'
  private Unary unary() {
    Unary un = null;
    if (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS || lexer.token == Symbol.NOT){
      un = new Unary(lexer.getStringValue());
      lexer.nextToken();
    } else
      error.signal("Expected unary operator");
    return un;
  }

  // Digit ::= '0' | '1' | ... | '9'
  // LEXER

  // Letter ::= 'A' | 'B' | ... | 'Z' | 'a' | 'b' | ... | 'z'
  // LEXER

}
