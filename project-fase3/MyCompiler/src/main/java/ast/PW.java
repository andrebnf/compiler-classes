package main.java.ast;

import java.io.PrintWriter;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class PW {

  public void add() {
    currentIndent += step;
  }
  public void sub() {
    currentIndent -= step;
  }

  public void set(PrintWriter out) {
    this.out = out;
    currentIndent = 0;
  }

  public void set(int indent) {
    currentIndent = indent;
  }

  public void print(String s) {
    out.print(space.substring(0, currentIndent));
    out.print(s);
  }

  public void println(String s) {
    out.print(space.substring(0, currentIndent));
    out.println(s);
  }

  int currentIndent = 0;
  /* there is a Java and a Green mode.
     indent in Java mode:
     3 6 9 12 15 ...
     indent in Green mode:
     3 6 9 12 15 ...
  */
  static public final int green = 0, java = 1;
  int mode = java;
  public int step = 2;
  public PrintWriter out;


  static final private String space = "                                                                                                        ";

}
