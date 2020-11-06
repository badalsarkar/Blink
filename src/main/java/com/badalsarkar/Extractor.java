package com.badalsarkar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {
	
	public static List<String> extractUrlFromText(String text, Pattern pattern){
		int startPos = 0;
		int endPos = 0;
		List<String> url = new ArrayList<String>();
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			startPos = matcher.start();
			endPos = matcher.end();
			url.add(text.substring(startPos, endPos));
		}
		return url;
	}
}
