package checks;

import nekogochan.Check;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LengthTest {
  Check check = Check.length(10);

  @Test
  void true_IfInputLengthMoreThen10() {
    var str = "some_string";
    assertTrue(check.test(str));
  }

  @Test
  void false_IfInputLengthLessThen10() {
    var str = "some";
    assertFalse(check.test(str));
  }
}
