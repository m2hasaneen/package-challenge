package com.mercateo.challenge.packaging.model;

import java.util.List;

public class Item {
  private long              lineNumber;
  private long              maxWeight;
  private List<ItemPackage> packages;
  private String            exception;

  public Item(long maxWeight, List<ItemPackage> packages) {
    this.maxWeight = maxWeight;
    this.packages = packages;
  }

  public Item(long lineNumber, List<ItemPackage> packages, String exception) {
    this.lineNumber = lineNumber;
    this.packages = packages;
    this.exception = exception;
  }

  public Item(List<ItemPackage> packages, String exception) {
    this.lineNumber = lineNumber;
    this.packages = packages;
    this.exception = exception;
  }

  public long getLineNumber() {
    return lineNumber;
  }

  public void setLineNumber(long lineNumber) {
    this.lineNumber = lineNumber;
  }

  public long getMaxWeight() {
    return maxWeight;
  }

  public void setMaxWeight(long maxWeight) {
    this.maxWeight = maxWeight;
  }

  public List<ItemPackage> getPackages() {
    return packages;
  }

  public void setPackages(List<ItemPackage> packages) {
    this.packages = packages;
  }

  public String getException() {
    return exception;
  }

  public void setException(String exception) {
    this.exception = exception;
  }

  @Override
  public String toString() {
    return "Item{" +
            "lineNumber=" + lineNumber +
            ", maxWeight=" + maxWeight +
            ", packages=" + packages +
            ", exception='" + exception + '\'' +
            '}'; 
  }
}
