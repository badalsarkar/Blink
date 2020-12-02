package ca.badalsarkar;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * This class provides support to parse CLI arguments. I have used Apache Commons CLI
 *
 * @see <a href=
 *     "http://commons.apache.org/proper/commons-cli/introduction.html">http://commons.apache.org/proper/commons-cli/introduction.html</a>
 * @author badal
 * @version $Id: $Id
 */
public class CliParserImpl implements CliParser {
  private static final Options OPTIONS = new Options();
  private CommandLine cli;

  /**
   * {@inheritDoc}
   *
   * <p>Define CLI options
   */
  @Override
  public void registerCliOption(CliOption option) {
    OPTIONS.addOption(
        Option.builder(option.getShortName())
            .longOpt(option.getLongName())
            .desc(option.getDescription())
            .build());
  }

  /**
   * {@inheritDoc}
   *
   * <p>Define CLI options that requires argument
   */
  @Override
  public void defineCliOptionWithArgument(CliOption option) {
    OPTIONS.addOption(
        Option.builder(option.getShortName())
            .longOpt(option.getLongName())
            .hasArg()
            .desc(option.getDescription())
            .build());
  }

  /** {@inheritDoc} */
  @Override
  public void defineCliGroupOption(CliOption... options) {
    OptionGroup groupOptions = new OptionGroup();
    for (CliOption c : options) {
      groupOptions.addOption(
          Option.builder(c.getShortName())
              .longOpt(c.getLongName())
              .desc(c.getDescription())
              .build());
    }
    OPTIONS.addOptionGroup(groupOptions);
  }

  /**
   * {@inheritDoc}
   *
   * <p>Process CLI arguments and returns it.
   */
  @Override
  public void parse(String[] args) throws ParseException {
    CommandLineParser parser = new DefaultParser();
    cli = parser.parse(OPTIONS, args);
  }

  /** {@inheritDoc} */
  @Override
  public boolean isCliOptionSet(CliOption option) {
    if (cli != null && cli.hasOption(option.getShortName())) {
      return true;
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public String getCliOptionArgValue(CliOption option) {
    if (cli != null) {
      return cli.getOptionValue(option.getShortName());
    }
    return "";
  }

  /** Prints help for CLI -h */
  public void printHelp() {
    String header = "\nFind dead URL link.\n\n";
    String footer =
        "\nPlease report issues at https://github.com/badalsarkar/URLChecker/issues\n\n";
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("urlchecker", header, OPTIONS, footer, true);
  }

  /**
   * Prints App version.
   *
   * @param version a {@link java.lang.String} object.
   */
  public void printVersion(String version) {
    String header = "\nBlink";
    System.out.println(header + " " + version);
  }
}
