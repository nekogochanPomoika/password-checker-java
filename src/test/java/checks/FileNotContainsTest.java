package checks;

import nekogochan.StrictCheck;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileNotContainsTest {

  StrictCheck check = StrictCheck.fileNotContains("src/test/java/checks/Passwords.txt");

  public FileNotContainsTest() throws IOException {}

  @Test
  void true_ifInputNotInFile() {
    var s = "some string";
    assertTrue(check.check(s));
  }

  @Test
  void false_ifInputInFile() {
    var s = "password";
    assertFalse(check.check(s));
  }
}
