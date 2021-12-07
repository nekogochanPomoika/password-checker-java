import nekogochan.Preprocessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PreprocessorTest {

  Preprocessor preprocessor = s -> s + "str";

  @Test
  void apply() {
    assertEquals("some str", preprocessor.apply("some "));
  }

  @Test
  void andThen() {
    var prep = preprocessor.andThen(s -> s + " 222");
    assertEquals("some str 222", prep.apply("some "));
  }

  @Test
  void compose() {
    var prep = preprocessor.compose(s -> s + "222 ");
    assertEquals("222 str some", prep.apply(" some"));
  }
}
