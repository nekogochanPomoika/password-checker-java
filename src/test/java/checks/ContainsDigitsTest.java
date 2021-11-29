package checks;

import nekogochan.StrictCheck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainsDigitsTest {

  StrictCheck check = StrictCheck.containsDigits();

  @Test
  void true_ifContainsAnyDigit() {
    var s = "1_aA";
    assertTrue(check.check(s));
  }

  @Test
  void false_ifNoneContainsDigits() {
    var s = "_Aa";
    assertFalse(check.check(s));
  }
}
