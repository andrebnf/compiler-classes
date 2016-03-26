public class Aula1 {

  /*
    compiler 1

    (+    1(* 5 3)) --> (1+(5*3))
  */

  public static void main(String[] args) {
    char []input = args[0].toCharArray();
    input = removeSpaces(input);

    int inputLength = getCharArrayLength(input);
    int recCounter = recTransform(input, 1);

    if (inputLength != recCounter + 1) {
      System.out.print("\nCompile Error");
    }

    System.out.println();
  }

  public static int getCharArrayLength(char []array) {
    int i;
    for (i = 0; array[i] != '\0'; i++);
    return i;
  }

  public static int recTransform(char []list, int i){
    char operator = ' ';

    System.out.print('(');
    operator = list[i];
    i++;
    if (list[i] == '(')
      i = recTransform(list, i+1);
    else
      System.out.print(list[i]);

    i++;
    System.out.print(operator);
    if (list[i] == '(')
      i = recTransform(list, i+1);
    else
      System.out.print(list[i]);

    i++;
    System.out.print(')');
    return i;
  }

  public static char[] removeSpaces(char []list) {
    int nonSpaceCharCounter = 0;
    char []trimmedList = new char[list.length];
    for (int i = 0; i < list.length; i++)
      if (list[i] != ' ') {
        trimmedList[nonSpaceCharCounter] = list[i];
        nonSpaceCharCounter++;
      }
    trimmedList[nonSpaceCharCounter] = '\0';
    return trimmedList;
  }
}
