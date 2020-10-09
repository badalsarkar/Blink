package com.badalsarkar;

/**
 * This is an utility class which encapsulates process of 
 * environmental variables. All the environmental variables 
 * are managed by this class. Any other class/methods needing 
 * environmental variable consults with this class.
 *
 */
public final class Environment {
	// All supported variables
	private static boolean cliColor;	// Used to print URL status in color
	
	// extracts all variables
	public static void extractAllVariables() {
		cliColor = Integer.parseInt(System.getenv("CLICOLOR"))==1?true:false;
	}
	
	public static boolean getCliColor() {
		return cliColor;
	}
}
