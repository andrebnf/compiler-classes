package main.java.lexer;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public enum Symbol {

  EOF         ("eof"),
  IDENT       ("Ident"),
  NUMBER      ("Number"),
  CHAR        ("char"),
  VOID        ("void"),
  MAIN        ("main"),
  PLUS        ("+"),
  MINUS       ("-"),
  MULT        ("*"),
  DIV         ("/"),
  LT          ("<"),
  LE          ("<="),
  GT          (">"),
  GE          (">="),
  NEQ         ("!="),
  EQ          ("="),
  OR          ("||"),
  AND         ("&&"),
  MOD         ("%"),
  NOT         ("!"),
  SEMICOLON   (";"),
  DOT         ("."),
  LEFTPAR     ("("),
  RIGHTPAR    (")"),
  LEFTCURLY   ("{"),
  RIGHTCURLY  ("}"),
  LEFTBRACE   ("["),
  RIGHTBRACE  ("]"),
  ASSIGN      (":="),
  COMMA       (","),
  READINTEGER ("int"),
  READDOUBLE  ("double"),
  READCHAR    ("char"),
  INT         ("int"),
  DOUBLE      ("double"),
  IF          ("if"),
  ELSE        ("else"),
  WHILE       ("while"),
  BREAK       ("break"),
  PRINT       ("print");

  Symbol(String name) {
    this.name = name;
  }

  public String toString() {
    return name;
  }

  private String name;

}
