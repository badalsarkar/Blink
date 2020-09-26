package com.badalsarkar;

/**
 * This class encapsulate a url's status received from HTTP request.
 *
 */
public class UrlStatus {
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
		System.out.println(PrintColor.get(this.status).format(formatLineForPrinting()));
	}

	public String formatLineForPrinting() {
		String line = "Status\t[%d]\t[%s]%4s\t%s";
		line = String.format(line, this.status, HttpStatusToText.get(this.status), "", this.url);
		return line;
	}
}
