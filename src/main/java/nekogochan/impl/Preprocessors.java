package nekogochan.impl;

import nekogochan.Preprocessor;

import java.util.stream.Stream;

public class Preprocessors {

  public static Preprocessor replace(String regex, String replacement) {
    return s -> s.replaceAll(regex, replacement);
  }

  public static Preprocessor replace(char target, char replacement) {
    return s -> s.replace(target, replacement);
  }

  public static Preprocessor replaceBypasses() {
    return Stream.of(replace("[!1]", "i"),
                     replace('@', 'a'),
                     replace('3', 'e'),
                     replace("[5$]", "s"),
                     replace('0', 'o'),
                     replace('|', 'l'))
                 .reduce(Preprocessor::andThen)
                 .get();

  }
}
