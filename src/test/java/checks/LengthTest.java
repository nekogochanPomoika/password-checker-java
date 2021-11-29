package checks;

import nekogochan.StrictCheck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LengthTest {
  StrictCheck check = StrictCheck.length(10);

  @Test
  void true_IfInputLengthMoreThen10() {
    var str = "some_string";
    assertTrue(check.check(str));
  }

  @Test
  void false_IfInputLengthLessThen10() {
    var str = "some";
    assertFalse(check.check(str));
  }
}
