package main.java.lexer;

import java.util.*;
import main.java.ast.*;
import main.java.compiler.*;

/**
 * 2016/01 Compiler - Federal University of São Calos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class Lexer {

  // current token
  public Symbol   token;
  private String  stringValue;
  private double  numberValue;
  private char    charValue;

  private int     tokenPos;

  // input[lastTokenPos] is the last character of the last token
  private int     lastTokenPos;

  // program given as input - source code
  private char    []input;

  // number of current line. Starts with 1
  private int lineNumber;

  private CompilerError     error;
  private static final int  MaxValueInteger = 32768;
  private static final int  MaxValueDouble = 65536;

  public Lexer(char []input, CompilerError error) {
    this.input = input;
    // add an end-of-file label to make it easy to do the lexer
    input[input.length - 1] = '\0';
    // number of the current line
    lineNumber = 1;
    tokenPos = 0;
    this.error = error;
  }

  // contains the keywords
  static private Hashtable<String, Symbol> keywordsTable;

  // this code will be executed only once for each program execution
  static {
    keywordsTable = new Hashtable<String, Symbol>();

    keywordsTable.put("eof",          Symbol.EOF);
    keywordsTable.put("Ident",        Symbol.IDENT);
    keywordsTable.put("Number",       Symbol.NUMBER);
    keywordsTable.put("char",         Symbol.CHAR);
    keywordsTable.put("void",         Symbol.VOID);
    keywordsTable.put("main",         Symbol.MAIN);
    keywordsTable.put("readDouble",   Symbol.READDOUBLE);
    keywordsTable.put("readChar",     Symbol.READCHAR);
    keywordsTable.put("readInteger",  Symbol.READINTEGER);
    keywordsTable.put("int",          Symbol.INT);
    keywordsTable.put("double",       Symbol.DOUBLE);
    keywordsTable.put("char",         Symbol.CHARID);
    keywordsTable.put("if",           Symbol.IF);
    keywordsTable.put("else",         Symbol.ELSE);
    keywordsTable.put("while",        Symbol.WHILE);
    keywordsTable.put("break",        Symbol.BREAK);
    keywordsTable.put("print",        Symbol.PRINT);

  }

  public void nextToken() {
    char ch;

    while ((ch = input[tokenPos]) == ' ' || ch == '\r' || ch == '\t' || ch == '\n')  {
      // count the number of lines
      if (ch == '\n')
        lineNumber++;
      tokenPos++;
    }
    if (ch == '\0')
      token = Symbol.EOF;
    else if (input[tokenPos] == '/' && input[tokenPos + 1] == '/') { // line coment
      // comment found
      while (input[tokenPos] != '\0'&& input[tokenPos] != '\n')
        tokenPos++;
      nextToken();
    }
    else if(input[tokenPos] == '/' && input[tokenPos + 1] == '*') { // block comment
      tokenPos += 2;
      while (!(input[tokenPos] == '*' && input[tokenPos + 1] == '/')){
        tokenPos++;
      }
      tokenPos += 2;
      nextToken();
    } else {
      if (isLetter(ch)) {
        // get an identifier, keyword or read function
        StringBuffer ident = new StringBuffer();
        while (isLetter(input[tokenPos]) || Character.isDigit(input[tokenPos])
                || input[tokenPos] == '_') {
          ident.append(input[tokenPos]);
          tokenPos++;
        }
        stringValue = ident.toString();
        // if identStr is in the list of keywords, it is a keyword!
        Symbol value = keywordsTable.get(stringValue);
        if (value == null)
          token = Symbol.IDENT;
        else
          token = value; // keyword identifier

        tokenPrinter();
      }
      else if (Character.isDigit(ch)) {
        // get a number
        StringBuffer number = new StringBuffer();
        boolean isDouble = false;
        while (Character.isDigit(input[tokenPos]) || input[tokenPos] == '.') {
          number.append(input[tokenPos]);
          if (input[tokenPos] == '.') isDouble = true;
          tokenPos++;
        }

        try {
          if (isDouble) {
            token = Symbol.DOUBLE;
            numberValue = Double.valueOf(number.toString()).intValue();
            if (numberValue >= MaxValueDouble)
              error.signal("Number out of limits");
          }
          else {
            token = Symbol.INT;
            numberValue = Double.valueOf(number.toString()).intValue();
            if (numberValue >= MaxValueInteger)
              error.signal("Number out of limits");
          }
        } catch (NumberFormatException e) {
          error.signal("Number out of limits");
        }

        tokenPrinter();
      } else if (input[tokenPos] == '\''){
        token = Symbol.CHAR;
        charValue = input[tokenPos + 1];
        if (input[tokenPos + 2] != '\''){
          error.signal("Char malformed");
        }
        tokenPos += 3;

        tokenPrinter();
      } else {
        tokenPos++;
        switch (ch) {
          case '+':
            token = Symbol.PLUS;
            break;
          case '-':
            token = Symbol.MINUS;
            break;
          case '*':
            token = Symbol.MULT;
            break;
          case '/':
            token = Symbol.DIV;
            break;
          case '%':
            token = Symbol.MOD;
            break;
          case '<':
            if (input[tokenPos] == '=') {
              tokenPos++;
              token = Symbol.LE; // <=
            } else
              token = Symbol.LT; // <
            break;
          case '>':
            if (input[tokenPos] == '=') {
              tokenPos++;
              token = Symbol.GE; // >=
            } else
              token = Symbol.GT; // >
            break;
          case '=':
            token = Symbol.EQ;
            break;
          case '!':
            if (input[tokenPos] == '='){
              tokenPos++;
              token = Symbol.NEQ;
            } else
              token = Symbol.NOT;
            break;
          case '|':
            if (input[tokenPos] == '|'){
              tokenPos++;
              token = Symbol.OR;
            } else
              error.signal("Illegal logical operator. Expected `||`, but got:" + ch );
            break;
          case '&':
            if (input[tokenPos] == '&'){
              tokenPos++;
              token = Symbol.AND;
            } else
              error.signal("Illegal logical operator. Expected `&&`, but got:" + ch );
            break;
          case '(':
            token = Symbol.LEFTPAR;
            break;
          case ')':
            token = Symbol.RIGHTPAR;
            break;
          case '[':
            token = Symbol.LEFTBRACE;
            break;
          case ']':
            token = Symbol.RIGHTBRACE;
            break;
          case '{':
            token = Symbol.LEFTCURLY;
            break;
          case '}':
            token = Symbol.RIGHTCURLY;
            break;
          case ',':
            token = Symbol.COMMA;
            break;
          case ';':
            token = Symbol.SEMICOLON;
            break;
          case ':':
            if (input[tokenPos] == '='){
              tokenPos++;
              token = Symbol.ASSIGN;
            } else
              error.signal("Illegal assign operator. Expected `:=`, but got:" + ch );
            break;
          default :
            error.signal("Invalid Character: '" + ch + "'");
        }

        tokenPrinter();
      }
    }
    lastTokenPos = tokenPos - 1;

  }

  // return the line number of the last token got with getToken()
  public int getLineNumber() {
    return lineNumber;
  }

  public String getCurrentLine() {
    int i = lastTokenPos;
    if ( i == 0 )
      i = 1;
    else
      if (i >= input.length)
        i = input.length;

    StringBuffer line = new StringBuffer();
    // go to the beginning of the line
    while (i >= 1 && input[i] != '\n')
      i--;
    if (input[i] == '\n')
      i++;
    // go to the end of the line putting it in variable line
    while (input[i] != '\0' && input[i] != '\n' && input[i] != '\r') {
      line.append( input[i] );
      i++;
    }
    return line.toString();
  }

  private void tokenPrinter() {
    if (token == Symbol.IDENT) {
      System.out.print("Ident ");
    } else if (token == Symbol.NUMBER) {
      System.out.print("Number ");
    } else if (token == Symbol.CHAR) {
      System.out.print("Char ");
    } else if (token == Symbol.VOID) {
      System.out.print("void ");
    } else if (token == Symbol.MAIN) {
      System.out.print("main ");
    } else if (token == Symbol.PLUS) {
      System.out.print("+ ");
    } else if (token == Symbol.MINUS) {
      System.out.print("- ");
    } else if (token == Symbol.MULT) {
      System.out.print("* ");
    } else if (token == Symbol.DIV) {
      System.out.print("/ ");
    } else if (token == Symbol.LT) {
      System.out.print("< ");
    } else if (token == Symbol.LE) {
      System.out.print("<= ");
    } else if (token == Symbol.GT) {
      System.out.print("> ");
    } else if (token == Symbol.GE) {
      System.out.print(">= ");
    } else if (token == Symbol.NEQ) {
      System.out.print("!= ");
    } else if (token == Symbol.EQ) {
      System.out.print("= ");
    } else if (token == Symbol.OR) {
      System.out.print("|| ");
    } else if (token == Symbol.AND) {
      System.out.print("&& ");
    } else if (token == Symbol.MOD) {
      System.out.print("% ");
    } else if (token == Symbol.NOT) {
      System.out.print("! ");
    } else if (token == Symbol.SEMICOLON) {
      System.out.print("; ");
    } else if (token == Symbol.DOT) {
      System.out.print(". ");
    } else if (token == Symbol.LEFTPAR) {
      System.out.print("( ");
    } else if (token == Symbol.RIGHTPAR) {
      System.out.print(") ");
    } else if (token == Symbol.LEFTCURLY) {
      System.out.print("{ ");
    } else if (token == Symbol.RIGHTCURLY) {
      System.out.print("} ");
    } else if (token == Symbol.LEFTBRACE) {
      System.out.print("[ ");
    } else if (token == Symbol.RIGHTBRACE) {
      System.out.print("] ");
    } else if (token == Symbol.ASSIGN) {
      System.out.print(":= ");
    } else if (token == Symbol.COMMA) {
      System.out.print(", ");
    } else if (token == Symbol.READINTEGER) {
      System.out.print("readInteger ");
    } else if (token == Symbol.READDOUBLE) {
      System.out.print("readDouble ");
    } else if (token == Symbol.READCHAR) {
      System.out.print("readChar ");
    } else if (token == Symbol.INT) {
      System.out.print("int ");
    } else if (token == Symbol.CHARID) {
      System.out.print("char ");
    } else if (token == Symbol.DOUBLE) {
      System.out.print("double ");
    } else if (token == Symbol.IF) {
      System.out.print("if ");
    } else if (token == Symbol.ELSE) {
      System.out.print("else ");
    } else if (token == Symbol.WHILE) {
      System.out.print("while ");
    } else if (token == Symbol.BREAK) {
      System.out.print("break ");
    } else if (token == Symbol.PRINT) {
      System.out.print("print ");
    }
  }

  public String getStringValue() {
    return stringValue;
  }

  public double getNumberValue() {
    return numberValue;
  }

  public char getCharValue() {
    return charValue;
  }

  public void setNumberValue(double newNumber) {
    numberValue = newNumber;
  }

  private boolean isLetter(char c) {
    if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
      return true;
    return false;
  }
}
