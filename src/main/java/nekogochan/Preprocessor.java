package nekogochan;

import java.util.function.UnaryOperator;

public interface Preprocessor extends UnaryOperator<String> {
  default Preprocessor andThen(Preprocessor that) {
    return s -> that.apply(this.apply(s));
  }

  static Preprocessor replaceAll(String... regexAndReplacments) {
    if (regexAndReplacments.length % 2 == 1) {
      throw new IllegalArgumentException("inputs regexAndReplacments must be odd");
    }
    return s -> {
      for (int i = 0; i < regexAndReplacments.length; i += 2) {
        var k = regexAndReplacments[i];
        var v = regexAndReplacments[i + 1];
        s = s.replaceAll(k, v);
      }
      return s;
    };
  }
}
