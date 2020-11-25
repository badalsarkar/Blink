package com.badalsarkar;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {

  /**
   * Extract all urls from a file
   *
   * @param source
   * @param pattern
   * @return
   * @throws IOException
   */
  public static List<String> extractUrlFromFile(String source, Pattern pattern) throws IOException {
    List<String> urls = new ArrayList<String>();
    if (argsAreInvalid(source, pattern)) {
      return urls;
    }
    File file = new File(source);
    Scanner in = new Scanner(file, "UTF-8");
    while (in.hasNextLine()) {
      urls.addAll(extractUrlFromText(in.nextLine(), pattern));
    }
    in.close();
    return urls;
  }

  /**
   * Extract all urls from some text
   *
   * @param text
   * @param pattern
   * @return
   */
  public static List<String> extractUrlFromText(String text, Pattern pattern) {
    List<String> url = new ArrayList<String>();
    if (argsAreInvalid(text, pattern)) {
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

  /**
   * Validattion function
   *
   * @param text
   * @param pattern
   * @return
   */
  private static boolean argsAreInvalid(String text, Pattern pattern) {
    if (text == null || text.length() == 0 || pattern == null) {
      return true;
    }
    return false;
  }
}
