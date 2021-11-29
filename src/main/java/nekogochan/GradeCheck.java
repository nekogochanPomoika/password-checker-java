package nekogochan;

import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public interface GradeCheck {

  int check(String password);

  default GradeCheck add(int i) {
    return s -> check(s) + i;
  }

  default GradeCheck multiply(int i) {
    return s -> check(s) * i;
  }

  default GradeCheck subtract(int i) {
    return s -> check(s) - i;
  }

  default GradeCheck divide(int i) {
    return s -> check(s) / i;
  }

  default GradeCheck andAdd(GradeCheck that) {
    return s -> this.check(s) +
                that.check(s);
  }

  default GradeCheck or(int that, Predicate<String> predicate) {
    return s -> predicate.test(s) ? that : this.check(s);
  }

  default GradeCheck or(int that, IntPredicate predicate) {
    return s -> {
      var val = this.check(s);
      if (predicate.test(val)) {
        val = that;
      }
      return val;
    };
  }

  default GradeCheck orGet(IntSupplier that, Predicate<String> predicate) {
    return s -> predicate.test(s) ? that.getAsInt() : this.check(s);
  }

  default GradeCheck orCheck(GradeCheck that, Predicate<String> predicate) {
    return s -> (predicate.test(s) ? that : this).check(s);
  }

  default StrictCheck toStrictCheck(int threshold) {
    return s -> this.check(s) >= threshold;
  }

  static GradeCheck of(GradeCheck that) {
    return that;
  }

  static GradeCheck length() {
    return String::length;
  }

  static GradeCheck lowercaseCount() {
    return matchesCount("[a-z]");
  }

  static GradeCheck uppercaseCount() {
    return matchesCount("[A-Z]");
  }

  static GradeCheck digitCount() {
    return matchesCount("\\d");
  }

  static GradeCheck specialsCount() {
    return matchesCount("[!@#$%^&*()\\-+=\\\\|/.,:;\\[\\]{}]");
  }

  static GradeCheck matchesCount(String regex) {
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
