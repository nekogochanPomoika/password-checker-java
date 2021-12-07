import nekogochan.StrictCheck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StrictCheckTest {

  StrictCheck check_true = s -> s.equals("str");
  StrictCheck check_false = s -> !s.equals("str");
  String str = "str";

  @Test
  void check() {
    assertTrue(check_true.check(str));
    assertFalse(check_false.check(str));
  }

  @Test
  void and() {
    var check = check_true.and(check_false);
    assertFalse(check.check(str));
  }

  @Test
  void or() {
    var check = check_true.or(check_false);
    assertTrue(check.check(str));
  }

  @Test
  void xor() {
    var check_1 = check_true.xor(check_true);
    var check_2 = check_true.xor(check_false);
    var check_3 = check_false.xor(check_true);
    var check_4 = check_false.xor(check_false);
    assertFalse(check_1.check(str));
    assertTrue(check_2.check(str));
    assertTrue(check_3.check(str));
    assertFalse(check_4.check(str));
  }

  @Test
  void negate() {
    var check = check_true.negate();
    assertFalse(check.check(str));
  }

  @Test
  void withPreprocessor() {
    var check = check_true.withPreprocessor(s -> s + "another string");
    assertFalse(check.check(str));
  }

  @Test
  void toGrade() {
    var check = StrictCheck.of(s -> s.equals("str")).toGrade(10, 0);
    assertEquals(10, check.check("str"));
    assertEquals(0, check.check("non str"));
  }
}
