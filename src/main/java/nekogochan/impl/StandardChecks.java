package nekogochan.impl;

import nekogochan.GradeCheck;
import nekogochan.StrictCheck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StandardChecks {

  public static StrictCheck standardLengthPrimaryCheck() {
    return Stream.of(GradeChecks.length().multiply(8),
                     StrictChecks.containsDigits().toGrade(8, 0),
                     StrictChecks.containsUppercase().toGrade(8, 0),
                     StrictChecks.containsSpecials().toGrade(8, 0))
                 .reduce(GradeCheck::andAdd).get()
                 .toStrict(100);
  }

  public static StrictCheck notMatchWithPasswordsDbCheck(Path file, int lengthDiff) throws IOException {
    var passwords = Files.lines(file).collect(Collectors.toSet());
    return StrictChecks.contains(passwords, (a, b) -> {
      if (Math.abs(a.length() - b.length()) > lengthDiff) {
        return false;
      }
      var _a = a.toLowerCase();
      var _b = b.toLowerCase();
      return _a.contains(_b) && _b.contains(_a);
    }).negate();
  }
}
