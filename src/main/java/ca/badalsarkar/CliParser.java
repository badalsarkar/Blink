package ca.badalsarkar;

/**
 * This interface must be implemented by Cli Parser.
 *
 * @author badal
 * @version $Id: $Id
 */
public interface CliParser {
  /**
   * registerCliOption.
   *
   * @param option a {@link ca.badalsarkar.CliOption} object.
   */
  void registerCliOption(CliOption option);

  /**
   * defineCliOptionWithArgument.
   *
   * @param option a {@link ca.badalsarkar.CliOption} object.
   */
  void defineCliOptionWithArgument(CliOption option);

  /**
   * defineCliGroupOption.
   *
   * @param options a {@link ca.badalsarkar.CliOption} object.
   */
  void defineCliGroupOption(CliOption... options);

  /**
   * parse.
   *
   * @param args an array of {@link java.lang.String} objects.
   * @throws java.lang.Exception if any.
   */
  void parse(String[] args) throws Exception;

  /**
   * isCliOptionSet.
   *
   * @param option a {@link ca.badalsarkar.CliOption} object.
   * @return a boolean.
   */
  boolean isCliOptionSet(CliOption option);

  /**
   * getCliOptionArgValue.
   *
   * @param option a {@link ca.badalsarkar.CliOption} object.
   * @return a {@link java.lang.String} object.
   */
  String getCliOptionArgValue(CliOption option);

  /** printHelp. */
  void printHelp();
}
