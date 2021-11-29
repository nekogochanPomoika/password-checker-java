import nekogochan.GradeCheck;
import nekogochan.StrictCheck;

import java.io.IOException;
import java.util.function.IntPredicate;
import java.util.stream.Stream;

public class Main {

  static IntPredicate lessThen(int i) {
    return a -> a < i;
  }

  static IntPredicate moreThen(int i) {
    return a -> a > i;
  }

  public static void main(String[] args) throws IOException {
    var check = Stream.of(GradeCheck.length().or(5, moreThen(5)).multiply(10).subtract(20),
                          GradeCheck.digitCount().or(0, moreThen(3)).multiply(10),
                          GradeCheck.specialsCount().or(3, moreThen(3)).multiply(15),
                          GradeCheck.uppercaseCount().or(3, moreThen(3)).multiply(10))
                      .reduce(GradeCheck::andAdd).get()
                      .or(100, moreThen(100))
                      .toStrictCheck(0)
                      .and(StrictCheck.fileNotContains("10-million-password-list-top-1000000.txt"));

    System.out.println("check = " + check.check("fuckyou"));
  }
}
