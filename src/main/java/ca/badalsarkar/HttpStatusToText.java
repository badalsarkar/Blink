package ca.badalsarkar;


import java.util.TreeMap;

/**
 * This is a simple calss to store status text to different HTTP response code range. Example:
 * 100-199, Good 200-299, Good
 */
public class HttpStatusToText {
  private static TreeMap<Integer, String> httpTextForCode = new TreeMap<Integer, String>();

  static {
    httpTextForCode.put(1, "ERROR");
    httpTextForCode.put(100, "GOOD");
    httpTextForCode.put(200, "GOOD");
    httpTextForCode.put(300, "REDIRECT");
    httpTextForCode.put(400, "BAD");
    httpTextForCode.put(500, "BAD");
    httpTextForCode.put(600, "UNKNOWN");
  }

  /**
   * Returns the stauts corresponding to a HTTP status code
   *
   * @param code
   * @return String
   */
  public static String get(int code) {
    return httpTextForCode.floorEntry(code).getValue();
  }
}
