package nekogochan.impl;

import nekogochan.StrictCheck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StrictChecks {

  public static StrictCheck length(int length) {
    return s -> s.length() >= length;
  }

  public static StrictCheck containsLowercase() {
    return contains("[a-z]");
  }

  public static StrictCheck containsUppercase() {
    return contains("[A-Z]");
  }

  public static StrictCheck containsDigits() {
    return contains("\\d");
  }

  public static StrictCheck containsSpecials() {
    return contains("[!@#$%^&*()_\\-+=\\\\|/.,:;\\[\\]{}]");
  }

  public static StrictCheck contains(String regex) {
    return Pattern.compile(regex).asPredicate()::test;
  }

  public static StrictCheck contains(Set<String> set) {
    return set::contains;
  }

  public static StrictCheck contains(Set<String> set, BiPredicate<String, String> matchRule) {
    return s -> set.stream().anyMatch(_s -> matchRule.test(s, _s));
  }
}
