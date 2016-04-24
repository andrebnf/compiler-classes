package test.java;

import main.java.ast.Program;
import main.java.compiler.Compiler;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by andrebnf on 4/23/16.
 */
public class compilerTest {

  @Rule
  public ExpectedException errorException = ExpectedException.none();

  @Test
  public void testShouldCompile1() {
    char []input ="v m () {}".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile2() {
    char []input ="v m () { i a; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile3() {
    char []input ="v m () { d[] a; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile4() {
    char []input ="v m () { i a; d h; c j; i[] g; d[] k; c[] n; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile5() {
    char []input ="v m () { b; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile6() {
    char []input ="v m () { b; b; b; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile7() {
    char []input ="v m () { i[] a; i[] j; s(); b; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile8() {
    char []input ="v m () { f(a) { b; } }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile9() {
    char []input ="v m () { f (a) { b; } e { b; } }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile10() {
    char []input ="v m () { w (a) { b; } }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile11() {
    char []input ="v m () { f (a) { b; p(a, j, k) } e { b; p(a, j, k) } }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile12() {
    char []input ="v m () { f (a) { } }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile13() {
    char []input ="v m () { w (a) { } }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile14() {
    char []input ="v m () { p (a) }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile15() {
    char []input ="v m () { p (a, j, j - k, -a) }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile16() {
    char []input ="v m () { a : j; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile17() {
    char []input ="v m () { a[k]; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile18() {
    char []input ="v m () { a[j[k]]; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile19() {
    char []input ="v m () { t(); r(); s(); r(); t(); }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile20() {
    char []input ="v m () { (a + j); }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile21() {
    char []input ="v m () { a % j; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile22() {
    char []input ="v m () { f(! a) { b; } }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile23() {
    char []input ="v m () { a > j; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile24() {
    char []input ="v m () { i a; i j; i k; a : j + k; a : j - k; a : j / k; k : a % j; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile25() {
    char []input ="v m () { i a; i j; i k; i n; i q; a[n] : j[n] + k[n] - q[n] / a[n]; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile26() {
    char []input ="v m () { i a; a : +a; a : -a; a : ! a; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile27() {
    char []input ="v m () { i a; i j; i k; a : j > k; a : j < k; a : j # k; a : j = k; }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile28() {
    char []input ="v m () { f (a + j) { r(); s(); a: (a + (j)); } }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile29() {
    char []input ="v m () { i a; p (a, h, j, k, (a + h), j - h) }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldCompile30() {
    char []input ="v m () { i a; w (a < j) { a : a - j; a : j - k; r(); b; } }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShoudCompile31() {
    char []input ="v m () { i a; i j; i k; i n; a : a + (j -(a % k)); a : a + ((j + k) - (j / k)); }".toCharArray();

    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile1() throws Exception {
    errorException.expect(RuntimeException.class);
    errorException.expectMessage("Error at \"x y () {}");

    char []input ="x y () {}".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile2() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () {".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile3() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m ( {}".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile4() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () a : a + j; }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile5() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m (a) {}".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile6() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="a : j[k];".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile7() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { i a }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile8() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { c; } ".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile9() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { d j[]; }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile10() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { d[ j; }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile11() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { f ( a { b; } }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile12() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { f ( a ) { b; ) }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile13() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { w ( a ) { b; ) }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile14() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { w ( a ) b; } }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile15() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { f ( a ) { w(a) { b; } }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile16() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { f (a) { b; } g { b; } }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile17() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { b b;}".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile18() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { p(a, j,) }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile19() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { p(a, j, k } }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile20() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { (a + j; }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile21() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { a : a + j }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile22() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { i a; > (a + j); }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile23() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { a : !; }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile24() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { a[k : j; }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile25() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { r(]; }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile26() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { s(; }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile27() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { (a (a+j); }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

  @Test
  public void testShouldNotCompile28() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { i ç; }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

//  @Test
//  public void testShouldNotCompile29() throws Exception {
//    errorException.expect(RuntimeException.class);
//
//    char []input ="v m () { i r; }".toCharArray();
//    Compiler compiler = new Compiler();
//    Program program  = compiler.compile(input);
//  }

  @Test
  public void testShouldNotCompile30() throws Exception {
    errorException.expect(RuntimeException.class);

    char []input ="v m () { i a; i j; i k; a : j + k; j : a + k - a - j }".toCharArray();
    Compiler compiler = new Compiler();
    Program program  = compiler.compile(input);
  }

}

