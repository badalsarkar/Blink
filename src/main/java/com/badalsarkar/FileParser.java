package com.badalsarkar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FileParser reads a file and extracts HTTP/HTTPS links
 *
 */
public class FileParser {
	private final Pattern pattern;
	private final String source;
	private final List<String> allUrls = new ArrayList<String>();

	// Constructor
	public FileParser(String source, String pattern) {
		this.source = source;
		this.pattern = Pattern.compile(pattern);
	}

	/**
	 * Extracts all URLs from file.
	 */
	public void extractAllUrls() throws IOException {
		File file = new File(source);
		Scanner in = new Scanner(file);
		while (in.hasNextLine()) {
			extractUrlFromLine(in.nextLine());
		}
		in.close();
	}

	/**
	 * Extracts URLs from a line. A line can have multiple URL.
	 * 
	 * @param line
	 * @return List<String>
	 */
	private void extractUrlFromLine(String line) {
		int startPos = 0;
		int endPos = 0;
		List<String> url = new ArrayList<String>();
		Matcher matcher = this.pattern.matcher(line);
		while (matcher.find()) {
			startPos = matcher.start();
			endPos = matcher.end();
			this.addToAllUrls(line.substring(startPos, endPos));
		}
	}

	/**
	 * Adds an item to allUrls.
	 * 
	 * @param item
	 */
	private void addToAllUrls(String item) {
		this.allUrls.add(item);
	}

	/**
	 * Returns allUrls
	 * 
	 * @return
	 */
	public List<String> getAllUrls() {
		return this.allUrls;
	}
}
