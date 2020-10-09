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
 * This class provides support to parse CLI arguments. I have used Apache
 * Commons CLI
 * {@link http://commons.apache.org/proper/commons-cli/introduction.html}
 *
 */
public class CliParser {
	private static final Options OPTIONS = new Options();

	/**
	 * Process CLI arguments and returns it.
	 * 
	 * @param args
	 * @return CLI arguments
	 * @throws org.apache.commons.cli.ParseException
	 */
	public static CommandLine getCliArgs(String[] args) {
		CommandLineParser parser = new DefaultParser();
		CommandLine cli = null;
		try {
			addOptions();
			addOptionGroups();
			cli = parser.parse(OPTIONS, args);
		} catch (ParseException e) {
			System.out.println();
			System.out.println("[ERROR]" + e.getMessage());
			printHelp();
			System.exit(1);
		}
		return cli;
	}

	/**
	 * Configures CLI options
	 */
	private static void addOptions() {
		Option version = Option.builder("v").longOpt("version").desc("App version").build();
		Option destination = Option.builder("d").longOpt("destination").hasArg().desc("The destination file path")
				.build();
		Option source = Option.builder("s").longOpt("source").hasArg().desc("The source file path").build();
		Option help = Option.builder("h").longOpt("help").desc("Help").build();
		OPTIONS.addOption(version);
		OPTIONS.addOption(destination);
		OPTIONS.addOption(source);
		OPTIONS.addOption(help);
	}

	/**
	 * Configure mutually exclusive options
	 */
	private static void addOptionGroups() {
		OptionGroup optionGroup = new OptionGroup();
		Option all = Option.builder("a").longOpt("all").desc("Prints both good and bad URL").build();
		Option good = Option.builder("g").longOpt("good").desc("Prints only good URL").build();
		Option bad = Option.builder("b").longOpt("bad").desc("Prints only bad URL").build();
		optionGroup.addOption(all).addOption(good).addOption(bad);
		// optionGroup.setSelected(all);
		OPTIONS.addOptionGroup(optionGroup);
	}

	/**
	 * Prints help for CLI -h
	 */
	public static void printHelp() {
		String header = "\nFind dead URL link.\n\n";
		String footer = "\nPlease report issues at https://github.com/badalsarkar/URLChecker/issues\n\n";
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("urlchecker", header, OPTIONS, footer, true);
	}

	/**
	 * Prints app version.
	 */
	public static void printVersion() {
		String header = "\nurlchecker";
		String version = "\nV.0.1";
		System.out.println(header + version);
	}
}
