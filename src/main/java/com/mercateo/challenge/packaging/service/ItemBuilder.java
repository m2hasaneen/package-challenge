package com.mercateo.challenge.packaging.service;

import com.mercateo.challenge.packaging.constant.Constraints;
import com.mercateo.challenge.packaging.exception.ExceedConstraintsException;
import com.mercateo.challenge.packaging.exception.FormatException;
import com.mercateo.challenge.packaging.model.Item;
import com.mercateo.challenge.packaging.model.ItemPackage;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
  public static Item build(final String data) {
    List<ItemPackage> packages = new ArrayList<>();

    // separate based on spaces
    String[] args = data.split(" ");

    if (args.length == 0) {
      return new Item(new ArrayList<>(), "item [" + data + "] format is not valid.");
    }

    // validate package format
    if (args.length < 3) {
      return new Item(new ArrayList<>(), "item [" + data + "] format is not valid.");
    }

    // Validate package separator
    if (!args[1].equals(":")) {
      return new Item(new ArrayList<>(), "item [" + data + "] separator is not valid.");
    }

    long itemWeight = Long.parseLong(args[0]);

    // validate number packages
    int numberOfPackages = args.length - 2;
    if (numberOfPackages > Constraints.PACKAGE_ITEMS) {
      return new Item(new ArrayList<>(), "item " + itemWeight + " weight [" + numberOfPackages + "] exceed [" + Constraints.PACKAGE_ITEMS + "].");
    }

    // validate item max weight
    if (itemWeight > Constraints.PACKAGE_ITEM_WEIGHT) {
      return new Item(itemWeight, new ArrayList<>(), "item " + data + " weight [" + itemWeight + "] exceed [" + Constraints.PACKAGE_ITEM_WEIGHT + "].");
    }

    // loop over packages to parse them into packages object
    for (int counter = 2; counter <= args.length - 1; counter++) {
      try {
        packages.add(PackageBuilder.build(args[counter]));
      } catch (ExceedConstraintsException | FormatException e) {
        return new Item(itemWeight, new ArrayList<>(), e.getMessage());
      }
    }
    return new Item(itemWeight, packages);
  }
}
