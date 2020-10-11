package com.mercateo.challenge.packaging.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PackagingFile {
  public static Stream<String> read(String fileName) throws IOException {
    return Files.newBufferedReader(Paths.get(fileName)).lines().filter(str -> !str.trim().isEmpty()).map(str -> str.toUpperCase());
  }
}
