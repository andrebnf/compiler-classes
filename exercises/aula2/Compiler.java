//    comp2 - aula 2 (LE)

/*
EE ::= TT EL
EL ::= + TT EL |
TT ::= FF TL
TL ::= * FF TL |
FF ::= number | ( EE )


Example of program:
EE::=FF    EL    EL::=TT    TT
5         + 4     * 9     * (E)


@author André Bonfatti (andrebnf@gmail.com)
*/


public class Compiler {

  public int compile(char []p_input) {
    int eval;
    input = p_input;
    tokenPos = 0;
    nextToken();

    eval = EE();

    if ( token != '\0' )
      error();

    return eval;
  }

  // E ::= T E'
  private int EE() {
    int esq;
    int dir;
    esq = TT();
    dir = EL(esq);

    return dir;
  }

  // E' ::= + T E' |
  private int EL(int esq) {
    int esq_aux;
    int dir = 0;
    if (token == '+') {
      // System.out.print(token);
      nextToken();
      esq_aux = TT();
      dir = EL(esq_aux);
    }
    return esq + dir;
  }

  // T ::= F T'
  private int TT() {
    int esq;
    int dir;
    esq = FF();
    dir = TL(esq);

    return dir;
  }

  // T' ::= * F T' |
  private int TL(int esq) {
    int esq_aux;
    int dir = 1;
    if (token == '*') {
      // System.out.print(token);
      nextToken();
      esq_aux = FF();
      dir = TL(esq_aux);
    }
    return esq * dir;
  }

  // F ::= Numero | (E)
  private int FF() {
    int res;
    if (token == '(') {
      nextToken();
      res = EE();
      if (token != ')') {
        error();
      }
      nextToken();
      return res;
    } else {
      return number();
    }
  }

  //number ::= '0'| '1' | ... | '9'
  private int number() {
    int value = 0;
    if(token >= '0' && token <= '9') {
      value = token - '0';
      nextToken();
    }
    else
    error();

    return value;
  }

  private void nextToken() {

    while(tokenPos < input.length && input[tokenPos] == ' '){
      tokenPos++;
    }

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
