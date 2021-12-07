import nekogochan.GradeCheck;
import nekogochan.StrictCheck;
import nekogochan.impl.GradeChecks;
import nekogochan.impl.StandardChecks;
import nekogochan.impl.StrictChecks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  static Path passwordsListPath = Path.of("10-million-password-list-top-1000000.txt");

  static IntPredicate lessThen(int i) {
    return a -> a < i;
  }

  static IntPredicate moreThen(int i) {
    return a -> a > i;
  }


  public static void main(String[] args) throws IOException {

    var passwords = Files.lines(passwordsListPath).collect(Collectors.toSet());

    var standardCheck = StandardChecks.standardLengthPrimaryCheck();
//                                      .and(StandardChecks.notMatchWithPasswordsDbCheck(passwordsListPath, 4));

    Stream.of("passwordwithbiglength",
              "small",
              "STRSTR1!_",
              "megashit",
              "MEGASHIT123_")
          .map(p -> p + " -> " + standardCheck.check(p))
          .forEach(System.out::println);
  }
}
