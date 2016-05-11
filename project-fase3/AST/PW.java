package AST;

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
    if (linePrinted == 1) {
      out.print(space.substring(0, currentIndent));
      linePrinted = 0;
    }
    out.print(s);
  }

  public void println(String s) {
    if (linePrinted == 1) {
      out.print(space.substring(0, currentIndent));
    }
    out.println(s);
    linePrinted = 1;
  }

  private int currentIndent = 0;
  private int linePrinted = 0;

  public int step = 2;
  public PrintWriter out;

  static final private String space = "                                                                                                        ";

}
