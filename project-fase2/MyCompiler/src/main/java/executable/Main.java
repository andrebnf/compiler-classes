package main.java.executable;
import main.java.ast.*;
import main.java.compiler.Compiler;

import java.io.*;

/**
 * 2016/01 Compiler - Federal University of São Carlos - Sorocaba Campus
 * @author  André Bonfatti, 408182
 * @author  Thales Chagas,  408557
 */

public class Main {

  public static void main(String []args) {
    File file;
    FileReader stream;
    int numChRead;
    Program program;

    if (args.length != 1)  {
      System.out.println("Usage:\n\tMain <input>");
      System.out.println("input is the file to be compiled");
    }
    else {
      file = new File(args[0]);
      if (! file.exists() || ! file.canRead()) {
        System.out.println("Either the file " + args[0] + " does not exist or it cannot be read");
        throw new RuntimeException();
      }
      try {
        stream = new FileReader(file);
      } catch (FileNotFoundException e) {
        System.out.println("Something wrong: file does not exist anymore");
        throw new RuntimeException();
      }
      // one more character for '\0' at the end that will be added by the
      // compiler
      char []input = new char[(int) file.length() + 1];

      try {
        numChRead = stream.read(input, 0, (int) file.length());
      } catch (IOException e) {
        System.out.println("Error reading file " + args[0]);
        throw new RuntimeException();
      }

      if (numChRead != file.length()) {
        System.out.println("Read error");
        throw new RuntimeException();
      }
      try {
        stream.close();
      } catch (IOException e) {
        System.out.println("Error in handling the file " + args[0]);
        throw new RuntimeException();
      }

      Compiler compiler = new Compiler();

      PrintWriter printWriter = new PrintWriter(System.out);
      program = null;
      // the generated code goes to a file and so are the errors
      try {
        program  = compiler.compile(input, printWriter);
      } catch (RuntimeException e) {
        System.out.println(e);
      }

    }
  }
}
