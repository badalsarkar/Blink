package com.badalsarkar;

enum PrintFilter{
	ALL,
	GOOD,
	BAD
}

public class UrlPrinter {
	 private PrintFilter urlToPrint= PrintFilter.ALL;

	public PrintFilter getUrlToPrint() {
		return urlToPrint;
	}

	public void setUrlToPrint(PrintFilter urlToPrint) {
		this.urlToPrint = urlToPrint;
	}
	
	public void print(UrlStatus url) {
		if(urlToPrint==PrintFilter.GOOD) {
			if(url.getUrlCategory()==UrlCategory.GOOD) {
				url.printToScreen();
			}
		}
		else if(urlToPrint==PrintFilter.BAD) {
			if(url.getUrlCategory()==UrlCategory.BAD) {
				url.printToScreen();
			}
		}
		else {
			url.printToScreen();
		}
		
	}
	 
}
