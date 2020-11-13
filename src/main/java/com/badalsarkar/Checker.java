package com.badalsarkar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/** This class makes HTTP request with an URL and records its response. */
public class Checker {
  // HTTP requests are made asynchronously.
  // Currently, I am making 100 async request
  // at a time. I still need to check how far
  // I can go increasing this number, without
  // affecting performance.
  private static final int MAX_ASYNC_TASK = 100;
  // private List<String> urls;
  // private int checked=0;

  public Checker(List<String> urls) {
    // this.urls= urls;
  }

  /** Check all the Urls and print to screen. */
  public static List<UrlStatus> check(List<String> urls, UrlPrinter urlPrinter) {
    int size = urls.size();
    int remaining = size;
    int checked = 0;
    List<UrlStatus> urlStatus = new ArrayList<UrlStatus>();
    // Start sending http request
    while (remaining > 0) {
      // Checking if I need to run 100 async task or less
      int asyncTaskToRun = remaining > MAX_ASYNC_TASK ? MAX_ASYNC_TASK : remaining;
      List<CompletableFuture<UrlStatus>> httpRequests =
          new ArrayList<CompletableFuture<UrlStatus>>(asyncTaskToRun);
      // initiating async task
      for (int i = 0; i < asyncTaskToRun; i++) {
        String url = urls.get(checked++);
        httpRequests.add(CompletableFuture.supplyAsync(() -> makeRequest(url)));
      }

      // await for async tasks to complete
      for (CompletableFuture<UrlStatus> task : httpRequests) {
        try {
          UrlStatus status = task.get();
          urlStatus.add(status);
          urlPrinter.print(status);
        } catch (InterruptedException | ExecutionException e) {
          // empty
          // continue even if exception
        } catch (Exception ex) {
          // Exception might come from printToScreen
          // ignore it and keep processing
        }
      }
      remaining = size - checked;
    }
    return urlStatus;
  }

  /**
   * Makes Http request to a URL and returns the status code. @TODO add feature to handle
   * redirect @TODO add feature to handle different network error
   *
   * @param url
   * @return {@link UrlStatus}
   */
  private static UrlStatus makeRequest(String url) {
    UrlStatus status = null;
    try {
      HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
      connection.setInstanceFollowRedirects(true);
      connection.setRequestMethod("HEAD");
      connection.setConnectTimeout(5000);
      status = new UrlStatus(url, connection.getResponseCode());
    } catch (IOException ex) {
      // Currently, if there is any network error e.g Timeout,
      // I am returning 1, and this represents "BAD" status.
      return new UrlStatus(url, 1);
    }
    return status;
  }
}
