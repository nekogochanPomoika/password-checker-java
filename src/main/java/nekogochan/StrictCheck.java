package nekogochan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface StrictCheck {
  
  boolean check(String s);

  static StrictCheck of(StrictCheck that) {
    return that;
  }

  default GradeCheck toGrade(int onTrue, int onFalse) {
    return s -> this.check(s) ? onTrue : onFalse;
  }

  default StrictCheck and(StrictCheck that) {
    return s -> this.check(s) && that.check(s);
  }

  static StrictCheck length(int length) {
    return s -> s.length() >= length;
  }

  static StrictCheck containsLowercase() {
    return contains("[a-z]");
  }

  static StrictCheck containsUppercase() {
    return contains("[A-Z]");
  }

  static StrictCheck containsDigits() {
    return contains("\\d");
  }

  static StrictCheck containsSpecials() {
    return contains("[!@#$%^&*()_\\-+=\\\\|/.,:;\\[\\]{}]");
  }

  static StrictCheck contains(String regex) {
    return Pattern.compile(regex).asPredicate()::test;
  }

  static StrictCheck fileNotContains(String filename) throws IOException {
    var passwords = Files.newBufferedReader(Path.of(filename)).lines().collect(Collectors.toSet());
    return s -> !passwords.contains(s);
  }
}
