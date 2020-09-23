package com.badalsarkar;

import java.util.Map;
import java.util.TreeMap;

public class HttpStatusToText {
	private static TreeMap<Integer, String> httpTextForCode= new TreeMap<Integer, String>();
	static{
		httpTextForCode.put(1, "BAD");
		httpTextForCode.put(100, "GOOD");
		httpTextForCode.put(200, "GOOD");
		httpTextForCode.put(300, "REDIRECT");
		httpTextForCode.put(400, "BAD");
		httpTextForCode.put(500, "BAD");
		httpTextForCode.put(600, "UNKNOWN");
	}
	
	public static String get(int code) {
		return httpTextForCode.floorEntry(code).getValue();
	}
}
