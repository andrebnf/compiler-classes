import AST.*;
import AST.Number;
import Error.CompilerError;
import Lexer.*;

import java.io.PrintWriter;
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
StdType      ::= 'int' | 'double' | 'char'
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
  private CompilerError error;
  private Lexer   lexer;
  private int     breakableCount;
  private int     lineExpressionsCount;
  private int     arrayTypeAssign;
  private String  varIdentBuffer;

  public Program compile(char []input, PrintWriter outError) {
    breakableCount = 0;
    lineExpressionsCount = 0;
    arrayTypeAssign = 0;
    varIdentBuffer = "";

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

    if (arrayTypeAssign > 0) {
      arrayTypeAssign--;
      variable.setArrayVariable();

      // SEM - check array size
      if (lexer.getIntValue() <= 0)
        error.signal("Invalid array size");

      variable.setArraySize(lexer.getIntValue());
    }

    symbolTable.put(name, variable);
    return variable;
  }

  // Type ::= StdType | ArrayType
  private Type type() {
    Type type = null;
    if (lexer.token == Symbol.INT || lexer.token == Symbol.DOUBLE || lexer.token == Symbol.CHAR){
      type = stdType();
      if (lexer.token == Symbol.LEFTBRACE) {
        arrayType();
        ArrayType arr = ArrayType.fromStdType(type);
        type = arr;
      }
    } else
      error.signal("Expected standard type declaration");

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
        arrayTypeAssign++;
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
    lineExpressionsCount = 0;

    if (lexer.token == Symbol.IF) {
      stmt = ifStmt();
      lineExpressionsCount = 2;
    } else if (lexer.token == Symbol.WHILE) {
      stmt = whileStmt();
      lineExpressionsCount = 2;
    } else if (lexer.token == Symbol.BREAK) {
      stmt = breakStmt();
      lineExpressionsCount = 2;
    } else if (lexer.token == Symbol.PRINT) {
      stmt = printStmt();
      lineExpressionsCount = 2;
    } else {
      stmt = expr();

      // SEM - check if only one expression w/o assign or read funcions in a line
      if (lineExpressionsCount == 1)
        error.signal("Assign expected.");

      lineExpressionsCount = 0;
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

        // SEM - is breakable statement (can break inside if breakableCount > 0)
        breakableCount++;

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
    if (--breakableCount < 0)
      error.signal("Can't use keyword `break` in a non-breakable context");

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
    Expr rightExpr = null;
    SimExpr simExpr = simExpr();
    expr.setSimExpr(simExpr);
    expr.setType(simExpr.getType());
    if (lexer.token == Symbol.LT || lexer.token == Symbol.LE ||
            lexer.token == Symbol.GT || lexer.token == Symbol.GE ||
            lexer.token == Symbol.NEQ || lexer.token == Symbol.EQ) {
      expr.setRelOp(relOp());
      rightExpr = expr();
      expr.setExpr(rightExpr);

      // SEM - Incompatible types
      if (expr.getType() != rightExpr.getType())
        error.signal("expr: Incompatible types");
    }

    if (expr.getRelOp() != null){
      // SEM - if || is chained with ==. Generally, if || is not chained with &&, raise error
      if (simExpr.getLeftTerm().hasUnchainableMulOp() && simExpr.hasUnchainableAddOp())
        error.signal("Unchainable operators");
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

    // SEM - Incompatible types
    // evaluate types set simExpr type and check term types
    if (!simExpr.evaluateTypes()) {
      error.signal("simExpr: Incompatible types");
    }

    return simExpr;
  }

  // Term ::= Factor { MulOp Factor }
  private Term term() {
    Term term = new Term();
    Factor leftFactor = factor();
    term.setLeftFactor(leftFactor);
    while (lexer.token == Symbol.MULT || lexer.token == Symbol.DIV
            || lexer.token == Symbol.MOD || lexer.token == Symbol.AND){
      // SEM - % with double types
      if (leftFactor.getType() == StdType.doubleType && lexer.token == Symbol.MOD) {
        error.signal("Double type not permitted for this operation");
      }
      term.addMulOp(mulOp());
      term.addRightFactor(factor());
    }

    // SEM - Incompatible types
    // evaluate types set term type and check factors types
    if (!term.evaluateTypes()){
      error.signal("term: Incompatible types");
    }

    return term;
  }

  // Factor ::= LValue ':=' Expr | LValue | Number | '(' Expr ')' | CharValue
  //              | 'readInteger' '(' ')' | 'readDouble' '(' ')' | 'readChar' '(' ')'
  private Factor factor() {
    Factor factor = new Factor();

    // 'readInteger' '(' ')' | 'readDouble' '(' ')' | 'readChar' '(' ')'
    if (lexer.token == Symbol.READINTEGER || lexer.token == Symbol.READDOUBLE
            || lexer.token == Symbol.READCHAR) {

      // SEM - incrementing lineExpressionsCount: checked afterwards to analise if
      //       only one expression is present on line
      lineExpressionsCount++;

      // SEM - read function is beign assigned
      if (varIdentBuffer.isEmpty())
        error.signal("Could'nt find variable to be assigned with read function.");

      // SEM - set types and set readFunction context
      if (lexer.token == Symbol.READINTEGER){
        factor.setType(StdType.intType);
        factor.setReadFunction(ReadFunction.setReadInt(varIdentBuffer));
      } else if (lexer.token == Symbol.READDOUBLE){
        factor.setType(StdType.doubleType);
        factor.setReadFunction(ReadFunction.setReadDouble(varIdentBuffer));
      } else { // if (lexer.token == Symbol.READCHAR)
        factor.setType(StdType.charType);
        factor.setReadFunction(ReadFunction.setReadChar(varIdentBuffer));
      }
      varIdentBuffer = "";

      lexer.nextToken();
      if (lexer.token == Symbol.LEFTPAR) {
        lexer.nextToken();
        if (lexer.token == Symbol.RIGHTPAR) {
          lexer.nextToken();
        } else
          error.signal("Expected `)`");
      } else
        error.signal("Expected `(`");
    }

    // '(' Expr ')'
    else if (lexer.token == Symbol.LEFTPAR) {

      // SEM - incrementing lineExpressionsCount: checked afterwards to analise if
      //       only one expression is present on line
      lineExpressionsCount++;

      lexer.nextToken();
      Expr expr = expr();
      factor.setExpr(expr);
      factor.setType(expr.getType()); // SEM

      if (lexer.token == Symbol.RIGHTPAR) {
        lexer.nextToken();
      } else
        error.signal("Expected `)`");
    }

    // Number
    else if (lexer.token == Symbol.DOUBLE || lexer.token == Symbol.INT) {

      if (lexer.token == Symbol.DOUBLE) {
        factor.setType(StdType.doubleType);
      }
      else {
        factor.setType(StdType.intType);
      }

      factor.setNumber(number());
    }

    // CharValue
    else if (lexer.token == Symbol.CHAR) {

      factor.setCharValue(charValue());
      factor.setType(StdType.charType);
    }

    // LValue | LValue ':=' Expr
    else {
      varIdentBuffer = "";

      // LValue
      LValue l = lValue();
      Type rType = null;
      factor.setLValue(l);

      // LValue ':=' Expr
      if (lexer.token == Symbol.ASSIGN) {

        // SEM - incrementing lineExpressionsCount: checked afterwards to analise if
        //       only one expression is present on line
        lineExpressionsCount += 2;

        lexer.nextToken();
        Expr expr = expr();
        factor.setExpr(expr);
        rType = expr.getType();

        // SEM - Incompatible types
        // check if lValue type is the same as expr
        if (l.getType() != rType) {
          error.signal("factor: Incompatible types");
        }
      }

      factor.setType(l.getType());
    }

    return factor;
  }

  // LValue ::= Ident | Ident '[' Expr ']'
  private LValue lValue() {
    LValue lValue = new LValue(ident());
    boolean isVector = false;

    // SEM - check if variable was declared
    String name = lexer.getStringValue();
    Variable v = symbolTable.get(name);
    if (v == null)
      error.signal("Variable " + name + " was not declared");

    varIdentBuffer = name;

    if (lexer.token == Symbol.LEFTBRACE) {
      lexer.nextToken();
      isVector = true;
      Expr expr = expr();
      // SEM - check if int inside [] and assign type to expr
      if (expr.getType() != StdType.intType) {
        error.signal("Expected `int` value resulted from expression inside `[]`");
      }
      lValue.setExpr(expr);

      if (lexer.token == Symbol.RIGHTBRACE) {
        lexer.nextToken();
      } else
        error.signal("Expected `}`");
    }

    // setting a type to this lValue
    Type t = v.getType();
    if (isVector) {
      t = StdType.fromArrayType(t);
    }

    lValue.setType(t);

    return lValue;
  }

  // Ident ::= Letter { '_' | Letter | Digit }
  private Ident ident() {
    if (lexer.token != Symbol.IDENT) {
      error.signal("Identifier expected here. Instead, got: " + lexer.token);
    }
    String oldValue = lexer.getStringValue();
    lexer.nextToken();

    return new Ident(oldValue);
  }

  // Number ::= [ '+' | '-' ] IntNumber [ '.' IntNumber ]
  private Number number() {
    String multip = "";
    if (lexer.token == Symbol.MINUS) {
      lexer.nextToken();
      multip = "-";
    } else if (lexer.token == Symbol.PLUS){
      lexer.nextToken();
    }

    if (lexer.token != Symbol.DOUBLE && lexer.token != Symbol.INT){
      error.signal("Expected number type here");
    }

    if (lexer.token == Symbol.DOUBLE){
      double oldValue = lexer.getDoubleValue();
      lexer.nextToken();
      return new Number(multip + "" + oldValue);
    } else {
      int oldValue = lexer.getIntValue();
      lexer.nextToken();
      return new Number(multip + "" + oldValue);
    }
  }

  // IntNumber ::= Digit { Digit }
  // LEXER
  //  private IntNumber intNumber() {
  //    if (lexer.token != Symbol.INT){
  //      error.signal("Expected integer type here");
  //    }
  //    return new IntNumber(lexer.getNumberValue());
  //  }

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

      if (lexer.token == Symbol.EQ)
        relOp = new RelOp("==");
      else
        relOp = new RelOp(lexer.token.toString());

      lexer.nextToken();
    } else
      error.signal("Expected relational operator");
    return relOp;
  }

  // AddOp ::= '+' | '-' | '||'
  private AddOp addOp() {
    AddOp addOp = null;
    if (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS || lexer.token == Symbol.OR){
      addOp = new AddOp(lexer.token.toString());
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
      mulOp = new MulOp(lexer.token.toString());
      lexer.nextToken();
    } else
      error.signal("Expected mult operator");
    return mulOp;
  }

  // Unary ::= '+' | '-' | '!'
  private Unary unary() {
    Unary un = null;
    if (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS || lexer.token == Symbol.NOT){
      un = new Unary(lexer.token.toString());
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
