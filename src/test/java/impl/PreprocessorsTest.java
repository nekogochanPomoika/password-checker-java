package impl;

import nekogochan.impl.Preprocessors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PreprocessorsTest {

  @Test
  void replace_1() {
    var prep = Preprocessors.replace("[ab]", "c");
    assertEquals("ccc", prep.apply("abc"));
  }

  @Test
  void replace_2() {
    var prep = Preprocessors.replace('a', 'c');
    assertEquals("cbc", prep.apply("abc"));
  }

  @Test
  void replaceBypasses() {
    var prep = Preprocessors.replaceBypasses();
    assertEquals("password", prep.apply("p@5$w0rd"));
  }
}
