package com.badalsarkar;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.cli.ParseException;

/** Main class. */
public class App {
  private static List<String> allUrls;
  private static UrlPrinter urlPrinter;
  private static final String appVersion = "V0.1";
  /** This pattern matches string with beginning word HTTP/HTTPS. */
  private static final String pattern =
      "(http|https):\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

  public static void main(String[] args) {
    Environment.extractAllVariables();
    /** if Cli.getVersion */
    try {
      Cli.init(args);
      if (Cli.isSet(Cli.help)) {
        Cli.printHelp();
        System.exit(0);
      }

      if (Cli.isSet(Cli.version)) {
        Cli.printVersion(appVersion);
      }

      if (Cli.isSet(Cli.in)) {
        configureUrlPrinter();
        try (BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")))) {
          while (true) {
            String text = null;
            if ((text = reader.readLine()) != null) {
              Checker.check(
                  Extractor.extractUrlFromText(text, Pattern.compile(pattern)), urlPrinter);
            } else {
              break;
            }
          }
        }
        System.exit(0);
      }
      if (Cli.isSet(Cli.source)) {
        configureUrlPrinter();
        processFile(Cli.getCliOptionArgValue(Cli.source), urlPrinter);
        System.exit(0);
      } else {
        Cli.printHelp();
        System.exit(0);
      }
    } catch (IOException iox) {
      System.out.println("Encountered error while handling file");
      System.exit(0);
    } catch (SecurityException sx) {
      System.out.println("You don't have permission to write to file");
      System.exit(0);
    } catch (ParseException px) {
      System.out.println("Commandlin arguments can'g be parsed.");
    }
  }

  /**
   * Configures a printer for printing URL. This printer has several settings to modify the way URL
   * status is printed in the screen.
   */
  private static void configureUrlPrinter() {
    urlPrinter = new UrlPrinter();
    // Only prints good URL
    if (Cli.isSet(Cli.good)) {
      urlPrinter.setUrlToPrint(PrintFilter.GOOD);
    }
    // Only prints bad URL
    if (Cli.isSet(Cli.bad)) {
      urlPrinter.setUrlToPrint(PrintFilter.BAD);
    }
    // Color print or not
    urlPrinter.setPrintInColor(Environment.getCliColor());
  }

  /**
   * Process the file to extract all HTTP/HTTPS links and check if links are valid.
   *
   * @param path File path
   * @param destination File to save the result
   * @param printInColor When true, the output is printed in color in console
   */
  private static void processFile(String source, UrlPrinter urlPrinter)
      throws IOException, SecurityException {
    System.out.println("Processing...");
    extractUrl(source);
    checkUrl(urlPrinter);
  }

  /**
   * Extracts URLs from file matching pattern.
   *
   * @param source
   */
  private static void extractUrl(String source) throws IOException {
    allUrls = Extractor.extractUrlFromFile(source, Pattern.compile(pattern));
  }

  /**
   * Make HTTP request and store the result.
   *
   * @param destination File to save the result
   * @throws IOException
   * @throws SecurityException
   */
  private static void checkUrl(UrlPrinter urlPrinter) throws IOException, SecurityException {
    Checker.check(allUrls, urlPrinter);
  }
}
