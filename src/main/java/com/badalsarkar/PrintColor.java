package com.badalsarkar;

import java.util.Map;
import java.util.TreeMap;

import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;

public class PrintColor {
	private static final AnsiFormat greenColor = new AnsiFormat(Attribute.GREEN_TEXT());
    private static final AnsiFormat redColor = new AnsiFormat(Attribute.RED_TEXT());
    private static final AnsiFormat yellowColor = new AnsiFormat(Attribute.YELLOW_TEXT());
    private static final AnsiFormat blackColor = new AnsiFormat(Attribute.BLACK_TEXT());
    private static TreeMap<Integer, AnsiFormat> httpTextForCode= new TreeMap<Integer, AnsiFormat>();
    static {
    	httpTextForCode.put(1, greenColor);
    	httpTextForCode.put(100, yellowColor);
    	httpTextForCode.put(200, greenColor);
    	httpTextForCode.put(300, yellowColor);
    	httpTextForCode.put(400, redColor);
    	httpTextForCode.put(500, redColor);
    	httpTextForCode.put(600, blackColor);
    }
    	    		
	public static AnsiFormat get(int httpStatus) {
		return httpTextForCode.floorEntry(httpStatus).getValue();
	}
	
}
