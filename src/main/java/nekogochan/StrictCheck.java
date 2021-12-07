package nekogochan;

public interface StrictCheck {

  boolean check(String s);

  default StrictCheck and(StrictCheck that) {
    return s -> this.check(s) && that.check(s);
  }

  default StrictCheck or(StrictCheck that) {
    return s -> this.check(s) || that.check(s);
  }

  default StrictCheck xor(StrictCheck that) {
    return s -> this.check(s) ^ that.check(s);
  }

  default StrictCheck negate() {
    return s -> !check(s);
  }

  default StrictCheck withPreprocessor(Preprocessor preprocessor) {
    return s -> check(preprocessor.apply(s));
  }

  default GradeCheck toGrade(int onTrue, int onFalse) {
    return s -> check(s) ? onTrue : onFalse;
  }

  static StrictCheck of(StrictCheck that) {
    return that;
  }
}
