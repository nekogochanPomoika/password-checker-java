package impl;

import nekogochan.impl.StandardChecks;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StandardChecksTest {

  @Test
  void standardLengthPrimaryCheck() {
    var check = StandardChecks.standardLengthPrimaryCheck();
    assertTrue(check.check("passwordwithbiglength"));
    assertFalse(check.check("small"));
    assertFalse(check.check("STRSTR1!_"));
    assertFalse(check.check("megashit"));
    assertTrue(check.check("MEGASHIT123_"));
  }

  @Test
  void notMatchWithPasswordsDbCheck() throws IOException {
    var check = StandardChecks.notMatchWithPasswordsDbCheck(Path.of("src/test/java/Passwords.txt"), 2);
    assertTrue(check.check("str"));
    assertFalse(check.check("password"));
    assertFalse(check.check("password1"));
    assertFalse(check.check("passwo"));
  }
}
