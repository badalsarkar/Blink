package com.badalsarkar;

/**
 * This interface must be implemented by Cli Parser.
 *
 * @author badal
 */
public interface CliParser {
  void registerCliOption(CliOption option);

  void defineCliOptionWithArgument(CliOption option);

  void defineCliGroupOption(CliOption... options);

  void parse(String[] args);

  boolean isCliOptionSet(CliOption option);

  String getCliOptionArgValue(CliOption option);

  void printHelp();
}
