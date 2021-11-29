package nekogochan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface Check extends Predicate<String> {
  static Check length(int length) {
    return s -> s.length() >= length;
  }

  static Check containsLowercase() {
    return contains("[a-z]");
  }

  static Check containsUppercase() {
    return contains("[A-Z]");
  }

  static Check containsDigits() {
    return contains("\\d");
  }

  static Check containsSpecials() {
    return contains("[!@#$%^&*()_\\-+=\\\\|/.,:;\\[\\]{}]");
  }

  static Check contains(String regex) {
    return Pattern.compile(regex).asPredicate()::test;
  }

  static Check fileNotContains(String filename) throws IOException {
    var passwords = Files.newBufferedReader(Path.of(filename)).lines().collect(Collectors.toSet());
    return s -> !passwords.contains(s);
  }
}
