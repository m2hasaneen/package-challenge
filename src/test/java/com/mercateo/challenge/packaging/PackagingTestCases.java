package com.mercateo.challenge.packaging;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class PackagingTestCases {
  private final ByteArrayOutputStream outContent  = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent  = new ByteArrayOutputStream();
  private final PrintStream           originalOut = System.out;
  private final PrintStream           originalErr = System.err;

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void sampleResponseWith4Elements() throws IOException {
    String result = Files.lines(Paths.get("src/test/resources/output/Output-4.txt")).collect(Collectors.joining("\n")) + "\n";
    // call service
    Packaging.main(new String[]{"src/test/resources/input/Input-4.txt"});
    // validate response
    assertEquals(result, outContent.toString());
  }

  @Test
  public void inputInvalidPackageFormat() throws IOException {
    String result = Files.lines(Paths.get("src/test/resources/output/Output-invalid-package-format.txt")).collect(Collectors.joining("\n")) + "\n";
    // call service
    Packaging.main(new String[]{"src/test/resources/input/Input-invalid-package-format.txt"});
    // validate response
    assertEquals(result, outContent.toString());
  }

  @Test
  public void invalidFilePath() throws IOException {
    String result = Files.lines(Paths.get("src/test/resources/output/invalid-file-path.txt")).collect(Collectors.joining("\n")) + "\n";
    // call service
    Packaging.main(new String[]{"src/test/resources/input/invalid-file-path.txt"});
    // validate response
    assertEquals(result, errContent.toString());
  }

  @Test
  public void nullFilePath() throws IOException {
    String result = Files.lines(Paths.get("src/test/resources/output/invalid-input.txt")).collect(Collectors.joining("\n")) + "\n";
    // call service
    Packaging.main(new String[]{});
    // validate response
    Files.write(Paths.get("./output-1.txt"), outContent.toByteArray());
    assertEquals(result, outContent.toString());
  }

  @Test
  public void emptyFilePath() throws IOException {
    String result = Files.lines(Paths.get("src/test/resources/output/empty-file.txt")).collect(Collectors.joining("\n"));
    // call service
    Packaging.main(new String[]{"src/test/resources/input/empty-file.txt"});
    // validate response
    Files.write(Paths.get("./output-1.txt"), outContent.toByteArray());
    assertEquals(result, outContent.toString());
  }
}
