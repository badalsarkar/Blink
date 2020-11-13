package com.badalsarkar;

enum UrlCategory {
  GOOD,
  BAD,
  REDIRECT
}

/** This class encapsulate a url's status received from HTTP request. */
public class UrlStatus {
  private String url;
  private int statusCode;
  private UrlCategory urlCategory;

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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

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

  public String formatLineForPrinting() {
    String line = "Status\t[%d]\t[%s]%4s\t%s";
    line =
        String.format(line, this.statusCode, HttpStatusToText.get(this.statusCode), "", this.url);
    return line;
  }

  public UrlCategory getUrlCategory() {
    return urlCategory;
  }
}
