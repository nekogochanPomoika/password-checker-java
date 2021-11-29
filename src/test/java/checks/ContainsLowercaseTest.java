package checks;

import nekogochan.StrictCheck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainsLowercaseTest {

  StrictCheck check = StrictCheck.containsLowercase();

  @Test
  void true_ifContainsAnyLowercase() {
    var s = "1_aA";
    assertTrue(check.check(s));
  }

  @Test
  void false_ifNoneContainsLowercase() {
    var s = "1_A";
    assertFalse(check.check(s));
  }
}
