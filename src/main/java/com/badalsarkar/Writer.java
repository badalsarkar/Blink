package com.badalsarkar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class provides support for writing to file.
 * @author badal
 *
 */
public class Writer {
	private PrintWriter printWriter;
	
	public Writer() {
		//empty
	}
	
	/**
	 * Attaches a resource reference to printWriter.
	 * @param path
	 * @throws IOException
	 * @throws SecurityException
	 */
	public void setPrintWriter(String path) throws IOException, SecurityException {
		File file = new File(path);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		this.printWriter = new PrintWriter(bw);
	}
	
	/**
	 * Appends a line to the file.
	 * @param line
	 */
	public void append(String line) {
		this.printWriter.println(line);
	}
	
	/**
	 * Closes the file.
	 */
	public void closeWriter() {
		printWriter.close();
	}

}
