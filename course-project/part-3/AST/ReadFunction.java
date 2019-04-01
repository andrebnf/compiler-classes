package AST;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class ReadFunction {

  private static final int READCHAR = 1;
  private static final int READINT = 2;
  private static final int READDOUBLE = 3;

  private int readType;
  private String ident;

  public static ReadFunction setReadChar(String ident) {
    return new ReadFunction(READCHAR, ident);
  }

  public static ReadFunction setReadInt(String ident) {
    return new ReadFunction(READINT, ident);
  }

  public static ReadFunction setReadDouble(String ident) {
    return new ReadFunction(READDOUBLE, ident);
  }

  public ReadFunction(int readType, String ident) {
    this.readType = readType;
    this.ident = ident;
  }

  public void genC(PW pw){
    switch (readType) {
      case READCHAR:
        pw.print("scanf(\"%c\", "+ ident +")");
        break;
      case READINT:
        pw.print("scanf(\"%d\", "+ ident +")");
        break;
      case READDOUBLE:
        pw.print("scanf(\"%f\", "+ ident +")");
        break;
    }
  }

}
