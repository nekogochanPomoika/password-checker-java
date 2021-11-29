package checks;

import nekogochan.StrictCheck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainsSpecialsTest {

  StrictCheck check = StrictCheck.containsSpecials();

  @Test
  void true_ifContainsAnySpecial() {
    var s = "1_aA";
    assertTrue(check.check(s));
  }

  @Test
  void false_ifNoneContainsSpecials() {
    var s = "1Aa";
    assertFalse(check.check(s));
  }
}
