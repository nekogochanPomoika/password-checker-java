package impl;

import nekogochan.impl.StrictChecks;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StrictChecksTest {

  @Test
  void length() {
    var check = StrictChecks.length(10);
    assertTrue(check.check(" ".repeat(15)));
    assertTrue(check.check(" ".repeat(10)));
    assertFalse(check.check(" "));
  }

  @Test
  void containsLowercase() {
    var check = StrictChecks.containsLowercase();
    assertTrue(check.check("somestr"));
    assertFalse(check.check("SOMESTR"));
  }

  @Test
  void containsUppercase() {
    var check = StrictChecks.containsUppercase();
    assertTrue(check.check("SOMESTR"));
    assertFalse(check.check("somestr"));
  }

  @Test
  void containsDigits() {
    var check = StrictChecks.containsDigits();
    assertTrue(check.check("somestr123"));
    assertFalse(check.check("somestr"));
  }

  @Test
  void contains_1() {
    var check = StrictChecks.contains("a");
    assertTrue(check.check("abc"));
    assertFalse(check.check("bcd"));
  }

  @Test
  void contains_2() {
    var check = StrictChecks.contains(Set.of("somestr"));
    assertTrue(check.check("somestr"));
    assertFalse(check.check("anotherstr"));
  }

  @Test
  void contains_3() {
    var check = StrictChecks.contains(Set.of("somestr"), String::equalsIgnoreCase);
    assertTrue(check.check("SomeSTr"));
    assertFalse(check.check("anotherstr"));
  }
}
