package ca.badalsarkar;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extractor class.
 *
 * @author badal
 * @version $Id: $Id
 */
public class Extractor {

  /**
   * Extract all urls from a file
   *
   * @param source a {@link java.lang.String} object.
   * @param pattern a {@link java.util.regex.Pattern} object.
   * @return a {@link java.util.List} object.
   * @throws java.io.IOException if any.
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
   * @param text a {@link java.lang.String} object.
   * @param pattern a {@link java.util.regex.Pattern} object.
   * @return a {@link java.util.List} object.
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
