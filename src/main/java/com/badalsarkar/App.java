package com.badalsarkar;

import java.util.List;

public class App {
	private static final String pattern = "(http|https):\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    
    public static void main(String[] args){
    	if(args.length==0) {
    		// print how to use
    		Messages.printHowToUseProgram();
    		System.exit(0);
    	}
    	
    	/**
    	 * @todo add multifile support
    	 */
    	String source =args[0];
    	FileParser fileParser = new FileParser(source, pattern);
    	fileParser.extractAllTokens();
    	List<String> allUrl = fileParser.getAllTokens();
    	for(String s : allUrl) {
    		System.out.println(s);
    	}
        long startTime = System.nanoTime();
    	Checker checker = new Checker(allUrl);
    	checker.check();
        long endTime = System.nanoTime();
        int totalTime = (int)((endTime - startTime)/1000000000L);
        System.out.printf("Total time taken: %d second\n",totalTime );

    }
}
