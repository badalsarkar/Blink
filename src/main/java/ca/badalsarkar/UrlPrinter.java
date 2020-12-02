package ca.badalsarkar;

/** Filter is used to print ALL or only GOOD or BAD URL */
enum PrintFilter {
  ALL,
  GOOD,
  BAD
}

/**
 * This printer takes some settings and apply those for printing URL in the console.
 *
 * @author badal
 * @version $Id: $Id
 */
public class UrlPrinter {
  private PrintFilter urlToPrint = PrintFilter.ALL;
  private boolean colorOrNot = true;

  /**
   * Getter for the field <code>urlToPrint</code>.
   *
   * @return a {@link ca.badalsarkar.PrintFilter} object.
   */
  public PrintFilter getUrlToPrint() {
    return urlToPrint;
  }

  /**
   * Setter for the field <code>urlToPrint</code>.
   *
   * @param urlToPrint a {@link ca.badalsarkar.PrintFilter} object.
   */
  public void setUrlToPrint(PrintFilter urlToPrint) {
    this.urlToPrint = urlToPrint;
  }

  /**
   * isPrintInColor.
   *
   * @return a boolean.
   */
  public boolean isPrintInColor() {
    return colorOrNot;
  }

  /**
   * setPrintInColor.
   *
   * @param printInColor a boolean.
   */
  public void setPrintInColor(boolean printInColor) {
    this.colorOrNot = printInColor;
  }

  /**
   * print.
   *
   * @param url a {@link ca.badalsarkar.UrlStatus} object.
   */
  public void print(UrlStatus url) {
    if (urlToPrint == PrintFilter.GOOD) {
      if (url.getUrlCategory() == UrlCategory.GOOD) {
        url.print(colorOrNot);
      }
    } else if (urlToPrint == PrintFilter.BAD) {
      if (url.getUrlCategory() == UrlCategory.BAD) {
        url.print(colorOrNot);
      }
    } else {
      url.print(colorOrNot);
    }
  }
}
