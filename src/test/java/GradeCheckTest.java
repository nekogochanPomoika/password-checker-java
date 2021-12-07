import nekogochan.GradeCheck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GradeCheckTest {

  static GradeCheck simpleCheck = String::length;
  static String str = "some string";

  @Test
  void check() {
    assertEquals(str.length(), simpleCheck.check(str));
  }

  @Test
  void add() {
    var check = simpleCheck.add(10);
    assertEquals(str.length() + 10, check.check(str));
  }

  @Test
  void multiply() {
    var check = simpleCheck.multiply(2);
    assertEquals(str.length() * 2, check.check(str));
  }

  @Test
  void subtract() {
    var check = simpleCheck.subtract(5);
    assertEquals(str.length() - 5, check.check(str));
  }

  @Test
  void divide() {
    var check = simpleCheck.divide(2);
    assertEquals(str.length() / 2, check.check(str));
  }

  @Test
  void andAdd() {
    var check = simpleCheck.andAdd(s -> s.indexOf(" "));
    assertEquals(str.length() + str.indexOf(" "), check.check(str));
  }

  @Test
  void limitTop() {
    var check = simpleCheck.limitTop(4);
    assertEquals(4, check.check(" ".repeat(100)));
  }

  @Test
  void limitBottom() {
    var check = simpleCheck.limitBottom(100);
    assertEquals(100, check.check(""));
  }

  @Test
  void limit() {
    var check = simpleCheck.limit(4, 100);
    assertEquals(4, check.check(""));
    assertEquals(100, check.check(" ".repeat(200)));
  }

  @Test
  void or_1() {
    var check_true = simpleCheck.or(10, (String s) -> true);
    var check_false = simpleCheck.or(10, (String s) -> false);
    assertEquals(10, check_true.check(str));
    assertEquals(str.length(), check_false.check(str));
  }

  @Test
  void or_2() {
    var check_true = simpleCheck.or(10, (int i) -> true);
    var check_false = simpleCheck.or(10, (int i) -> false);
    assertEquals(10, check_true.check(str));
    assertEquals(str.length(), check_false.check(str));
  }

  @Test
  void orGet_1() {
    var check_true = simpleCheck.orGet(() -> 10, (String s) -> true);
    var check_false = simpleCheck.orGet(() -> 10, (String s) -> false);
    assertEquals(10, check_true.check(str));
    assertEquals(str.length(), check_false.check(str));
  }

  @Test
  void orGet_2() {
    var check_true = simpleCheck.orGet(() -> 10, (int s) -> true);
    var check_false = simpleCheck.orGet(() -> 10, (int s) -> false);
    assertEquals(10, check_true.check(str));
    assertEquals(str.length(), check_false.check(str));
  }

  @Test
  void orCheck() {
    GradeCheck check = s -> 10;
    var check_true = simpleCheck.orCheck(check, (String s) -> true);
    var check_false = simpleCheck.orCheck(check, (String s) -> false);
    assertEquals(10, check_true.check(str));
    assertEquals(str.length(), check_false.check(str));
  }

  @Test
  void withPreprocessor() {
    var check = simpleCheck.withPreprocessor(s -> s.repeat(2));
    assertEquals(str.length() * 2, check.check(str));
  }

  @Test
  void toStrict() {
    var check_true = simpleCheck.toStrict(str.length());
    var check_false = simpleCheck.toStrict(str.length() + 1);
    assertTrue(check_true.check(str));
    assertFalse(check_false.check(str));
  }
}
