package checks;

import nekogochan.Check;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainsLowercaseTest {

  Check check = Check.containsLowercase();

  @Test
  void true_ifContainsAnyLowercase() {
    var s = "1_aA";
    assertTrue(check.test(s));
  }

  @Test
  void false_ifNoneContainsLowercase() {
    var s = "1_A";
    assertFalse(check.test(s));
  }
}
