package test.java;

import com.sun.xml.internal.fastinfoset.util.StringArray;
import main.java.ast.Program;
import main.java.compiler.Compiler;
import main.java.executable.Main;
import main.java.lexer.Symbol;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by andrebnf on 4/23/16.
 */
public class CompilerTest {

  private boolean testMain(String filePath) throws IOException {
    File file = new File(filePath);
    FileReader stream = new FileReader(file);

    char []input = new char[(int) file.length() + 1];

    int numChRead = stream.read(input, 0, (int) file.length());

    stream.close();

    Compiler compiler = new Compiler();

    PrintWriter printWriter = new PrintWriter(System.out);
    Program program = null;
    // the generated code goes to a file and so are the errors
    program  = compiler.compile(input, printWriter);

    return true;
  }

  @Rule
  public ExpectedException errorException = ExpectedException.none();

  @Test
  public void shouldCompile1() throws IOException {
    String input = "src/test/java/testFiles/shouldCompile/OKLEX01.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldCompile2() throws IOException {
    String input = "src/test/java/testFiles/shouldCompile/OKLEX02.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldCompile3() throws IOException {
    String input = "src/test/java/testFiles/shouldCompile/OKLEX03.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldCompile4() throws IOException {
    String input = "src/test/java/testFiles/shouldCompile/OKSEM01.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldCompile5() throws IOException {
    String input = "src/test/java/testFiles/shouldCompile/OKSEM02.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile1() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRLEX01.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile2() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRLEX02.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile3() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRLEX03.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile4() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRLEX04.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile5() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRLEX05.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile6() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRLEX06.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile7() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRLEX07.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile8() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRLEX08.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile9() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRLEX09.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile10() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM01.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile11() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM02.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile12() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM03.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile13() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM04.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile14() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM05.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile15() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM06.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile16() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM07.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile17() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM08.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile18() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM09.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile19() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM10.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile20() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM11.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile21() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM12.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile22() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM13.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile23() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM14.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile24() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM15.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile25() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM16.txt";
    assertTrue(testMain(input));
  }

  @Test
  public void shouldNotCompile26() throws Exception {
    errorException.expect(RuntimeException.class);

    String input = "src/test/java/testFiles/shouldNotCompile/ERRSEM17.txt";
    assertTrue(testMain(input));
  }

}

