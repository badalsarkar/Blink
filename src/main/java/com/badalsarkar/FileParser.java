package com.badalsarkar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FileParser reads a file and extracts http link and add to 
 * UrlBuffer which is a shared resource.
 *
 */

public class FileParser{
	private final Pattern pattern;
	private final String source;
	private final List<String> allTokens= new ArrayList<String>();
	
	// Constructor
	public FileParser(String source, String pattern) {
		this.source=source;
		this.pattern= Pattern.compile(pattern);
	}
	
	/**
	 * Stores an URL to the urlBuffer.
	 * This runs is a separate thread and writes to a shared resource.
	 * It simply reads a line from the source and extracts URLs from 
	 * the line and stores in the urlBuffer variable.
	 */
	public void extractAllTokens() {
		File file = new File(source);
		try (Scanner in = new Scanner(file);){
			while(in.hasNextLine()) {
				addUrl(in.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Extracts URLs from a line
	 * @param line
	 * @return List<String>
	 */
	private void addUrl(String line) {
		int startPos=0;
		int endPos=0;
		List<String> url= new ArrayList<String>();
		Matcher matcher = this.pattern.matcher(line);
		while(matcher.find()) {
			startPos= matcher.start();
			endPos= matcher.end();
			this.addToken(line.substring(startPos, endPos));
		}
	}

	
	private void addToken(String item) {
		this.allTokens.add(item);
	}
	
	public List<String> getAllTokens(){
		return this.allTokens;
	}
}
