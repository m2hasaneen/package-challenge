package com.mercateo.challenge.packaging.service;

import com.mercateo.challenge.packaging.model.ItemPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for providing a Combination of all distinct elements of an array.
 * */
public class Combination {

  private static Combination combination;

  private Combination() {
  }

  public static Combination init() {
    if (combination == null) {
      combination = new Combination();
    }
    return combination;
  }

  public List<List<ItemPackage>> subsets(List<ItemPackage> nums) {
    List<List<ItemPackage>> list = new ArrayList<>();
    subsetsHelper(list, new ArrayList<>(), nums, 0);
    return list;
  }

  private void subsetsHelper(List<List<ItemPackage>> list, List<ItemPackage> resultList, List<ItemPackage> nums, int start) {
    list.add(new ArrayList<>(resultList));
    for (int i = start; i < nums.size(); i++) {
      // add element
      resultList.add(nums.get(i));
      // Explore
      subsetsHelper(list, resultList, nums, i + 1);
      // remove
      resultList.remove(resultList.size() - 1);
    }
  }
}
