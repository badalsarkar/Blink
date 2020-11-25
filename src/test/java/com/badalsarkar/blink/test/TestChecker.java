package com.badalsarkar.blink.test;

import static com.apisimulator.embedded.SuchThat.contains;
import static com.apisimulator.embedded.SuchThat.isEqualTo;
import static com.apisimulator.embedded.http.HttpApiSimulation.httpApiSimulation;
import static com.apisimulator.embedded.http.HttpApiSimulation.httpRequest;
import static com.apisimulator.embedded.http.HttpApiSimulation.httpResponse;
import static com.apisimulator.embedded.http.HttpApiSimulation.simlet;

import com.apisimulator.embedded.http.HttpApiSimulation;
import com.badalsarkar.Checker;
import com.badalsarkar.UrlStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test for {@code Checker} class
 *
 * @author badal
 */
public class TestChecker {

  private static HttpApiSimulation mockServer = null;

  @BeforeAll
  public static void setUp() {
    mockServer = httpApiSimulation("mockSim");
    // defining mock response
    mockServer.add(
        simlet("test-google-good")
            .when(
                httpRequest()
                    .whereMethod("HEAD")
                    .whereUriPath(isEqualTo("/return/200"))
                    .whereHeader("Host", contains("google.com")))
            .then(httpResponse().withStatus(200).withHeader("Content-Type", "application/text")));

    mockServer.add(
        simlet("test-google-bad")
            .when(
                httpRequest()
                    .whereMethod("HEAD")
                    .whereUriPath(isEqualTo("/return/404"))
                    .whereHeader("Host", contains("google.com")))
            .then(httpResponse().withStatus(404).withHeader("Content-Type", "application/text")));

    mockServer.add(
        simlet("test-google-redirect")
            .when(
                httpRequest()
                    .whereMethod("HEAD")
                    .whereUriPath(isEqualTo("/return/301"))
                    .whereHeader("Host", contains("google.com")))
            .then(httpResponse().withStatus(301).withHeader("Content-Type", "application/text")));
  }

  @AfterAll
  public static void tearDown() {
    if (mockServer != null) {
      mockServer.stop();
    }
  }

  @Test
  public void testGoodUrl() throws Exception {
    try {
      System.setProperty("http.proxyHost", "localhost");
      System.setProperty("http.proxyPort", "6090");

      UrlStatus url = Checker.makeRequest("http://www.google.com/return/200");
      Assertions.assertEquals(200, url.getStatusCode());
    } finally {
      System.clearProperty("http.proxyHost");
      System.clearProperty("http.proxyPort");
    }
  }

  @Test
  public void testBadUrl() throws Exception {
    try {
      System.setProperty("http.proxyHost", "localhost");
      System.setProperty("http.proxyPort", "6090");

      UrlStatus url = Checker.makeRequest("http://www.google.com/return/404");
      Assertions.assertEquals(404, url.getStatusCode());
    } finally {
      System.clearProperty("http.proxyHost");
      System.clearProperty("http.proxyPort");
    }
  }

  @Test
  public void testRedirectUrl() throws Exception {
    try {
      System.setProperty("http.proxyHost", "localhost");
      System.setProperty("http.proxyPort", "6090");

      UrlStatus url = Checker.makeRequest("http://www.google.com/return/301");
      Assertions.assertEquals(301, url.getStatusCode());
    } finally {
      System.clearProperty("http.proxyHost");
      System.clearProperty("http.proxyPort");
    }
  }
}
