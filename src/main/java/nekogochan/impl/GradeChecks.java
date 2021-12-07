package nekogochan.impl;

import nekogochan.GradeCheck;

import java.util.regex.Pattern;

public class GradeChecks {

  public static GradeCheck length() {
    return String::length;
  }

  public static GradeCheck lowercaseCount() {
    return matchesCount("[a-z]");
  }

  public static GradeCheck uppercaseCount() {
    return matchesCount("[A-Z]");
  }

  public static GradeCheck digitCount() {
    return matchesCount("\\d");
  }

  public static GradeCheck specialsCount() {
    return matchesCount("[!@#$%^&*()\\-+=\\\\|/.,:;\\[\\]{}]");
  }

  public static GradeCheck matchesCount(String regex) {
    var pattern = Pattern.compile(regex);
    return s -> {
      var m = pattern.matcher(s);
      var i = 0;
      while (m.find()) {
        i++;
      }
      return i;
    };
  }
}
