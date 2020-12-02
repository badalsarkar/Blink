package ca.badalsarkar;

enum UrlCategory {
  GOOD,
  BAD,
  REDIRECT
}

/**
 * This class encapsulate a url's status received from HTTP request.
 *
 * @author badal
 * @version $Id: $Id
 */
public class UrlStatus {
  private String url;
  private int statusCode;
  private UrlCategory urlCategory;

  /**
   * Constructor for UrlStatus.
   *
   * @param url a {@link java.lang.String} object.
   * @param status a int.
   */
  public UrlStatus(String url, int status) {
    this.url = url;
    this.statusCode = status;
    if (status == 1) {
      urlCategory = UrlCategory.BAD;
    } else if (status >= 100 && status <= 299) {
      urlCategory = UrlCategory.GOOD;
    } else if (status >= 400 && status <= 499) {
      urlCategory = urlCategory.BAD;
    }
  }

  /**
   * Getter for the field <code>url</code>.
   *
   * @return a {@link java.lang.String} object.
   */
  public String getUrl() {
    return url;
  }

  /**
   * Setter for the field <code>url</code>.
   *
   * @param url a {@link java.lang.String} object.
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * Getter for the field <code>statusCode</code>.
   *
   * @return a int.
   */
  public int getStatusCode() {
    return statusCode;
  }

  /**
   * Setter for the field <code>statusCode</code>.
   *
   * @param statusCode a int.
   */
  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  /**
   * print.
   *
   * @param colorPrint a boolean.
   */
  public void print(boolean colorPrint) {
    if (colorPrint) {
      printToScreenWithColor();
    } else {
      printToScreenWithoutColor();
    }
  }

  private void printToScreenWithColor() {
    System.out.println(PrintColor.get(this.statusCode).format(formatLineForPrinting()));
  }

  private void printToScreenWithoutColor() {
    System.out.println(formatLineForPrinting());
  }

  /**
   * formatLineForPrinting.
   *
   * @return a {@link java.lang.String} object.
   */
  public String formatLineForPrinting() {
    String line = "Status\t[%d]\t[%s]%4s\t%s";
    line =
        String.format(line, this.statusCode, HttpStatusToText.get(this.statusCode), "", this.url);
    return line;
  }

  /**
   * Getter for the field <code>urlCategory</code>.
   *
   * @return a {@link ca.badalsarkar.UrlCategory} object.
   */
  public UrlCategory getUrlCategory() {
    return urlCategory;
  }
}
