package com.mercateo.challenge.packaging.service;

import com.mercateo.challenge.packaging.file.PackagingFile;
import com.mercateo.challenge.packaging.model.ItemPackage;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Comparator;
import java.util.stream.Collectors;

public class PackingService {
  private static PackingService packingService;

  private PackingService() {
  }

  public static PackingService init() {
    if (packingService == null) {
      packingService = new PackingService();
    }
    return packingService;
  }

  public void process(final String fileName) {
    try {
      // read lines from file.
      PackagingFile.read(fileName)
              // map string lines to Item Object
              .map(ItemBuilder::build)
              .forEach(item ->
                      Combination.init().subsets(item.getPackages())
                              .stream()
                              .filter(itemPackages -> itemPackages.parallelStream().mapToDouble(ItemPackage::getWeight).sum() <= item.getMaxWeight())
                              .sorted(Comparator.comparingDouble(value -> value.parallelStream().mapToDouble(ItemPackage::getWeight).sum()))
                              .max(Comparator.comparingDouble(value -> value.parallelStream().mapToDouble(ItemPackage::getPrice).sum()))
                              .ifPresent(itemPackages -> {
                                if (item.getException() != null) {
                                  System.out.println(item.getException());
                                } else if (itemPackages.isEmpty()) {
                                  System.out.println("-");
                                } else {
                                  System.out.println(itemPackages.stream().map(ItemPackage::getId).collect(Collectors.joining(",")));
                                }
                              })
              );
    } catch (NoSuchFileException noSuchFileException) {
      System.err.println("File [" + fileName + "] does not exist.");
    } catch (IOException e) {
      System.err.println("Error reading file [" + fileName + "].");
    }
  }
}
