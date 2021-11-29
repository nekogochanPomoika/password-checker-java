package checks;

import nekogochan.Check;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainsDigitsTest {

  Check check = Check.containsDigits();

  @Test
  void true_ifContainsAnyDigit() {
    var s = "1_aA";
    assertTrue(check.test(s));
  }

  @Test
  void false_ifNoneContainsDigits() {
    var s = "_Aa";
    assertFalse(check.test(s));
  }
}
