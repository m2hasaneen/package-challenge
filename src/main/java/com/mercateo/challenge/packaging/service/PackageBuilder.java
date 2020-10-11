package com.mercateo.challenge.packaging.service;

import com.mercateo.challenge.packaging.constant.Constraints;
import com.mercateo.challenge.packaging.exception.ExceedConstraintsException;
import com.mercateo.challenge.packaging.exception.FormatException;
import com.mercateo.challenge.packaging.model.ItemPackage;

public class PackageBuilder {
  public static ItemPackage build(final String data) throws ExceedConstraintsException, FormatException {
    // is valid package data
    isValid(data);

    // is valid package structure
    isValidPackageStructure(data);

    String[] packageData = data.substring(1, data.length() - 1).split(",");

    String id = packageData[0];

    double weight = Double.parseDouble(packageData[1]);
    isMaxWeighExceed(data, weight);

    double cost = Double.parseDouble(packageData[2].substring(1));
    isMaxCostExceed(data, cost);

    return new ItemPackage(id, weight, cost);

  }

  private static void isValid(final String data) throws FormatException {
    if (data != null && data.isEmpty()) {
      throw new FormatException("package item [" + data + "] format is not valid.");
    }
  }

  private static void isMaxWeighExceed(final String data, final double weight) throws ExceedConstraintsException {
    if (weight > Constraints.PACKAGE_MAX_WEIGHT) {
      throw new ExceedConstraintsException("package item [" + data + "] weight [" + weight + "] exceed [" + Constraints.PACKAGE_MAX_WEIGHT + "].");
    }
  }

  private static void isMaxCostExceed(final String data, final double cost) throws ExceedConstraintsException {
    if (cost > Constraints.PACKAGE_MAX_COST) {
      throw new ExceedConstraintsException("package item [" + data + "] cost [" + cost + "] exceed [" + Constraints.PACKAGE_MAX_COST + "].");
    }
  }

  private static void isValidPackageStructure(final String data) throws FormatException {
    if (!data.startsWith("(") || !data.endsWith(")")) {
      throw new FormatException("package item [" + data + "] format is not valid.");
    }
  }

  private static void isValidPackageItems(final String[] data) throws FormatException {
    if (data.length != 3) {
      throw new FormatException("package item [" + data + "] format is not valid.");
    }
  }
}
