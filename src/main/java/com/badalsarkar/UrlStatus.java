package com.badalsarkar;

import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;

public class UrlStatus {

	//AnsiFormat greenColor = new AnsiFormat(Attribute.GREEN_TEXT());
    //AnsiFormat redColor = new AnsiFormat(Attribute.RED_TEXT());
	private String url;
	private int status;
	
	public UrlStatus(String url, int status) {
		this.url = url;
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStatusCode() {
		return status;
	}
	public void setStatusCode(int statusCode) {
		this.status = statusCode;
	}
	public void printToScreen() {
		String printLine= "Status\t[%d]\t[%s]%4s\t%s";
		printLine= String.format(printLine, this.status, HttpStatusToText.get(this.status),"", this.url);
		System.out.println(PrintColor.get(this.status).format(printLine));
	}


}
