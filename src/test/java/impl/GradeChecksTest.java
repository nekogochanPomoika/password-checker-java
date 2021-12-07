package impl;

import nekogochan.GradeCheck;
import nekogochan.impl.GradeChecks;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradeChecksTest {


  @Test
  void length() {
    var check = GradeChecks.length();
    assertEquals(10, check.check(" ".repeat(10)));
  }

  @Test
  void lowercaseCount() {
    var check = GradeChecks.lowercaseCount();
    assertEquals(4, check.check("soME StRInG"));
  }

  @Test
  void uppercaseCount() {
    var check = GradeChecks.uppercaseCount();
    assertEquals(4, check.check("SOme stRIng"));
  }

  @Test
  void digitsCount() {
    var check = GradeChecks.digitCount();
    assertEquals(4, check.check("some string 1234"));
  }

  @Test
  void specialsCount() {
    var check = GradeChecks.specialsCount();
    assertEquals(4, check.check("some string !@#$"));
  }

  @Test
  void matchesCount() {
    var check = GradeChecks.matchesCount("a");
    assertEquals(4, check.check("aboba aboba"));
  }
}
