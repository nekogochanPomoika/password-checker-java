package checks;

import nekogochan.Check;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileNotContainsTest {

  Check check = Check.fileNotContains("src/test/java/checks/Passwords.txt");

  public FileNotContainsTest() throws IOException {}

  @Test
  void true_ifInputNotInFile() {
    var s = "some string";
    assertTrue(check.test(s));
  }

  @Test
  void false_ifInputInFile() {
    var s = "password";
    assertFalse(check.test(s));
  }
}
