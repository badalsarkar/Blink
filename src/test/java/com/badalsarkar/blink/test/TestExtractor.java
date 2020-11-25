package com.badalsarkar.blink.test;


import com.badalsarkar.Extractor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestExtractor {

  @Test
  @DisplayName("Text contains two URLs, Extractor returns List of two URLs")
  void extractTwoUrls() {
    String text = "http://github.com https://github.com/badalsarkar/Blink";
    Pattern pattern =
        Pattern.compile("(http|https):\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    List<String> actualResult = Extractor.extractUrlFromText(text, pattern);

    List<String> expectedResult = new ArrayList<String>(2);
    expectedResult.add("http://github.com");
    expectedResult.add("https://github.com/badalsarkar/Blink");
    Assertions.assertEquals(expectedResult, actualResult);
  }

  @Test
  @DisplayName("Text contains no valid URL")
  void extractorReturnsNoValidUrl() {
    String text = "github.com htt://github.com";
    Pattern pattern =
        Pattern.compile("(http|https):\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    List<String> actualResult = Extractor.extractUrlFromText(text, pattern);

    List<String> expectedResult = new ArrayList<String>(2);
    Assertions.assertEquals(expectedResult, actualResult);
  }

  @Test
  @DisplayName("Text is null, should return empty List")
  void extractorWithNullText() {
    Pattern pattern =
        Pattern.compile("(http|https):\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    List<String> actualResult = Extractor.extractUrlFromText(null, pattern);

    List<String> expectedResult = new ArrayList<String>(2);
    Assertions.assertEquals(expectedResult, actualResult);
  }

  @Test
  @DisplayName("Text is null, should return empty List")
  void extractorWithEmptyText() {
    String text = "";
    Pattern pattern =
        Pattern.compile("(http|https):\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    List<String> actualResult = Extractor.extractUrlFromText(text, pattern);

    List<String> expectedResult = new ArrayList<String>(2);
    Assertions.assertEquals(expectedResult, actualResult);
  }

  @Test
  @DisplayName("Pattern is null, should return empty List")
  void extractorWithNullPattern() {
    String text = "github.com htt://github.com";

    List<String> actualResult = Extractor.extractUrlFromText(text, null);

    List<String> expectedResult = new ArrayList<String>(2);
    Assertions.assertEquals(expectedResult, actualResult);
  }
}
