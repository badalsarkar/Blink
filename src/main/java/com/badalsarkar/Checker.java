package com.badalsarkar;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * This class reads some URLS from urlBuffer which is shared resource.
 * Then it makes Http get request using the URL and writes the result
 * to status resource.
 * @author badal
 *
 */
public class Checker{
	private final int MAX_ASYNC_TASK=100;
	private List<String> urls;
	private List<UrlStatus> httpStatus;
	private int checked=0;
	
	public Checker(List<String> urls) {
		this.urls= urls;
		httpStatus = new ArrayList<UrlStatus>();
	}
	
	/**
	 * Check all the Urls and print to screen and write to a file.
	 */
	public void check() {
		int size = urls.size();
		int remaining = size-checked;
		int totalLink=0;
		while(remaining>0) {
			int asyncTaskToRun = remaining>MAX_ASYNC_TASK?MAX_ASYNC_TASK:remaining;
			List<CompletableFuture<UrlStatus>> httpRequests = new ArrayList<CompletableFuture<UrlStatus>>(asyncTaskToRun);
			for(int i =0; i<asyncTaskToRun;i++) {
				// initiate async task
				String url= urls.get(checked++);
				httpRequests.add(CompletableFuture.supplyAsync(()->makeRequest(url)));
			}
			
			// await for async tasks to complete
			for(CompletableFuture<UrlStatus> task: httpRequests) {
				try {
					//httpStatus.add(task.get());
					task.get().printToScreen();
					totalLink++;
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			remaining=size-checked;
		}
		
		// invoke output method
		System.out.println("Total Link Checked: "+ totalLink);
		
	}
	
	/**
	 * Makes Http request to a URL and returns the status code.
	 * @param url
	 * @return
	 */
	private UrlStatus makeRequest(String url){
        UrlStatus status = null; 
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            status= new UrlStatus(url, connection.getResponseCode());
        }
        catch(Exception ex){
        	return new UrlStatus(url, 1);
        }
        return status;
    }
	
}
