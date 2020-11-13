package com.badalsarkar;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * This class provides support to parse CLI arguments. I have used Apache Commons CLI {@link
 * http://commons.apache.org/proper/commons-cli/introduction.html}
 */
public class CliParserImpl implements CliParser {
  private static final Options OPTIONS = new Options();
  private CommandLine cli;

  /** Define CLI options */
  @Override
  public void registerCliOption(CliOption option) {
    OPTIONS.addOption(
        Option.builder(option.getShortName())
            .longOpt(option.getLongName())
            .desc(option.getDescription())
            .build());
  }

  @Override
  public void defineCliOptionWithArgument(CliOption option) {
    OPTIONS.addOption(
        Option.builder(option.getShortName())
            .longOpt(option.getLongName())
            .hasArg()
            .desc(option.getDescription())
            .build());
  }

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
   * Process CLI arguments and returns it.
   *
   * @param args
   * @return CLI arguments
   * @throws org.apache.commons.cli.ParseException
   */
  @Override
  public void parse(String[] args) {
    CommandLineParser parser = new DefaultParser();
    try {
      cli = parser.parse(OPTIONS, args);
    } catch (ParseException e) {
      printHelp();
      System.exit(1);
    }
  }

  @Override
  public boolean isCliOptionSet(CliOption option) {
    if (cli.hasOption(option.getShortName())) {
      return true;
    }
    return false;
  }

  @Override
  public String getCliOptionArgValue(CliOption option) {
    return cli.getOptionValue(option.getShortName());
  }

  /** Prints help for CLI -h */
  public void printHelp() {
    String header = "\nFind dead URL link.\n\n";
    String footer =
        "\nPlease report issues at https://github.com/badalsarkar/URLChecker/issues\n\n";
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("urlchecker", header, OPTIONS, footer, true);
  }

  /** Prints App version. */
  public void printVersion(String version) {
    String header = "\nurlchecker";
    System.out.println(header + "\n" + version);
  }
}
