package checks;

import nekogochan.Check;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainsUppercaseTest {

  Check check = Check.containsUppercase();

  @Test
  void true_ifContainsAnyUppercase() {
    var s = "1_aA";
    assertTrue(check.test(s));
  }

  @Test
  void false_ifNoneContainsUppercase() {
    var s = "1_a";
    assertFalse(check.test(s));
  }
}
