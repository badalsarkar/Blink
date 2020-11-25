package com.badalsarkar;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {

  public static List<String> extractUrlFromText(String text, Pattern pattern) {
    List<String> url = new ArrayList<String>();
    if (text == null || text.length() == 0 || pattern == null) {
      return url;
    }
    int startPos = 0;
    int endPos = 0;
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      startPos = matcher.start();
      endPos = matcher.end();
      url.add(text.substring(startPos, endPos));
    }
    return url;
  }
}
