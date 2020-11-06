package com.badalsarkar;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores information about all CLI options.
 * 
 * @author badal
 *
 */
public final class Cli {
	private static CliParserImpl cliParser;
	public static final CliOption help;
	public static final CliOption version;
	public static final CliOption source;
	public static final CliOption destination;
	public static final CliOption all;
	public static final CliOption good;
	public static final CliOption bad;
	public static final CliOption in;

	static {
		help = new CliOption("h", "help", "Show app usage help.");
		version = new CliOption("v", "version", "Show app version information");
		source = new CliOption("s", "source",
				"The path of the source file. File name without path, makes current directory as path.");
		destination = new CliOption("d", "destination", "The path of the destination file.");
		all = new CliOption("a", "all", "Print all URLS.");
		good = new CliOption("g", "good", "Print only good URL.");
		bad = new CliOption("b", "bad", "Print only bad URLs.");
		in = new CliOption("i", "in", "Accept text input from standard input");
	}

	/**
	 * Initializes CLI. Extracts CLI options values.
	 */
	public static void init(String[] args) {
		cliParser = new CliParserImpl();
		defineCliOptions();
		cliParser.parse(args);
	}

	/**
	 * Defines all CLI options.
	 */
	private static void defineCliOptions() {

		cliParser.registerCliOption(help);
		cliParser.registerCliOption(version);
		cliParser.registerCliOption(in);
		cliParser.defineCliOptionWithArgument(source);
		cliParser.defineCliOptionWithArgument(destination);
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
	 * Uses CliParser to extract CLI options value and stores to respective
	 * CliOption
	 */
	public static String getCliOptionArgValue(CliOption option) {
		return cliParser.getCliOptionArgValue(option);
	}

	/**
	 * Returns
	 */
	public static boolean isSet(CliOption option) {
		return cliParser.isCliOptionSet(option);
	}

	/**
	 * Prints app usage help
	 */
	public static void printHelp() {
		cliParser.printHelp();
	}
	
	/**
	 * Prints app version
	 */
	public static void printVersion(String appVersion) {
		cliParser.printVersion(appVersion);
	}
}
