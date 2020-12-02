package ca.badalsarkar;


import org.apache.commons.cli.ParseException;

/**
 * Stores information about all CLI options.
 *
 * @author badal
 * @version $Id: $Id
 */
public final class Cli {
  private static CliParserImpl cliParser;
  /** Constant <code>help</code> */
  public static final CliOption help;
  /** Constant <code>version</code> */
  public static final CliOption version;
  /** Constant <code>source</code> */
  public static final CliOption source;
  /** Constant <code>all</code> */
  public static final CliOption all;
  /** Constant <code>good</code> */
  public static final CliOption good;
  /** Constant <code>bad</code> */
  public static final CliOption bad;
  /** Constant <code>in</code> */
  public static final CliOption in;

  static {
    help = new CliOption("h", "help", "Show app usage help.");
    version = new CliOption("v", "version", "Show app version information");
    source =
        new CliOption(
            "s",
            "source",
            "The path of the source file. File name without path, makes current directory as path.");
    all = new CliOption("a", "all", "Print all URLS.");
    good = new CliOption("g", "good", "Print only good URL.");
    bad = new CliOption("b", "bad", "Print only bad URLs.");
    in = new CliOption("i", "in", "Accept text input from standard input");
  }

  /**
   * Initializes CLI. Extracts CLI options values.
   *
   * @param args an array of {@link java.lang.String} objects.
   * @throws org.apache.commons.cli.ParseException if any.
   */
  public static void init(String[] args) throws ParseException {
    cliParser = new CliParserImpl();
    defineCliOptions();
    cliParser.parse(args);
  }

  /** Defines all CLI options. */
  private static void defineCliOptions() {

    cliParser.registerCliOption(help);
    cliParser.registerCliOption(version);
    cliParser.registerCliOption(in);
    cliParser.defineCliOptionWithArgument(source);
    /*
     * Group options These options are mutually exclusive. Only one can be selected
     * from the group
     *
     * Good/Bad/All- This group determines which URL to print Good- Only prints the
     * Good URLs Bad- Only prints the Bad URLs All- Prints all URLs
     */
    cliParser.defineCliGroupOption(all, good, bad);
  }

  /**
   * Uses CliParser to extract CLI options value and stores to respective CliOption
   *
   * @param option a {@link ca.badalsarkar.CliOption} object.
   * @return a {@link java.lang.String} object.
   */
  public static String getCliOptionArgValue(CliOption option) {
    return cliParser.getCliOptionArgValue(option);
  }

  /**
   * Returns
   *
   * @param option a {@link ca.badalsarkar.CliOption} object.
   * @return a boolean.
   */
  public static boolean isSet(CliOption option) {
    return cliParser.isCliOptionSet(option);
  }

  /** Prints app usage help */
  public static void printHelp() {
    cliParser.printHelp();
  }

  /**
   * Prints app version
   *
   * @param appVersion a {@link java.lang.String} object.
   */
  public static void printVersion(String appVersion) {
    cliParser.printVersion(appVersion);
  }
}
