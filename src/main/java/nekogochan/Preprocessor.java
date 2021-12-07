package nekogochan;

import java.util.function.UnaryOperator;

public interface Preprocessor {

  String apply(String str);

  default Preprocessor andThen(Preprocessor that) {
    return s -> that.apply(this.apply(s));
  }

  default Preprocessor compose(Preprocessor that) {
    return that.andThen(this);
  }

  default UnaryOperator<String> asUnaryOperator() {
    return this::apply;
  }

  static Preprocessor of(Preprocessor that) {
    return that;
  }
}
