package com.badalsarkar.blink.test;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.badalsarkar.App;

public class TestApp {

  @Test
  public void testAppWithNoArguments() throws IOException {
    PrintStream originalStdOut = System.out;
    File actualOutput = File.createTempFile("Blink-help-test", null);
    PrintStream printToFile = new PrintStream(actualOutput);
    System.setOut(printToFile);

    String[] args = {};
    App.main(args);
    File expectedOutput = new File("src/test/resources/help-test-output.txt");

    System.setOut(originalStdOut);
    printToFile.close();
    Assertions.assertTrue(FileUtils.contentEquals(actualOutput, expectedOutput));
    actualOutput.delete();
  }

  @Test
  public void testAppWithHelp() throws IOException {
    PrintStream originalStdOut = System.out;
    File actualOutput = File.createTempFile("Blink-help-test", null);
    PrintStream printToFile = new PrintStream(actualOutput);
    System.setOut(printToFile);

    String[] args = {"-h"};
    App.main(args);

    File expectedOutput = new File("src/test/resources/help-test-output.txt");

    System.setOut(originalStdOut);
    printToFile.close();
    Assertions.assertTrue(FileUtils.contentEquals(actualOutput, expectedOutput));
    actualOutput.delete();
  }

  @Test
  public void testAppWithVersion() throws IOException {
    PrintStream originalStdOut = System.out;
    ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
    PrintStream consoleContent = new PrintStream(actualOutput);
    System.setOut(consoleContent);

    String[] args = {"-v"};
    App.main(args);

    System.setOut(originalStdOut);
    consoleContent.close();
    Assertions.assertEquals(actualOutput.toString(), "\nBlink V0.1");
  }
}
