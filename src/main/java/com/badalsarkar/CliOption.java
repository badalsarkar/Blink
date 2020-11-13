package com.badalsarkar;

public final class CliOption {
  private final String shortName;
  private final String longName;
  private final String description;

  public CliOption(String shortName, String longName, String description) {
    this.shortName = shortName;
    this.longName = longName;
    this.description = description;
  }

  public String getShortName() {
    return shortName;
  }

  public String getLongName() {
    return longName;
  }

  public String getDescription() {
    return description;
  }
}
