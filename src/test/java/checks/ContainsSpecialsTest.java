package checks;

import nekogochan.Check;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainsSpecialsTest {

  Check check = Check.containsSpecials();

  @Test
  void true_ifContainsAnySpecial() {
    var s = "1_aA";
    assertTrue(check.test(s));
  }

  @Test
  void false_ifNoneContainsSpecials() {
    var s = "1Aa";
    assertFalse(check.test(s));
  }
}
