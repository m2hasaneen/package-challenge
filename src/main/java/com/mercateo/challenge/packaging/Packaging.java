package com.mercateo.challenge.packaging;

import com.mercateo.challenge.packaging.service.PackingService;

public class Packaging {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Invalid input parameter.");
    } else {
      PackingService.init().process(args[0]);
    }
  }
}
