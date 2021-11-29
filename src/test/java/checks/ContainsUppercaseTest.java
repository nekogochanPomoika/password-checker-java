package checks;

import nekogochan.StrictCheck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainsUppercaseTest {

  StrictCheck check = StrictCheck.containsUppercase();

  @Test
  void true_ifContainsAnyUppercase() {
    var s = "1_aA";
    assertTrue(check.check(s));
  }

  @Test
  void false_ifNoneContainsUppercase() {
    var s = "1_a";
    assertFalse(check.check(s));
  }
}
