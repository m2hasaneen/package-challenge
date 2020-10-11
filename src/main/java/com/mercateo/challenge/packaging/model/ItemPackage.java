package com.mercateo.challenge.packaging.model;

public class ItemPackage {
  private String id;
  private double weight;
  private double price;

  public ItemPackage(String id, double weight, double price) {
    this.id = id;
    this.weight = weight;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "ItemPackage{" +
            "id=" + id +
            ", weight=" + weight +
            ", price=" + price +
            '}';
  }
}
