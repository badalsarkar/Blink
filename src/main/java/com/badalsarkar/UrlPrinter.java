package com.badalsarkar;

/**
 * Filter is used to print ALL or only GOOD or BAD URL
 *
 */
enum PrintFilter{
	ALL,
	GOOD,
	BAD
}

/**
 * This printer takes some settings and apply those for printing
 * URL in the console.
 *
 */
public class UrlPrinter {
	 private PrintFilter urlToPrint= PrintFilter.ALL;
	 private boolean colorOrNot=true;

	public PrintFilter getUrlToPrint() {
		return urlToPrint;
	}

	public void setUrlToPrint(PrintFilter urlToPrint) {
		this.urlToPrint = urlToPrint;
	}
	
	public boolean isPrintInColor() {
		return colorOrNot;
	}

	public void setPrintInColor(boolean printInColor) {
		this.colorOrNot = printInColor;
	}

	public void print(UrlStatus url) {
		if(urlToPrint==PrintFilter.GOOD) {
			if(url.getUrlCategory()==UrlCategory.GOOD) {
				url.print(colorOrNot);
			}
		}
		else if(urlToPrint==PrintFilter.BAD) {
			if(url.getUrlCategory()==UrlCategory.BAD) {
				url.print(colorOrNot);
			}
		}
		else {
			url.print(colorOrNot);
		}
		
	}
	 
}
