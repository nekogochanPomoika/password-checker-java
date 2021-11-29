import nekogochan.PasswordChecker;
import nekogochan.Preprocessor;

import java.io.IOException;
import java.util.stream.Stream;

import static nekogochan.Check.fileNotContains;
import static nekogochan.Check.length;

public class Main {
  public static void main(String[] args) throws IOException {
    var checker = new PasswordChecker.Builder()
      .addChecker("length", length(10))
      .addChecker("password_database", fileNotContains("10-million-password-list-top-1000000.txt"))
      .build();

    Stream.of("fireman10273")
          .map(s -> s + ": " + checker.check(s))
          .forEach(System.out::println);
  }
}
