package main.java.compiler;

import main.java.lexer.*;
import java.io.*;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class CompilerError {

  public CompilerError(PrintWriter out) {
    // output of an error is done in out
    this.out = out;
  }

  public void setLexer(Lexer lexer) {
    this.lexer = lexer;
  }

  public void signal( String strMessage ) {
    out.println("Error at line " + lexer.getLineNumber() + ": ");
    out.println(lexer.getCurrentLine());
    out.println(strMessage);
    if (out.checkError())
      System.out.println("Error in signaling an error");
    throw new RuntimeException(strMessage);
  }

  private Lexer lexer;
  PrintWriter out;
}
