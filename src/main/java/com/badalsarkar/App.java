package com.badalsarkar;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

/**
 * Main class.
 *
 */
public class App {
	private static int totalProcessingTime;
	private static List<String> allUrls;
	/**
	 * This pattern matches string with beginning word HTTP/HTTPS.
	 */
	private static final String pattern = "(http|https):\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	public static void main(String[] args) {
		Environment.extractAllVariables();
		try {
			// this is for parsing command line arguments.
			CommandLine cli = CliParser.getCliArgs(args);
			if (cli.hasOption("version")) {
				CliParser.printVersion();
				System.exit(0);
			}
			if (cli.hasOption("help")) {
				CliParser.printHelp();
				System.exit(0);
			}
			if (cli.hasOption("source")) {
				processFile(cli.getOptionValue("source"), cli.getOptionValue("destination"), Environment.getCliColor());
				printSummary();
				System.exit(0);
			} else {
				CliParser.printHelp();
				System.exit(0);
			}

		} catch (ParseException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException iox) {
			System.out.println("Encountered error while handling file");
			System.exit(0);
		} catch (SecurityException sx) {
			System.out.println("You don't have permission to write to file");
			System.exit(0);
		}

	}


	/**
	 * Process the file to extract all HTTP/HTTPS links and check if links are
	 * valid.
	 * 
	 * @param path        File path
	 * @param destination File to save the result
	 * @param printInColor When true, the output is printed in color in console
	 */
	private static void processFile(String source, String destination, boolean printInColor) throws IOException, SecurityException {
		System.out.println("Processing...");
		// Just to track how long it takes to execute
		long startTime = System.nanoTime();
		extractUrl(source);
		checkUrl(destination, printInColor);
		long endTime = System.nanoTime();
		totalProcessingTime = (int) ((endTime - startTime) / 1000000000L);
	}

	/**
	 * Extracts URLs from file matching pattern.
	 * 
	 * @param source
	 */
	private static void extractUrl(String source) throws IOException {
		FileParser fileParser = new FileParser(source, pattern);
		fileParser.extractAllUrls();
		allUrls = fileParser.getAllUrls();
	}

	/**
	 * Make HTTP request and store the result.
	 * 
	 * @param destination File to save the result
	 * @throws IOException
	 * @throws SecurityException
	 */
	private static void checkUrl(String destination, boolean printInColor) throws IOException, SecurityException {
		List<UrlStatus> urlStatus = Checker.check(allUrls, printInColor);
		if (destination != null) {
			Writer writer = new Writer();
			writer.setPrintWriter(destination);
			System.out.println("Writing to file...");
			for (UrlStatus status : urlStatus) {
				writer.append(status.formatLineForPrinting());
			}
			System.out.println("Written to file :" + destination);
		}
	}

	/**
	 * Prints a summary after processing.
	 */
	private static void printSummary() {
		System.out.printf("\n\tTotal link processed:%7s\n", allUrls.size());
		System.out.printf("\tTotal processing time:%7s seconds\n", totalProcessingTime);
	}
}
