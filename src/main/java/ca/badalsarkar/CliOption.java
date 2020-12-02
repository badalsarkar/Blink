package ca.badalsarkar;

/**
 * CliOption class.
 *
 * @author badal
 * @version $Id: $Id
 */
public final class CliOption {
  private final String shortName;
  private final String longName;
  private final String description;

  /**
   * Constructor for CliOption.
   *
   * @param shortName a {@link java.lang.String} object.
   * @param longName a {@link java.lang.String} object.
   * @param description a {@link java.lang.String} object.
   */
  public CliOption(String shortName, String longName, String description) {
    this.shortName = shortName;
    this.longName = longName;
    this.description = description;
  }

  /**
   * Getter for the field <code>shortName</code>.
   *
   * @return a {@link java.lang.String} object.
   */
  public String getShortName() {
    return shortName;
  }

  /**
   * Getter for the field <code>longName</code>.
   *
   * @return a {@link java.lang.String} object.
   */
  public String getLongName() {
    return longName;
  }

  /**
   * Getter for the field <code>description</code>.
   *
   * @return a {@link java.lang.String} object.
   */
  public String getDescription() {
    return description;
  }
}
