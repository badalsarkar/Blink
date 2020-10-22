package com.badalsarkar;

import java.io.IOException;
import java.util.List;

/**
 * Main class.
 *
 */
public class App {
	private static List<String> allUrls;
	private static UrlPrinter urlPrinter;
	private static final String appVersion = "V0.1";
	/**
	 * This pattern matches string with beginning word HTTP/HTTPS.
	 */
	private static final String pattern = "(http|https):\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	public static void main(String[] args) {
		Environment.extractAllVariables();
		Cli.init(args);
		/**
		 * if Cli.getVersion
		 */
		
		try {
			if(Cli.isSet(Cli.help)) {
				Cli.printHelp();
				System.exit(0);
			}
			
			if(Cli.isSet(Cli.version)) {
				Cli.printVersion(appVersion);
			}
			
			if(Cli.isSet(Cli.source)) {
				configureUrlPrinter();
				processFile(Cli.getCliOptionArgValue(Cli.source), Cli.getCliOptionArgValue(Cli.destination), urlPrinter);
				System.exit(0);
			}
			else {
				Cli.printHelp();
				System.exit(0);
			}
		} catch (IOException iox) {
			System.out.println("Encountered error while handling file");
			System.exit(0);
		} catch (SecurityException sx) {
			System.out.println("You don't have permission to write to file");
			System.exit(0);
		}

	}

	/**
	 * Configures a printer for printing URL.
	 * This printer has several settings to modify 
	 * the way URL status is printed in the screen.
	 */
	private static void configureUrlPrinter() {
		urlPrinter = new UrlPrinter();
		// Only prints good URL
		if(Cli.isSet(Cli.good)) {
			urlPrinter.setUrlToPrint(PrintFilter.GOOD);
		}
		// Only prints bad URL
		if(Cli.isSet(Cli.bad)) {
			urlPrinter.setUrlToPrint(PrintFilter.BAD);
		}
		// Color print or not
		urlPrinter.setPrintInColor(Environment.getCliColor());
	}
	
	/**
	 * Process the file to extract all HTTP/HTTPS links and check if links are
	 * valid.
	 * 
	 * @param path        File path
	 * @param destination File to save the result
	 * @param printInColor When true, the output is printed in color in console
	 */
	private static void processFile(String source, String destination, UrlPrinter urlPrinter) throws IOException, SecurityException {
		System.out.println("Processing...");
		extractUrl(source);
		checkUrl(destination, urlPrinter);
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
	private static void checkUrl(String destination, UrlPrinter urlPrinter) throws IOException, SecurityException {
		List<UrlStatus> urlStatus = Checker.check(allUrls, urlPrinter);
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
}
