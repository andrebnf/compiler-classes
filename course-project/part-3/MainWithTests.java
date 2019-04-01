import AST.*;

import java.io.*;
import java.util.ArrayList;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class MainWithTests {

  public static void main(String []args) {

    File file;
    FileReader stream;
    int numChRead;
    Program program;

    ArrayList<String> files = new ArrayList<String>();

    files.add("ERRLEX01.txt");
    files.add("ERRLEX02.txt");
    files.add("ERRLEX03.txt");
    files.add("ERRLEX04.txt");
    files.add("ERRLEX05.txt");
    files.add("ERRLEX06.txt");
    files.add("ERRLEX07.txt");
    files.add("ERRLEX08.txt");
    files.add("ERRLEX09.txt");
    files.add("ERRSEM01.txt");
    files.add("ERRSEM02.txt");
    files.add("ERRSEM03.txt");
    files.add("ERRSEM04.txt");
    files.add("ERRSEM05.txt");
    files.add("ERRSEM06.txt");
    files.add("ERRSEM07.txt");
    files.add("ERRSEM08.txt");
    files.add("ERRSEM09.txt");
    files.add("ERRSEM10.txt");
    files.add("ERRSEM11.txt");
    files.add("ERRSEM12.txt");
    files.add("ERRSEM13.txt");
    files.add("ERRSEM14.txt");
    files.add("ERRSEM15.txt");
    files.add("ERRSEM16.txt");
    files.add("ERRSEM17.txt");
    files.add("OKLEX01.txt");
    files.add("OKLEX02.txt");
    files.add("OKLEX03.txt");
    files.add("OKSEM01.txt");
    files.add("OKSEM02.txt");

    for (String file_name : files) {

      file = new File("Testes/" + file_name);
      if ( ! file.exists() || ! file.canRead() ) {
        System.out.println("Either the file " + file_name + " does not exist or it cannot be read");
        throw new RuntimeException();
      }
      try {
        stream = new FileReader(file);
      } catch ( FileNotFoundException e ) {
        System.out.println("Something wrong: file does not exist anymore");
        throw new RuntimeException();
      }
      // one more character for '\0' at the end that will be added by the
      // error
      char []input = new char[ (int ) file.length() + 1 ];

      try {
        numChRead = stream.read( input, 0, (int ) file.length() );
      } catch ( IOException e ) {
        System.out.println("Error reading file " + file_name);
        throw new RuntimeException();
      }

      try {
        stream.close();
      } catch ( IOException e ) {
        System.out.println("Error in handling the file " + file_name);
        throw new RuntimeException();
      }

      Compiler compiler = new Compiler();
      FileOutputStream  outputStream;
      try {
        outputStream = new FileOutputStream("Saidas/" + file_name);
      } catch ( IOException e ) {
        System.out.println("File " + file_name + " could not be opened for writing");
        throw new RuntimeException();
      }
      PrintWriter printWriter = new PrintWriter(outputStream);
      program = null;
      // the generated code goes to a file and so are the errors
      try {
        program  = compiler.compile(input, printWriter);
      } catch ( RuntimeException e ) {
        System.out.println(e);
      }
      if ( program != null ) {
        PW pw = new PW();
        pw.set(printWriter);
        program.genC(pw);
        if ( printWriter.checkError() ) {
          System.out.println("There was an error in the output");
        }
      }
    }
  }
}
