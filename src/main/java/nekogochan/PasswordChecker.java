package nekogochan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface PasswordChecker {
  List<String> check(String password);

  class Builder {
    private Preprocessor preprocessor = s -> s;
    private final Map<String, Predicate<String>> checkers = new HashMap<>();

    public Builder withPreprocessor(Preprocessor preprocessor) {
      this.preprocessor = preprocessor;
      return this;
    }

    public Builder addChecker(String key, Predicate<String> checker) {
      checkers.put(key, checker);
      return this;
    }

    public PasswordChecker build() {
      return preprocessor.andThen(this::findErrors)::apply;
    }

    private List<String> findErrors(String s) {
      var errors = new ArrayList<String>();
      checkers.forEach((k, ch) -> {
        if (!ch.test(s)) {
          errors.add(k);
        }
      });
      return errors;
    }
  }
}
