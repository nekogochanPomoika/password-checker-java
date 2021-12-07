package nekogochan;

import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

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

  default GradeCheck limitTop(int limit) {
    return s -> Math.min(limit, check(s));
  }

  default GradeCheck limitBottom(int limit) {
    return s -> Math.max(limit, check(s));
  }

  default GradeCheck limit(int bottom, int top) {
    return s -> Math.min(Math.max(check(s), bottom), top);
  }

  default GradeCheck or(int i, Predicate<String> condition) {
    return s -> condition.test(s) ? i : this.check(s);
  }

  default GradeCheck or(int i, IntPredicate condition) {
    return orGet(() -> i, condition);
  }

  default GradeCheck orGet(IntSupplier i, Predicate<String> condition) {
    return s -> condition.test(s) ? i.getAsInt() : this.check(s);
  }

  default GradeCheck orGet(IntSupplier i, IntPredicate condition) {
    return s -> {
      var val = this.check(s);
      if (condition.test(val)) {
        return i.getAsInt();
      }
      return val;
    };
  }

  default GradeCheck orCheck(GradeCheck that, Predicate<String> condition) {
    return s -> (condition.test(s) ? that : this).check(s);
  }

  default GradeCheck withPreprocessor(Preprocessor preprocessor) {
    return s -> this.check(preprocessor.apply(s));
  }

  static GradeCheck of(GradeCheck that) {
    return that;
  }

  default StrictCheck toStrict(int threshold) {
    return s -> this.check(s) >= threshold;
  }
}
