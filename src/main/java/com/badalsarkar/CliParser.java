package com.badalsarkar;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

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
	 * @return CLI argurments
	 * @throws org.apache.commons.cli.ParseException
	 */
	public static CommandLine getCliArgs(String[] args) throws org.apache.commons.cli.ParseException {
		CommandLineParser parser = new DefaultParser();
		addOptions();
		CommandLine cli = null;
		cli = parser.parse(OPTIONS, args);
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
		Option recursiveSearch = Option.builder("r").longOpt("recursive").desc("Starting from the file path entered by the user, recursively search all sub-directories for txt and html files").build();
		OPTIONS.addOption(version);
		OPTIONS.addOption(destination);
		OPTIONS.addOption(source);
		OPTIONS.addOption(help);
		OPTIONS.addOption(recursiveSearch);
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
	
	
	//Added by Bing Pan, October 2nd, 2020.
	//Based on the directory path the user entered via CLI, 
	//this function recursively searches all the sub-directories 
	//and looks for files with certain types of file extension such as txt or html
	//and returns an ArrayList of file paths in String that meet the file extension requirements.
	public static List<String> recursiveSearchSubDirectories(String source) {
		ArrayList<String> allFilePathFromSubDir = new ArrayList<>();
		
		File f = new File(source.toLowerCase());

		if (f.exists()) {			
			if (f.isDirectory()) {
				Collection<File> files = FileUtils.listFiles(f, new String[] {"txt", "html"}, true);
				for(File file : files) {
					allFilePathFromSubDir.add(file.getPath().toString());
				}
			}
		}
		else {
			System.out.println( "\"" + source + "\"" + ": invalid input for a directory/path");
		}
		
		return allFilePathFromSubDir;
	}
	
	
	
	
	
	
	
	
	
	
	
}
